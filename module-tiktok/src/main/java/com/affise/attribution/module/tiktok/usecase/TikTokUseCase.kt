package com.affise.attribution.module.tiktok.usecase

import org.json.JSONObject

internal interface TikTokUseCase {

    fun sendEvent(eventName: String?, properties: JSONObject?, eventId: String?)
}