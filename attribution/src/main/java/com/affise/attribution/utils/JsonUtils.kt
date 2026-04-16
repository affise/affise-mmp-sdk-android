package com.affise.attribution.utils

import org.json.JSONArray
import org.json.JSONObject


internal fun String.toJSONObject(): JSONObject? {
    if (this.isEmpty()) return null
    try {
        return JSONObject(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

internal fun <T> JSONArray.map(transform: (Any) -> T): List<T> {
    return (0 until length()).map { transform(get(it)) }
}

internal fun <K,V> JSONObject.map(transform: (Any, Any?) -> Pair<K, V>?): Map<K,V> {
    return keys().asSequence().mapNotNull { key ->
        transform(key, opt(key))
    }.toMap()
}