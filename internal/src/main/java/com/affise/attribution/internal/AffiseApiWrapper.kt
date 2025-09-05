package com.affise.attribution.internal

import android.app.Activity
import android.app.Application
import com.affise.attribution.Affise
import com.affise.attribution.internal.builder.AffiseBuilder
import com.affise.attribution.internal.callback.InternalResult
import com.affise.attribution.internal.callback.OnAffiseCallback
import com.affise.attribution.internal.events.EventFactory
import com.affise.attribution.internal.ext.addSettings
import com.affise.attribution.internal.ext.getAppIdAndSecretKey
import com.affise.attribution.internal.ext.isValid
import com.affise.attribution.internal.ext.opt
import com.affise.attribution.internal.ext.toListOfMap
import com.affise.attribution.internal.platform.InternalCrossPlatform
import com.affise.attribution.internal.data.DataMapper
import com.affise.attribution.internal.data.DataName
import com.affise.attribution.internal.ext.getMap
import com.affise.attribution.internal.ext.getString
import com.affise.attribution.internal.ext.toAffiseModules
import com.affise.attribution.internal.utils.jsonStringToMap
import com.affise.attribution.internal.utils.toJSONObject
import com.affise.attribution.referrer.toReferrerKey
import com.affise.attribution.settings.PushTokenService
import org.json.JSONObject


