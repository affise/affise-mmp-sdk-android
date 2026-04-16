package com.affise.attribution.utils

import com.affise.attribution.events.autoCatchingClick.AutoCatchingType
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.settings.AffiseConfig
import org.json.JSONArray
import org.json.JSONObject


private const val AFFISE_APP_ID = "affise_app_id"
private const val SECRET_KEY = "secret_key"
private const val IS_PRODUCTION = "is_production"
private const val PART_PARAM_NAME = "part_param_name"
private const val PART_PARAM_NAME_TOKEN = "part_param_name_token"
private const val APP_TOKEN = "app_token"
private const val AUTO_CATCHING_CLICK_EVENTS = "auto_catching_click_events"
private const val ENABLED_METRICS = "enabled_metrics"
private const val DOMAIN = "domain"
private const val CONFIG_VALUES = "config_values"
private const val DISABLE_MODULES = "disable_modules"

internal fun AffiseInitProperties.toJSONObject(): JSONObject {
    return JSONObject().also { json ->
        json.put(AFFISE_APP_ID, this.affiseAppId)
        json.put(SECRET_KEY, this.secretKey)
        json.put(IS_PRODUCTION, this.isProduction)
        json.put(PART_PARAM_NAME, this.partParamName)
        json.put(PART_PARAM_NAME_TOKEN, this.partParamNameToken)
        json.put(APP_TOKEN, this.appToken)

        JSONArray().also { array ->
            this.autoCatchingClickEvents?.forEach {
                array.put(it.type)
            }

            json.put(AUTO_CATCHING_CLICK_EVENTS, array)
        }

        json.put(ENABLED_METRICS, this.enabledMetrics)
        json.put(DOMAIN, this.domain)

        JSONObject().also { configJson ->
            for ((key, value) in this.configValues) {
                configJson.put(key.key, value)
            }

            json.put(CONFIG_VALUES, configJson)
        }

        JSONArray().also { array ->
            this.disableModules.forEach {
                array.put(it.module)
            }

            json.put(DISABLE_MODULES, array)
        }
    }
}


internal fun JSONObject.toAffiseInitProperties(): AffiseInitProperties? {
    if (!this.has(AFFISE_APP_ID) || !this.has(SECRET_KEY)) {
        return null
    }

    val autoCatchingClickEvents = this.optJSONArray(AUTO_CATCHING_CLICK_EVENTS)?.map {
        AutoCatchingType.from(it.toString())
    }?.filterNotNull()

    val configValues: Map<AffiseConfig, Any> = this.optJSONObject(CONFIG_VALUES)?.map { key, value ->
        val mapKey = AffiseConfig.from(key.toString())
        if (mapKey == null || value == null) {
            return@map null
        }
        mapKey to value
    } ?: emptyMap()

    val disableModules = this.optJSONArray(DISABLE_MODULES)?.map {
        AffiseModules.from(it.toString())
    }?.filterNotNull() ?: emptyList()

    return AffiseInitProperties(
        affiseAppId = this.optString(AFFISE_APP_ID),
        secretKey = this.optString(SECRET_KEY),
        isProduction = this.optBoolean(IS_PRODUCTION, true),
        partParamName = this.optString(PART_PARAM_NAME),
        partParamNameToken = this.optString(PART_PARAM_NAME_TOKEN),
        appToken = this.optString(APP_TOKEN),
        autoCatchingClickEvents = autoCatchingClickEvents,
        enabledMetrics = this.optBoolean(ENABLED_METRICS, false),
        domain = this.optString(DOMAIN),
        configValues = configValues,
        disableModules = disableModules
    )
}
