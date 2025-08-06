package com.affise.attribution.module.tiktok.utils

import org.json.JSONArray
import org.json.JSONObject

internal fun JSONObject.toMap(): Map<String, Any?> =
    keys().asSequence().associateWith { key -> toValue(get(key)) }

internal fun JSONArray.toList(): List<Any?> = (0 until length()).map { index -> toValue(get(index)) }

private fun toValue(element: Any?) = try {
    when (element) {
        JSONObject.NULL -> null
        is JSONObject -> element.toMap()
        is JSONArray -> element.toList()
        is com.tiktok.appevents.contents.TTContentsEventConstants.Currency -> element.name
        else -> element
    }
} catch (_: Exception) {
    "$element"
}




