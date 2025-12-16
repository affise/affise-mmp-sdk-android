package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.AppUUIDs

/**
 * Provider for parameter [ProviderType.AFFISE_ALT_DEVICE_ID]
 *
 * @property useCase to retrieve affise alt device id
 */
internal class AffiseAltDeviceIdProvider(
    private val useCase: AppUUIDs
) : StringPropertyProvider() {
    override val order: Float = 28.0f
    override val key: ProviderType = ProviderType.AFFISE_ALT_DEVICE_ID

    override fun provide(): String? = useCase.getAffiseAltDeviseId()
}