class AffiseApiWrapper(
    private val app: Application?,
) {

    companion object {
        private const val UUID = "callback_uuid"
        private const val TAG = "callback_tag"
    }

    var activity: Activity? = null

    private val factory: EventFactory = EventFactory()
    private val affiseBuilder: AffiseBuilder = AffiseBuilder()

    private var callback: ((AffiseApiMethod, Map<String, Any?>) -> Unit)? = null

    fun setCallback(callback: OnAffiseCallback? = null) {
        this.callback = { name, data ->
            val result = callback?.handleCallback(name.method, JSONObject(data).toString())
            jsonStringToMap(result)
        }
    }

    fun setCallback(callback: ((String, Map<String, Any?>) -> Unit)? = null) {
        this.callback = { name, data ->
            callback?.invoke(name.method, data)
        }
    }

    fun handleDeeplink(uri: String?) {
        InternalCrossPlatform.deeplink(uri ?: "")
    }

    fun react() {
        InternalCrossPlatform.react()
    }

    fun flutter() {
        InternalCrossPlatform.flutter()
    }

    fun unity() {
        InternalCrossPlatform.unity()
    }

    fun call(api: AffiseApiMethod?, map: Map<String, *>, result: InternalResult) {
        when (api) {
            AffiseApiMethod.INIT ->
                callInit(api, map, result)

            AffiseApiMethod.IS_INITIALIZED ->
                callIsInitialized(api, map, result)

            AffiseApiMethod.SEND_EVENT ->
                callSendEvent(api, map, result)

            AffiseApiMethod.SEND_EVENT_NOW ->
                callSendEventNow(api, map, result)

            AffiseApiMethod.ADD_PUSH_TOKEN ->
                callAddPushToken(api, map, result)

//            AffiseApiMethod.REGISTER_WEB_VIEW ->
//                callRegisterWebView(api, map, result)

//            AffiseApiMethod.UNREGISTER_WEB_VIEW ->
//                callUnregisterWebView(api, map, result)

            AffiseApiMethod.SET_SECRET_ID ->
                callSetSecretId(api, map, result)

//            AffiseApiMethod.SET_AUTO_CATCHING_TYPES ->
//                callSetAutoCatchingTypes(api, map, result)

            AffiseApiMethod.SET_OFFLINE_MODE_ENABLED ->
                callSetOfflineModeEnabled(api, map, result)

            AffiseApiMethod.IS_OFFLINE_MODE_ENABLED ->
                callIsOfflineModeEnabled(api, map, result)

            AffiseApiMethod.SET_BACKGROUND_TRACKING_ENABLED ->
                callSetBackgroundTrackingEnabled(api, map, result)

            AffiseApiMethod.IS_BACKGROUND_TRACKING_ENABLED ->
                callIsBackgroundTrackingEnabled(api, map, result)

            AffiseApiMethod.SET_TRACKING_ENABLED ->
                callSetTrackingEnabled(api, map, result)

            AffiseApiMethod.IS_TRACKING_ENABLED ->
                callIsTrackingEnabled(api, map, result)

            AffiseApiMethod.FORGET ->
                callForget(api, map, result)

//            AffiseApiMethod.SET_ENABLED_METRICS ->
//                callSetEnabledMetrics(api, map, result)

            AffiseApiMethod.CRASH_APPLICATION ->
                callCrashApplication(api, map, result)

            AffiseApiMethod.GET_RANDOM_USER_ID ->
                callGetRandomUserId(api, map, result)

            AffiseApiMethod.GET_RANDOM_DEVICE_ID ->
                callGetRandomDeviceId(api, map, result)

            AffiseApiMethod.GET_PROVIDERS ->
                callGetProviders(api, map, result)

            AffiseApiMethod.IS_FIRST_RUN ->
                callIsFirstRun(api, map, result)

            AffiseApiMethod.GET_REFERRER_URL_CALLBACK ->
                callGetReferrerUrl(api, map, result)

            AffiseApiMethod.GET_REFERRER_URL_VALUE_CALLBACK ->
                callGetReferrerUrlValue(api, map, result)

            AffiseApiMethod.GET_DEFERRED_DEEPLINK_CALLBACK ->
                callGetDeferredDeeplink(api, map, result)

            AffiseApiMethod.GET_DEFERRED_DEEPLINK_VALUE_CALLBACK ->
                callGetDeferredDeeplinkValue(api, map, result)

            AffiseApiMethod.REGISTER_DEEPLINK_CALLBACK ->
                callRegisterDeeplinkCallback(api, map, result)

            AffiseApiMethod.DEBUG_VALIDATE_CALLBACK ->
                callDebugValidateCallback(api, map, result)

            AffiseApiMethod.DEBUG_NETWORK_CALLBACK ->
                callDebugNetworkCallback(api, map, result)

            AffiseApiMethod.DEBUG_VERSION_NATIVE ->
                callDebugVersionNative(api, map, result)

            AffiseApiMethod.AFFISE_BUILDER ->
                callBuilder(api, map, result)

            ////////////////////////////////////////
            // modules
            ////////////////////////////////////////
            AffiseApiMethod.GET_MODULES_INSTALLED ->
                callGetModulesInstalled(api, map, result)

            AffiseApiMethod.GET_STATUS_CALLBACK ->
                callGetStatusCallback(api, map, result)

            // AppsFlyer Module
            AffiseApiMethod.MODULE_APPSFLYER_LOG_EVENT ->
                callModuleAppsFlyerLogEvent(api, map, result)

            // Link Module
            AffiseApiMethod.MODULE_LINK_LINK_RESOLVE_CALLBACK ->
                callModuleLinkLinkResolveCallback(api, map, result)

            // Subscription Module
            AffiseApiMethod.MODULE_SUBS_FETCH_PRODUCTS_CALLBACK ->
                callModuleSubsFetchProductsCallback(api, map, result)

            AffiseApiMethod.MODULE_SUBS_PURCHASE_CALLBACK ->
                callModuleSubsPurchaseCallback(api, map, result)

            // TikTok Module
            AffiseApiMethod.MODULE_TIKTOK_EVENT ->
                callModuleTikTokEvent(api, map, result)
            ////////////////////////////////////////
            // modules
            ////////////////////////////////////////

            else -> result.notImplemented()
        }
    }

    private fun callInit(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        if (app == null) {
            result.error("api [${api.method}]: no application context")
            return
        }

        val properties = map.opt<Map<*, *>>(api)

        if (properties == null) {
            result.error("api [${api.method}]: no valid AffiseInitProperties")
            return
        }

        if (!properties.isValid()) {
            result.error("api [${api.method}]: affiseAppId or secretKey is not set")
            return
        }

        val (affiseAppId, secretKey) = properties.getAppIdAndSecretKey()

        Affise
            .settings(
                affiseAppId = affiseAppId,
                secretKey = secretKey
            )
            .addSettings(properties)
            .setOnInitSuccess {
                val data = mapOf<String, Any?>()
                callback?.invoke(AffiseApiMethod.ON_INIT_SUCCESS_HANDLER, data)
            }
            .setOnInitError{
                val data = mapOf<String, Any?>(
                    AffiseApiMethod.ON_INIT_ERROR_HANDLER.method to it.localizedMessage,
                )
                callback?.invoke(AffiseApiMethod.ON_INIT_ERROR_HANDLER, data)
            }
            .start(app)

        InternalCrossPlatform.start()
        result.success(null)
    }

    private fun callIsInitialized(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.isInitialized().let {
            result.success(it)
        }
    }

    private fun callSendEvent(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val data = map.opt<Map<*, *>>(api)
        if (data == null) {
            result.error("api [${api.method}]: value not set")
            return
        }

        val event = factory.create(data)
        if (event != null) {
            event.send()
            result.success(null)
        } else {
            result.error("api [${api.method}]: not valid event ${map.toJSONObject()}")
        }
    }

    private fun callSendEventNow(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val data = map.opt<Map<*, *>>(api)
        if (data == null) {
            result.error("api [${api.method}]: value not set")
            return
        }

        val event = factory.create(data)
        if (event == null) {
            result.error("api [${api.method}]: not valid event ${map.toJSONObject()}")
            return
        }

        val uuid = map.opt<String>(UUID)
        event.sendNow({
            val dataMap = mapOf<String, Any?>(
                UUID to uuid,
                TAG to DataName.SUCCESS,
            )
            callback?.invoke(api, dataMap)
        }) { response ->
            val dataMap = mapOf<String, Any?>(
                UUID to uuid,
                TAG to DataName.FAILED,
                api.method to mapOf(
                    DataName.CODE to response.code,
                    DataName.MESSAGE to response.message,
                    DataName.BODY to response.body,
                ),
            )
            callback?.invoke(api, dataMap)
        }

        result.success(null)
    }

    private fun callAddPushToken(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val apiData = map.opt<Map<String, Any?>>(api)

        val pushToken = apiData?.opt<String>(DataName.PUSH_TOKEN)
        val pushTokenService = PushTokenService.from(apiData?.opt<String>(DataName.PUSH_TOKEN_SERVICE))
            ?: PushTokenService.FIREBASE

        if (pushToken.isNullOrBlank()) {
            result.error("api [${api.method}]: push token value not set")
        } else {
            Affise.addPushToken(pushToken, pushTokenService)
            result.success(null)
        }
    }

    private fun callRegisterWebView(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
//        Affise.registerWebView()
//        result.success(null)
        result.notImplemented()
    }

    private fun callUnregisterWebView(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.unregisterWebView()
        result.success(null)
    }

    private fun callSetSecretId(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val secretKey = map.opt<String>(api)
        if (secretKey.isNullOrBlank()) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.setSecretId(secretKey)
            result.success(null)
        }
    }

