package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.BooleanPropertyProvider
import com.affise.attribution.usecase.DeviceUseCase

/**
 * Provider for property [ProviderType.IS_ROOTED]
 *
 * @property deviceUseCase to retrieve devise state
 */
internal class IsRootedProvider(
    private val deviceUseCase: DeviceUseCase
): BooleanPropertyProvider() {

    override val order: Float = 66.0f
    override val key: ProviderType = ProviderType.IS_ROOTED

    override fun provide(): Boolean? = deviceUseCase.isRooted()
}