package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.MCCODE]
 *
 * @property useCase to fetch resources from
 */
class MCCProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 36.0f
    override val key: ProviderType = ProviderType.MCCODE

    override fun provide(): String = useCase.getMCC()
}