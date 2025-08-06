package com.affise.attribution.module.tiktok.usecase

import com.affise.attribution.events.custom.UserCustomEvent
import com.affise.attribution.module.tiktok.utils.toMap
import org.json.JSONObject

internal class TikTokUseCaseImpl : TikTokUseCase {
    override fun sendEvent(eventName: String?, properties: JSONObject?, eventId: String?) {
        eventName?: return

        UserCustomEvent(eventName, category = CATEGORY, userData = eventId)
            .internalAddRawParameters(properties?.toMap())
            .send()
    }

    companion object {
        private const val CATEGORY = "tiktok"
    }
}