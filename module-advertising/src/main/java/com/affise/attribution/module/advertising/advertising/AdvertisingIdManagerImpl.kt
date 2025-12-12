package com.affise.attribution.module.advertising.advertising

import android.app.Application
import android.content.Context
import com.affise.attribution.executors.ExecutorServiceProvider
import com.affise.attribution.logs.LogsManager
import com.google.android.gms.ads.identifier.AdvertisingIdClient


/**
 * Manager to track google advertising id changes
 *
 * @property executorProvider An Executor that provides methods to manage termination and methods
 * that can produce a Future for tracking progress of one or more asynchronous tasks.
 * @property logsManager for save errors
 */
internal class AdvertisingIdManagerImpl(
    private val executorProvider: ExecutorServiceProvider,
    private val logsManager: LogsManager?
) : AdvertisingIdManager {

    /**
     * Google Advertising ID
     */
    private var advertisingId: String? = null
    private var adPersonalization: Boolean = false
    private var advertisingIdInfo: AdvertisingIdClient.Info? = null

    /**
     * Init Advertising IdManager
     */
    override fun init(app: Application?) {
        app ?: return

        executorProvider.provideExecutorService().execute {
            try {
                advertisingIdInfo = getAdvertisingIdInfo(app)
                //Get Google Advertising ID
                advertisingId = getGoogleAdvertisingId()
                adPersonalization = checkGoogleAdPersonalization()
            } catch (throwable: Throwable) {
                //Log error
                logsManager?.addDeviceError(throwable)
            }
        }
    }

    /**
     * Get cashed Google Advertising ID
     * @return Google Advertising ID
     */
    override fun getAdvertisingId() = advertisingId

    /**
     * Get cashed Google Advertising Personalization
     * @return Google Advertising Personalization
     */
    override fun getAdPersonalization() = adPersonalization

    private fun getAdvertisingIdInfo(context: Context) = try {
        AdvertisingIdClient.getAdvertisingIdInfo(context)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
//        logsManager?.addDeviceError(throwable)
        null
    }

    /**
     * Get Google Advertising ID
     */
    private fun getGoogleAdvertisingId(): String? {
        //Get Google Advertising ID
        val advertisingId = advertisingIdInfo?.id
        if (advertisingId.isNullOrBlank()) return null
        return advertisingId
    }

    private fun checkGoogleAdPersonalization(): Boolean {
        advertisingId?.let {
            return !it.startsWith("00000000-", false)
        }
        return false
    }
}