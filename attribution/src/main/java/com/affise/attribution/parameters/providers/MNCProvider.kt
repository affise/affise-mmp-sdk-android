package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.MNCODE]
 *
 * @property useCase to fetch resources from
 */
class MNCProvider(
    private val useCase: PackageInfoUseCase,
) : StringPropertyProvider() {

    override val order: Float = 37.0f
    override val key: ProviderType = ProviderType.MNCODE

    override fun provide(): String = useCase.getMNC()
}