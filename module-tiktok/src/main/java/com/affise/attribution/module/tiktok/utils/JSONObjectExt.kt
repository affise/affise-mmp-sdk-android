package com.affise.attribution.module.tiktok.utils

import org.json.JSONArray
import org.json.JSONObject

internal fun JSONObject.toMap(): Map<String, Any?> =
    keys().asSequence().associateWith { key -> toValue(get(key)) }

internal fun JSONArray.toList(): List<Any?> = (0 until length()).map { index -> toValue(get(index)) }

private const val tikTokCurrencyClass = "com.tiktok.appevents.contents.TTContentsEventConstants\$Currency"

private fun toValue(element: Any?): Any? {
    return try {
        if (element?.javaClass?.name == tikTokCurrencyClass) {
            val ordinal = (element as? Enum<*>)?.ordinal
            return TikTokCurrency.fromOrdinal(ordinal)?.name
        }
        when (element) {
            JSONObject.NULL -> null
            is JSONObject -> element.toMap()
            is JSONArray -> element.toList()
            else -> element
        }
    } catch (_: Exception) {
        "$element"
    }
}




