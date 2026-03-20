package com.affise.app.ui.factories

import com.affise.app.ui.affiseSettings
import com.affise.app.ui.utils.toNormalCase
import com.affise.attribution.Affise
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.referrer.ReferrerKey

fun apiFactory(output: ((String) -> Unit)): List<Pair<String, () -> Unit>> = listOf(
    Pair("Debug: validate") {
        output("validate: requesting...")
        Affise.Debug.validate {
            output("validate: $it")
        }
    },
    Pair("Debug: version") {
        output("version: ${Affise.Debug.version()}")
    },
//        Pair("Debug: network") {
//            Affise.Debug.network { req, res ->
//
//            }
//        },
    Pair("addPushToken") {
        Affise.addPushToken("new_token")
    },
    Pair("setOfflineModeEnabled") {
        Affise.setOfflineModeEnabled(!Affise.isOfflineModeEnabled())
        affiseSettings.isOfflineModeEnabled.value = Affise.isOfflineModeEnabled()
        output("isOfflineModeEnabled: ${affiseSettings.isOfflineModeEnabled.value}")
    },
    Pair("isOfflineModeEnabled") {
        val value = Affise.isOfflineModeEnabled()
        output("isOfflineModeEnabled: $value")
    },
    Pair("setBackgroundTrackingEnabled") {
        Affise.setBackgroundTrackingEnabled(!Affise.isBackgroundTrackingEnabled())
        affiseSettings.isBackgroundTrackingEnabled.value = Affise.isBackgroundTrackingEnabled()
        output("isBackgroundTrackingEnabled: ${affiseSettings.isBackgroundTrackingEnabled.value}")
    },
    Pair("isBackgroundTrackingEnabled") {
        val value = Affise.isBackgroundTrackingEnabled()
        output("isBackgroundTrackingEnabled: $value")
    },
    Pair("setTrackingEnabled") {
        Affise.setTrackingEnabled(!Affise.isTrackingEnabled())
        affiseSettings.isBackgroundTrackingEnabled.value = Affise.isTrackingEnabled()
        output("isTrackingEnabled: ${affiseSettings.isBackgroundTrackingEnabled.value}")
    },
    Pair("isTrackingEnabled") {
        val value = Affise.isTrackingEnabled()
        output("isTrackingEnabled: $value")
    },
    Pair("getReferrer") {
        Affise.getReferrerUrl {
            output("getReferrer: $it")
        }
    },
    Pair("getReferrerValue") {
        Affise.getReferrerUrlValue(ReferrerKey.AD_ID) {
            output("getReferrerValue: $it")
        }
    },
    Pair("getDeferredDeeplink") {
        Affise.getDeferredDeeplink {
            output("getDeferredDeeplink: $it")
        }
    },
    Pair("getDeferredDeeplinkValue") {
        Affise.getDeferredDeeplinkValue(ReferrerKey.AD_ID) {
            output("getDeferredDeeplinkValue: $it")
        }
    },
    Pair("getStatus") {
        output("getStatus: requesting...")
        Affise.Module.getStatus(AffiseModules.Status) { keyValue ->
            output(
                "getStatus: ${
                    keyValue.joinToString("\n") { "key = ${it.key}; value = ${it.value}" }
                }"
            )
        }
    },
    Pair("getModulesInstalled") {
        val value = Affise.Module.getModulesInstalled()
        output("getModulesInstalled: [ ${value.joinToString(", ")}]")
    },
    Pair("getRandomUserId") {
        val value = Affise.getRandomUserId()
        output("getRandomUserId: $value")
    },
    Pair("getRandomDeviceId") {
        val value = Affise.getRandomDeviceId()
        output("getRandomDeviceId: $value")
    },
    Pair("getProviders") {
        val providerType = ProviderType.GAID_ADID
        val value = Affise.getProviders()
        output("getProviders: ${providerType.provider} = ${value[providerType] as? String}")
    },
    Pair("isFirstRun") {
        val value = Affise.isFirstRun()
        output("isFirstRun: $value")
    },
)
    .map { Pair(it.first.toNormalCase().uppercase(), it.second) }
