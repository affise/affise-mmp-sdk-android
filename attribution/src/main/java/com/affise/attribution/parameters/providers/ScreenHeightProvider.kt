package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.ScreenUseCase

class ScreenHeightProvider(
    private val useCase: ScreenUseCase,
) : LongPropertyProvider() {

    override val order: Float = 42.2f
    override val key: ProviderType = ProviderType.SCREEN_HEIGHT

    override fun provide(): Long = useCase.getScreenHeight()
}
