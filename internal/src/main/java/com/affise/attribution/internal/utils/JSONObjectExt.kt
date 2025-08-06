package com.affise.attribution.internal.utils

import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.toMap(): Map<String, Any?> =
    keys().asSequence().associateWith { key -> toValue(get(key)) }

fun JSONArray.toList(): List<Any?> = (0 until length()).map { index -> toValue(get(index)) }

private fun toValue(element: Any?) = when (element) {
    JSONObject.NULL -> null
    is JSONObject -> element.toMap()
    is JSONArray -> element.toList()
    else -> element
}

fun jsonStringToMap(data: String?): Map<String, Any?> {
    if (data.isNullOrEmpty()) return emptyMap()
    val json = try {
        JSONObject(data)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
    return json?.toMap() ?: emptyMap()
}

fun Map<*, *>?.toJSONObject(): JSONObject? {
    return this?.let {
        try {
            JSONObject(it)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}