package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.AFFISE_PKG_APP_NAME]
 *
 * @property context to retrieve package name from
 */
class AffisePackageAppNameProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 2.0f
    override val key: ProviderType = ProviderType.AFFISE_PKG_APP_NAME

    override fun provide(): String? = useCase.getPackageAppName()
}