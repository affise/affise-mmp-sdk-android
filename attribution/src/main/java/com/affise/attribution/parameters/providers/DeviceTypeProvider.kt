package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.DEVICE_TYPE]
 *
 * @property useCase to retrieve system service and configuration
 */
class DeviceTypeProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 42.0f
    override val key: ProviderType = ProviderType.DEVICE_TYPE

    override fun provide() = useCase.getDeviceType()
}