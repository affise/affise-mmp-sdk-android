package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.APP_VERSION]
 * App version number (Android)
 *
 * @property useCase to retrieve app version name
 */
class AppVersionProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 3.0f
    override val key: ProviderType = ProviderType.APP_VERSION

    override fun provide(): String? = useCase.getAppVersion()
}