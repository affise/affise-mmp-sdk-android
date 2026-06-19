package com.affise.attribution.usecase

interface ScreenUseCase {

    fun getScreenWidth(): Long

    fun getScreenHeight(): Long

    fun getDensity(): Float
}
