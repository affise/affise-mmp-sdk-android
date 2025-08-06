package com.affise.attribution.module.tiktok

import com.affise.attribution.module.tiktok.usecase.TikTokUseCase
import com.affise.attribution.module.tiktok.usecase.TikTokUseCaseImpl
import com.affise.attribution.modules.AffiseModule
import com.affise.attribution.modules.tiktok.AffiseTikTokApi
import org.json.JSONObject


internal class TikTokModule : AffiseModule(), AffiseTikTokApi {

    override val version: String = BuildConfig.AFFISE_VERSION

    private val useCase: TikTokUseCase by lazy {
        TikTokUseCaseImpl()
    }

    override fun start() {
    }

    override fun sendEvent(eventName: String?, properties: JSONObject?, eventId: String?) {
        useCase.sendEvent(eventName, properties, eventId)
    }
}