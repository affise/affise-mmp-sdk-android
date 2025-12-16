package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * App version number (Android) [ProviderType.APP_VERSION_RAW]
 *
 * @property useCase to retrieve app version
 */
class AppVersionRawProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 4.0f
    override val key: ProviderType = ProviderType.APP_VERSION_RAW

    override fun provide(): String? = useCase.getAppVersionRaw()
}