//    private fun callSetAutoCatchingTypes(
//        api: AffiseApiMethod,
//        map: Map<String, *>,
//        result: InternalResult
//    ) {
//        val list = map.opt<List<*>>(api)
//        val types = list?.mapNotNull {
//            it?.toString()?.toAutoCatchingType()
//        }
//        Affise.setAutoCatchingTypes(types)
//        result.success(null)
//    }

    private fun callSetOfflineModeEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val enabled = map.opt<Boolean>(api)
        if (enabled == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.setOfflineModeEnabled(enabled)
            result.success(null)
        }
    }

    private fun callIsOfflineModeEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.isOfflineModeEnabled().let {
            result.success(it)
        }
    }

    private fun callSetBackgroundTrackingEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val enabled = map.opt<Boolean>(api)
        if (enabled == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.setBackgroundTrackingEnabled(enabled)
            result.success(null)
        }
    }

    private fun callIsBackgroundTrackingEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.isBackgroundTrackingEnabled().let {
            result.success(it)
        }
    }

    private fun callSetTrackingEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val enabled = map.opt<Boolean>(api)
        if (enabled == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.setTrackingEnabled(enabled)
            result.success(null)
        }
    }

    private fun callIsTrackingEnabled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.isTrackingEnabled().let {
            result.success(it)
        }
    }

    private fun callForget(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val userData = map.opt<String>(api)
        if (userData.isNullOrBlank()) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.forget(userData)
            result.success(null)
        }
    }

