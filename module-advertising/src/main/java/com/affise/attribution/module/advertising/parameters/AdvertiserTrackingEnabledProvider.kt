package com.affise.attribution.module.advertising.parameters

import com.affise.attribution.module.advertising.advertising.AdvertisingIdManager
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.BooleanPropertyProvider

class AdvertiserTrackingEnabledProvider(
    private val advertisingIdManager: AdvertisingIdManager,
) : BooleanPropertyProvider() {

    override val order: Float = 31.55f
    override val key: ProviderType = ProviderType.ADVERTISER_TRACKING_ENABLED

    override fun provide(): Boolean = advertisingIdManager.isAdvertiserTrackingEnabled()
}
