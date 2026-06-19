package com.affise.attribution.module.advertising.parameters

import com.affise.attribution.module.advertising.advertising.AdvertisingIdManager
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.BooleanPropertyProvider


class ApplicationTrackingEnabledProvider(
    private val advertisingIdManager: AdvertisingIdManager,
) : BooleanPropertyProvider() {

    override val order: Float = 31.56f
    override val key: ProviderType = ProviderType.APPLICATION_TRACKING_ENABLED

    override fun provide(): Boolean = advertisingIdManager.isAdvertiserTrackingEnabled()
}