//    private fun callSetEnabledMetrics(
//        api: AffiseApiMethod,
//        map: Map<String, *>,
//        result: InternalResult
//    ) {
//        val enabled = map.opt<Boolean>(api)
//        if (enabled == null) {
//            result.error("api [${api.method}]: value not set")
//        } else {
//            Affise.setEnabledMetrics(enabled)
//            result.success(null)
//        }
//    }

    private fun callCrashApplication(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.crashApplication()
        result.success(null)
    }

    private fun callGetRandomUserId(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.getRandomUserId())
    }

    private fun callGetRandomDeviceId(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.getRandomDeviceId())
    }

    private fun callGetProviders(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.getProviders().entries.associate { it.key.provider to it.value })
    }

    private fun callIsFirstRun(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.isFirstRun())
    }

    private fun callGetReferrerUrl(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        Affise.getReferrerUrl { referrer ->
            val data = mapOf<String, Any?>(
                UUID to uuid,
                api.method to referrer,
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    private fun callGetReferrerUrlValue(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        val key = map.opt<String>(api)?.toReferrerKey()

        if (key == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.getReferrerUrlValue(key) { referrer ->
                val data = mapOf<String, Any?>(
                    UUID to uuid,
                    api.method to referrer,
                )
                callback?.invoke(api, data)
            }
            result.success(null)
        }
    }

    private fun callGetDeferredDeeplink(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        Affise.getDeferredDeeplink { referrer ->
            val data = mapOf<String, Any?>(
                UUID to uuid,
                api.method to referrer,
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    private fun callGetDeferredDeeplinkValue(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        val key = map.opt<String>(api)?.toReferrerKey()

        if (key == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.getDeferredDeeplinkValue(key) { referrer ->
                val data = mapOf<String, Any?>(
                    UUID to uuid,
                    api.method to referrer,
                )
                callback?.invoke(api, data)
            }
            result.success(null)
        }
    }

    private fun callRegisterDeeplinkCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.registerDeeplinkCallback { value ->
            val data = mapOf<String, Any?>(
                api.method to DataMapper.fromDeeplinkValue(value)
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    private fun callDebugValidateCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        Affise.Debug.validate {
            val data = mapOf<String, Any?>(
                UUID to uuid,
                api.method to it.status,
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    private fun callDebugNetworkCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        Affise.Debug.network { request, response ->
            val data = mapOf<String, Any?>(
                api.method to mapOf(
                    DataName.REQUEST to DataMapper.fromRequest(request),
                    DataName.RESPONSE to DataMapper.fromResponse(response),
                ),
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    private fun callDebugVersionNative(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.Debug.version())
    }

    private fun callBuilder(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        affiseBuilder.call(api, map, result)
    }

    ////////////////////////////////////////
    // Modules
    ////////////////////////////////////////
    private fun callGetModulesInstalled(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        result.success(Affise.Module.getModulesInstalled().map { it.name })
    }

    private fun callGetStatusCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        val module = map.opt<String>(api)?.toAffiseModules()
        if (module == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.Module.getStatus(module) {
                val data = mapOf<String, Any?>(
                    UUID to uuid,
                    api.method to it.toListOfMap(),
                )
                callback?.invoke(api, data)
            }
            result.success(null)
        }
    }

    // AppsFlyer Module
    private fun callModuleAppsFlyerLogEvent(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val data = map.opt<Map<*, *>>(api)

        if (data == null) {
            result.error("api [${api.method}]: no valid data")
            return
        }

        val eventName = data.getString(DataName.EVENT_NAME)
        @Suppress("UNCHECKED_CAST")
        val eventValues: Map<String, Any>? = data.getMap(DataName.EVENT_VALUES) as? Map<String, Any>

        if (eventName == null) {
            result.error("api [${api.method}]: eventName not set")
            return
        }

        Affise.Module.AppsFlyer.logEvent(eventName, eventValues ?: emptyMap())
        result.success(null)
    }

    // Link Module
    private fun callModuleLinkLinkResolveCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        val url = map.opt<String>(api)
        if (url == null) {
            result.error("api [${api.method}]: value not set")
        } else {
            Affise.Module.Link.resolve(url) {
                val data = mapOf<String, Any?>(
                    UUID to uuid,
                    api.method to it,
                )
                callback?.invoke(api, data)
            }
            result.success(null)
        }
    }

    // Subscription Module
    private fun callModuleSubsFetchProductsCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)
        val ids = map.opt<List<String>>(api)
        if (ids.isNullOrEmpty()) {
            result.error("api [${api.method}]: value not set")
            return
        }

        Affise.Module.Subscription.fetchProducts(ids) {
            val data = mapOf<String, Any?>(
                UUID to uuid,
                api.method to DataMapper.fromFetchProductsResult(it),
            )
            callback?.invoke(api, data)
        }

        result.success(null)
    }

    // Subscription Module
    private fun callModuleSubsPurchaseCallback(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val uuid = map.opt<String>(UUID)

        if (activity == null) {
            result.error("api [${api.method}]: activity not set")
            return
        }

        val apiData = map.opt<Map<String, Any?>>(api)

        val product = DataMapper.toAffiseProduct(apiData?.opt<Map<String, *>>(DataName.PRODUCT))
        if (product == null) {
            result.error("api [${api.method}]: product not set")
            return
        }

        val type = DataMapper.toAffiseProductType(apiData?.opt<String>(DataName.TYPE))

        Affise.Module.Subscription.purchase(
            activity = activity!!,
            product = product,
            type = type
        ) {
            val data = mapOf<String, Any?>(
                UUID to uuid,
                api.method to DataMapper.fromPurchaseResult(it),
            )
            callback?.invoke(api, data)
        }
        result.success(null)
    }

    // TikTok Module
    private fun callModuleTikTokEvent(
        api: AffiseApiMethod,
        map: Map<String, *>,
        result: InternalResult
    ) {
        val data = map.opt<Map<*, *>>(api)

        if (data == null) {
            result.error("api [${api.method}]: no valid data")
            return
        }

        val eventId = data.getString(DataName.EVENT_ID)
        val eventName = data.getString(DataName.EVENT_NAME)
        val properties: JSONObject? = data.getMap(DataName.EVENT_VALUES)?.toJSONObject()

        Affise.Module.TikTok.sendEvent(eventName, properties, eventId)
        result.success(null)
    }
    ////////////////////////////////////////
    // modules
    ////////////////////////////////////////
}