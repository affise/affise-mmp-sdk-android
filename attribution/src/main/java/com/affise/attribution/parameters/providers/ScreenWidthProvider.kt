package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.ScreenUseCase

class ScreenWidthProvider(
    private val useCase: ScreenUseCase,
) : LongPropertyProvider() {

    override val order: Float = 42.1f
    override val key: ProviderType = ProviderType.SCREEN_WIDTH

    override fun provide(): Long = useCase.getScreenWidth()
}
