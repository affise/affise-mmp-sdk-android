package com.affise.attribution.usecase

import android.app.Application

internal class ScreenUseCaseImpl(
    private val app: Application,
) : ScreenUseCase {

    override fun getScreenWidth(): Long = app.resources.displayMetrics.widthPixels.toLong()

    override fun getScreenHeight(): Long = app.resources.displayMetrics.heightPixels.toLong()

    override fun getDensity(): Float = app.resources.displayMetrics.density
}
