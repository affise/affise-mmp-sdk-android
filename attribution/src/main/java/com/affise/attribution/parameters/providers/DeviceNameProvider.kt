package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.DEVICE_NAME]
 *
 * @property useCase to retrieve global settings
 */
class DeviceNameProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 41.0f
    override val key: ProviderType = ProviderType.DEVICE_NAME

    override fun provide(): String? = useCase.getDeviceName()
}