package com.affise.attribution.module.rustore.referrer

import android.app.Application
import android.content.SharedPreferences
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.modules.store.StoreApi
import com.affise.attribution.referrer.AffiseReferrerData
import com.affise.attribution.usecase.StoreUseCase
import ru.rustore.sdk.install.referrer.InstallReferrerClient
import ru.rustore.sdk.install.referrer.model.InstallReferrer


internal class RuStoreReferrerUseCase(
    private val preferences: SharedPreferences?,
    private val app: Application?,
    private val logsManager: LogsManager?,
    private val toStringConverter: RuStoreReferrerDataToStringConverter,
    private val toRuStoreReferrerDataConverter: StringToRuStoreReferrerDataConverter,
): StoreApi {

    private var client: InstallReferrerClient? = null

    private var installReferrerUpdated: String? = null

    init {
        app?.let {
            client = InstallReferrerClient(it)
        }
    }

    override fun startInstallReferrerRetrieve(onFinished: (() -> Unit)?) {
        client?.getInstallReferrer()?.let {
            it.addOnSuccessListener { result ->
                result?.let { data ->
                    processReferrerDetails(data)
                    setReferrerUpdated(data)
                }
                onFinished?.invoke()
            }
            it.addOnFailureListener { throwable ->
                logsManager?.addSdkError(throwable)
                onFinished?.invoke()
            }
        } ?: onFinished?.invoke()
    }

    private fun processReferrerDetails(data: InstallReferrer) {
        //Generate referrer data
        RuStoreReferrerData(
            installAppTimestamp = data.installAppTimestamp,
            packageName = data.packageName,
            receivedTimestamp = data.receivedTimestamp,
            referrerId = data.referrerId,
            versionCode = data.versionCode,
        )
            .let(toStringConverter::convert)
            .let(::storeToSharedPreferences)
    }

    fun getRuStoreInstallReferrerData(): RuStoreReferrerData? {
        return preferences
            ?.getString(RUSTORE_REFERRER_KEY, null)
            ?.let(toRuStoreReferrerDataConverter::convert)
    }

    override fun getInstallReferrerData(): AffiseReferrerData {
        val ruStore = getRuStoreInstallReferrerData()
        return AffiseReferrerData(
            installReferrer = ruStore?.referrerId ?: "",
            referrerClickTimestampSeconds = 0,
            installBeginTimestampSeconds = 0,
            referrerClickTimestampServerSeconds = ruStore?.receivedTimestamp ?: 0,
            installBeginTimestampServerSeconds = ruStore?.installAppTimestamp ?: 0,
            installVersion = "",
            googlePlayInstantParam = false
        )
    }

    @Synchronized
    override fun isInstallReferrerUpdated(): Boolean {
        val result = isReferrerDataUpdated()
        if (result) {
            setReferrerDataUpdated(false)
        }
        return result
    }

    private fun storeToSharedPreferences(s: String) {
        preferences?.edit()?.let {
            it.putString(RUSTORE_REFERRER_KEY, s)
            it.commit()
        }
    }

    private fun setReferrerUpdated(data: InstallReferrer) {
        if (
            installReferrerUpdated != null &&
            installReferrerUpdated != data.referrerId
        ) {
            setReferrerDataUpdated(true)
        }

        installReferrerUpdated = data.referrerId
    }

    private fun setReferrerDataUpdated(value: Boolean) {
        preferences?.edit()?.let {
            it.putBoolean(StoreUseCase.REFERRER_UPDATED_KEY, value)
            it.commit()
        }
    }

    private fun isReferrerDataUpdated(): Boolean {
        return preferences?.getBoolean(StoreUseCase.REFERRER_UPDATED_KEY, false) ?: false
    }

    companion object {
        private const val RUSTORE_REFERRER_KEY = "rustore_referrer_data"
    }
}