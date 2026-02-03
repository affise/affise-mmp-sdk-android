package com.affise.attribution.module.huawei.referrer

import android.app.Application
import android.content.SharedPreferences
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.modules.store.StoreApi
import com.affise.attribution.referrer.AffiseReferrerData
import com.huawei.hms.ads.installreferrer.api.InstallReferrerClient
import com.huawei.hms.ads.installreferrer.api.InstallReferrerStateListener
import com.huawei.hms.ads.installreferrer.api.ReferrerDetails


internal class HuaweiReferrerUseCase(
    private val preferences: SharedPreferences?,
    private val app: Application?,
    private val logsManager: LogsManager?,
    private val toStringConverter: HuaweiReferrerDataToStringConverter,
    private val toHuaweiReferrerDataConverter: StringToHuaweiReferrerDataConverter,
) : StoreApi {

    private var client: InstallReferrerClient? = null

    init {
        app?.let {
            // Create InstallReferrerClient
            client = InstallReferrerClient
                .newBuilder(app)
                .build()
        }
    }

    override fun startInstallReferrerRetrieve(onFinished: (() -> Unit)?) {
        client?.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        client?.let {
                            try {
                                processReferrerDetails(it.installReferrer)
                            } catch (_: Exception) {
                            }
                        }
                    }

                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED,
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> client?.endConnection()
                }

                onFinished?.invoke()
            }

            override fun onInstallReferrerServiceDisconnected() {
                client?.startConnection(this)
            }
        }) ?: onFinished?.invoke()
    }

    private fun processReferrerDetails(data: ReferrerDetails?) {
        data ?: return

        //Generate referrer data
        HuaweiReferrerData(
            installReferrer = data.installReferrer,
            referrerClickTimestampSeconds = data.referrerClickTimestampSeconds,
            installBeginTimestampServerSeconds = data.installBeginTimestampSeconds,
        )
            .let(toStringConverter::convert)
            .let(::storeToSharedPreferences)
    }

    private fun getHuaweiInstallReferrerData(): HuaweiReferrerData? {
        return preferences
            ?.getString(HUAWEI_REFERRER_KEY, null)
            ?.let(toHuaweiReferrerDataConverter::convert)
    }

    override fun getInstallReferrerData(): AffiseReferrerData? {
        val huawei = getHuaweiInstallReferrerData()
        return AffiseReferrerData(
            installReferrer = huawei?.installReferrer ?: "",
            referrerClickTimestampSeconds = 0,
            installBeginTimestampSeconds = 0,
            referrerClickTimestampServerSeconds = huawei?.referrerClickTimestampSeconds ?: 0,
            installBeginTimestampServerSeconds = huawei?.installBeginTimestampServerSeconds ?: 0,
            installVersion = "",
            googlePlayInstantParam = false
        )
    }

    private fun storeToSharedPreferences(s: String) {
        preferences?.edit()?.let {
            it.putString(HUAWEI_REFERRER_KEY, s)
            it.commit()
        }
    }

    companion object {
        private const val HUAWEI_REFERRER_KEY = "huawei_referrer_data"
    }
}