package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.FloatPropertyProvider
import com.affise.attribution.usecase.ScreenUseCase

class DensityProvider(
    private val useCase: ScreenUseCase,
) : FloatPropertyProvider() {

    override val order: Float = 42.3f
    override val key: ProviderType = ProviderType.DENSITY

    override fun provide(): Float = useCase.getDensity()
}
