package com.affise.app.ui.screen.predefined

import com.affise.attribution.events.Event
import com.affise.attribution.events.parameters.Predefined
import com.affise.attribution.events.parameters.PredefinedFloat
import com.affise.attribution.events.parameters.PredefinedLong
import com.affise.attribution.events.parameters.PredefinedString
import org.json.JSONException
import org.json.JSONObject


val allPredefined: List<Predefined> = init()

private fun init(): List<Predefined> = arrayOf(
    PredefinedFloat.values(),
    PredefinedLong.values(),
    PredefinedString.values()
).flatten().toList()

fun Predefined.nameOfPredefined(): String {
    return "${this::class.simpleName}.${this}"
}

fun Predefined.typeOfPredefined(): String {
    return "${this::class.simpleName}"
}

fun Predefined.toPredefineData(data: Any): Any? {
    return when (this) {
        is PredefinedFloat -> data.toString().toFloatOrNull()
        is PredefinedLong -> data.toString().toLongOrNull()
        is PredefinedString -> "$data"
        else -> null
    }
}

fun Event.applyPredefines(predefinedData: List<PredefinedData>) {
    for (data in predefinedData) {
        val typedData = data.predefined.toPredefineData(data.data) ?: continue

        when (data.predefined) {
            is PredefinedFloat -> this.addPredefinedParameter(data.predefined, typedData as Float)
            is PredefinedLong -> this.addPredefinedParameter(data.predefined, typedData as Long)
            is PredefinedString -> this.addPredefinedParameter(data.predefined, typedData as String)
            else -> continue
        }
    }
}

fun stringToDataList(jsonString: String?): List<PredefinedData> {
    jsonString ?: return emptyList()
    try {
        val json = JSONObject(jsonString)
        return json.toPredefinedDataList()
    } catch (e: JSONException) {
        return emptyList()
    }
}

fun List<PredefinedData>.toJsonString(): String {
    return this.toJsonObject().toString(0)
}

private fun List<PredefinedData>.toJsonObject(): JSONObject {
    val json = JSONObject()

    for (data in this) {
        json.put(data.predefined.value(), data.data)
    }

    return json
}

private fun JSONObject.toPredefinedDataList(): List<PredefinedData> {
    val result: MutableList<PredefinedData> = mutableListOf()

    val iter: MutableIterator<String?> = this.keys()
    while (iter.hasNext()) {
        val key = iter.next()
        val predefined = getPredefineByKey(key) ?: continue

        try {
            val value: Any = this.opt(key) ?: continue
            result.add(PredefinedData(predefined, value))
        } catch (e: JSONException) {
            // Something went wrong!
        }
    }

    return result
}

private fun getPredefineByKey(key: String?): Predefined? {
    key ?: return null
    return PredefinedFloat.from(key) ?: PredefinedLong.from(key) ?: PredefinedString.from(key)
}