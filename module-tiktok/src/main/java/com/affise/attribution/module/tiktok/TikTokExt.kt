package com.affise.attribution.module.tiktok

import com.affise.attribution.modules.tiktok.AffiseTikTokApi
import com.tiktok.appevents.base.TTBaseEvent


fun AffiseTikTokApi.sendEvent(event: TTBaseEvent) {
    this.sendEvent(
        eventName = event.eventName,
        properties = event.properties,
        eventId = event.eventId
    )
}