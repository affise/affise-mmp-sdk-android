package com.affise.attribution.modules.tiktok

import com.affise.attribution.modules.AffiseModuleApi
import org.json.JSONObject

interface AffiseTikTokApi : AffiseModuleApi {
    fun sendEvent(eventName: String?, properties: JSONObject?, eventId: String?)
}