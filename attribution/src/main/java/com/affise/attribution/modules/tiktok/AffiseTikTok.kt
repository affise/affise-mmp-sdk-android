package com.affise.attribution.modules.tiktok

import com.affise.attribution.modules.AffiseModuleApiWrapper
import com.affise.attribution.modules.AffiseModules
import org.json.JSONObject

internal class AffiseTikTok : AffiseModuleApiWrapper<AffiseTikTokApi>(AffiseModules.TikTok), AffiseModuleTikTokApi {
    /**
     * Module TikTok send event
     */
    override fun sendEvent(eventName: String?, properties: JSONObject?, eventId: String?) {
        moduleApi?.sendEvent(eventName, properties, eventId)
    }
}