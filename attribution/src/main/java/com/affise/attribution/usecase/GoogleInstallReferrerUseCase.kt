package com.affise.attribution.usecase

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import android.net.Uri
import com.affise.attribution.converter.Converter
import com.affise.attribution.converter.StringToAffiseReferrerDataConverter
import com.affise.attribution.deeplink.DeeplinkManager
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.modules.store.StoreApi
import com.affise.attribution.referrer.AffiseReferrerData
import com.affise.attribution.referrer.AffiseReferrerDataToStringConverter
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails

class GoogleInstallReferrerUseCase(
    private val preferences: SharedPreferences,
    private val toStringConverter: AffiseReferrerDataToStringConverter,
    private val toAffiseReferrerDataConverter: StringToAffiseReferrerDataConverter,
    private val app: Application,
    private val deeplinkManager: DeeplinkManager,
    private val logsManager: LogsManager,
    private val installReferrerToDeeplinkUriConverter: Converter<String, Uri?>,
) : StoreApi {

    /**
     * Referrer client
     */
    private var referrerClient: InstallReferrerClient? = null

    private var installReferrerUpdated: String? = null

    override fun startInstallReferrerRetrieve(onFinished: (() -> Unit)?) {
        //Create referrer client
        referrerClient = InstallReferrerClient.newBuilder(app)
            .build()

        //Start connection
        referrerClient?.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        try {
                            val data = referrerClient?.installReferrer ?: return

                            //Processing referrer details
                            processReferrerDetails(data)
                            setReferrerUpdated(data)
                        } catch (throwable: Throwable) {
                            logsManager.addSdkError(
                                RuntimeException("Error read ReferrerClient")
                            )
                        }
                    }

                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                        referrerClient?.endConnection()
                    }

                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        // Connection couldn't be established.
                        referrerClient?.endConnection()
                    }
                }
                onFinished?.invoke()
            }

            override fun onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                referrerClient?.startConnection(this)
            }
        })
    }

    /**
     * Get install referrer
     * @return install referrer
     */
    override fun getInstallReferrerData(): AffiseReferrerData? = preferences
        .getString(REFERRER_KEY, null)
        ?.let(toAffiseReferrerDataConverter::convert)

    @Synchronized
    override fun isInstallReferrerUpdated(): Boolean {
        val result = isReferrerDataUpdated()
        if (result) {
            setReferrerDataUpdated(false)
        }
        return result
    }

    /**
     * Processing referrer details
     */
    private fun processReferrerDetails(data: ReferrerDetails) {
        if (!isDelayedDeeplinkProcessed()) {
            data.installReferrer
                ?.let(installReferrerToDeeplinkUriConverter::convert)
                ?.also {
                    deeplinkManager.handleDeeplink(it)
                }
            setDelayedDeeplinkProcessed()
        }

        //Generate referrer data
        AffiseReferrerData(
            installReferrer = data.installReferrer ?: "",
            referrerClickTimestampSeconds = data.referrerClickTimestampSeconds,
            installBeginTimestampSeconds = data.installBeginTimestampSeconds,
            referrerClickTimestampServerSeconds = data.referrerClickTimestampServerSeconds,
            installBeginTimestampServerSeconds = data.installBeginTimestampServerSeconds,
            installVersion = data.installVersion ?: "",
            googlePlayInstantParam = data.googlePlayInstantParam,
        )
            .let(toStringConverter::convert)
            .let(::storeToSharedPreferences)
    }

    private fun setReferrerUpdated(data: ReferrerDetails) {
        if (
            installReferrerUpdated != null &&
            installReferrerUpdated != data.installReferrer
        ) {
            setReferrerDataUpdated(true)
        }

        installReferrerUpdated = data.installReferrer
    }

    /**
     * Save referrer data
     */
    @SuppressLint("ApplySharedPref")
    private fun storeToSharedPreferences(s: String) {
        val e = preferences.edit()
        e.putString(REFERRER_KEY, s)
        e.commit()
    }

    private fun isDelayedDeeplinkProcessed(): Boolean =
        preferences.getBoolean(DELAYED_DEEPLINK_PROCESSED_KEY, false)

    @SuppressLint("ApplySharedPref")
    private fun setDelayedDeeplinkProcessed() {
        preferences.edit()
            .apply {
                putBoolean(DELAYED_DEEPLINK_PROCESSED_KEY, true)
            }
            .commit()
    }

    @SuppressLint("ApplySharedPref")
    private fun setReferrerDataUpdated(value: Boolean) {
        preferences.edit()
            .apply {
                putBoolean(StoreUseCase.REFERRER_UPDATED_KEY, value)
            }
            .commit()
    }

    private fun isReferrerDataUpdated(): Boolean {
        return preferences.getBoolean(StoreUseCase.REFERRER_UPDATED_KEY, false)
    }

    companion object {
        private const val REFERRER_KEY = "referrer_data"
        private const val DELAYED_DEEPLINK_PROCESSED_KEY = "DELAYED_DEEPLINK_PROCESSED_KEY"
    }
}