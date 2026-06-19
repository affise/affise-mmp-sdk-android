package com.affise.attribution.module.advertising.advertising

import android.app.Application

/**
 * Manager to track google advertising id changes
 */
interface AdvertisingIdManager {
    /**
     * Called on [app] init
     */
    fun init(app: Application?)

    /**
     * Returns google advertising id if present
     */
    fun getAdvertisingId(): String?

    /**
     * Returns true if advertising id is set
     */
    fun getAdPersonalization(): Boolean

    /**
     * Returns true if advertiser tracking is enabled for google advertising id.
     */
    fun isAdvertiserTrackingEnabled(): Boolean
}
