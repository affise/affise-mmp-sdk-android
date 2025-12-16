package com.affise.attribution

import android.app.Application
import android.util.Log
import android.webkit.WebView
import com.affise.attribution.debug.AffiseDebug
import com.affise.attribution.debug.AffiseDebugApi
import com.affise.attribution.deeplink.OnDeeplinkCallback
import com.affise.attribution.errors.AffiseError
import com.affise.attribution.events.Event
import com.affise.attribution.events.OnSendFailedCallback
import com.affise.attribution.events.OnSendSuccessCallback
import com.affise.attribution.referrer.ReferrerKey
import com.affise.attribution.events.predefined.GDPREvent
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.internal.InternalEvent
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.modules.OnKeyValueCallback
import com.affise.attribution.modules.attribution.AffiseAttributionModule
import com.affise.attribution.parameters.providers.AffiseDeviceIdProvider
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.providers.RandomUserIdProvider
import com.affise.attribution.referrer.OnReferrerCallback
import com.affise.attribution.settings.AffiseSettings
import com.affise.attribution.settings.PushTokenService

/**
 * Entry point to initialise Affise Attribution library
 */
object Affise {

    /**
     * Api to communication with Affise
     */
    internal var api: AffiseApi? = null
        private set

    /**
     * Affise SDK settings builder
     *
     * To start SDK call .start(context)
     *
     * @param affiseAppId your app id
     * @param secretKey your SDK secretKey
     */
    @JvmStatic
    fun settings(affiseAppId: String, secretKey: String): AffiseSettings {
        return AffiseSettings(
            affiseAppId = affiseAppId,
            secretKey = secretKey
        )
    }

    /**
     * Init [AffiseComponent]
     */
    @JvmStatic
    @Synchronized
    internal fun start(initProperties: AffiseInitProperties, app: Application) {
        //Check creating AffiseComponent
        if (api == null) {
            //Create AffiseComponent
            api = AffiseComponent(app, initProperties)
        } else {
            api?.initProperties?.onInitErrorHandler?.handle(AffiseError.AlreadyInitialized())
            Log.w(this.javaClass.simpleName,AffiseError.ALREADY_INITIALIZED)
        }
    }

    @Deprecated("Use Affise.settings().setOnInitSuccess()")
    @JvmStatic
    fun isInitialized(): Boolean {
        return api?.isInitialized() ?: false
    }

    /**
     * Send events
     */
    @Deprecated("This method will be removed")
    @JvmStatic
    internal fun sendEvents() {
        api?.eventsManager?.sendEvents()
    }

    /**
     * Store and send [event]
     */
    @JvmStatic
    internal fun sendEvent(event: Event) {
        api?.storeEventUseCase?.storeEvent(event)
    }

    /**
     * Send now [event]
     */
    @JvmStatic
    internal fun sendEventNow(event: Event, success: OnSendSuccessCallback, failed: OnSendFailedCallback) {
        api?.immediateSendToServerUseCase?.sendNow(event, success, failed)
    }

    /**
     * Add [pushToken]
     */
    @JvmStatic
    fun addPushToken(pushToken: String, service: PushTokenService = PushTokenService.FIREBASE) {
        api?.pushTokenUseCase?.addPushToken(pushToken, service)
    }

    /**
     * Register [webView] to WebBridge
     */
    @JvmStatic
    fun registerWebView(webView: WebView) {
        api?.webBridgeManager?.registerWebView(webView)
    }

    /**
     * Unregister webView on WebBridge
     */
    @JvmStatic
    fun unregisterWebView() {
        api?.webBridgeManager?.unregisterWebView()
    }

    /**
     * Register [callback] for deeplink
     */
    @JvmStatic
    fun registerDeeplinkCallback(callback: OnDeeplinkCallback) {
        api?.deeplinkManager?.setDeeplinkCallback(callback)
    }

    /**
     * Set new SDK Secret Key
     */
    @JvmStatic
    fun setSecretId(secretKey: String) {
        api?.initPropertiesStorage?.updateSecretKey(secretKey)
    }

    /**
     * Send enabled autoCatching types
     */
//    @JvmStatic
//    fun setAutoCatchingTypes(types: List<AutoCatchingType>?) {
//        api?.autoCatchingClickProvider?.setTypes(types)
//    }

    /**
     * Sets offline mode to [enabled] state
     *
     * When enabled, no network activity should be triggered by this library,
     * but background work is not paused. When offline mode is enabled,
     * all recorded events should be sent
     */
    @JvmStatic
    fun setOfflineModeEnabled(enabled: Boolean) {
        api?.preferencesUseCase?.setOfflineModeEnabled(enabled)
    }

    /**
     * Returns current offline mode state
     */
    @JvmStatic
    fun isOfflineModeEnabled(): Boolean = api?.preferencesUseCase?.isOfflineModeEnabled() ?: false

    /**
     * Sets background tracking mode to [enabled] state
     *
     * When disabled, library should not generate any tracking events while in background
     */
    @JvmStatic
    fun setBackgroundTrackingEnabled(enabled: Boolean) {
        api?.preferencesUseCase?.setBackgroundTrackingEnabled(enabled)
    }

    /**
     * Returns current background tracking state
     */
    @JvmStatic
    fun isBackgroundTrackingEnabled(): Boolean =
        api?.preferencesUseCase?.isBackgroundTrackingEnabled() ?: false

    /**
     * Sets offline mode to [enabled] state
     *
     * When disabled, library should not generate any tracking events
     */
    @JvmStatic
    fun setTrackingEnabled(enabled: Boolean) {
        api?.preferencesUseCase?.setTrackingEnabled(enabled)
    }

    /**
     * Returns current tracking state
     */
    @JvmStatic
    fun isTrackingEnabled(): Boolean = api?.preferencesUseCase?.isTrackingEnabled() ?: false

    /**
     * Erases all user data from mobile and sends [GDPREvent]
     */
    @JvmStatic
    fun forget(userData: String) {
        api?.sendGDPREventUseCase?.registerForgetMeEvent(userData)
    }

    /**
     * Set [enabled] collect metrics
     *
     * When disabled, library should not generate any metrics events,
     * but will send the saved metrics events
     */
//    @JvmStatic
//    fun setEnabledMetrics(enabled: Boolean) {
//        api?.metricsManager?.setEnabledMetrics(enabled)
//    }

    @JvmStatic
    fun crashApplication() {
        api?.crashApplicationUseCase?.crash()
    }

    /**
     * Get referrer
     */
    @Deprecated(
        message = "This method will be removed",
        replaceWith = ReplaceWith("Affise.getDeferredDeeplink(callback)")
    )
    @JvmStatic
    fun getReferrer(callback: OnReferrerCallback?) {
        getDeferredDeeplink(callback)
    }

    /**
     * Get referrer Value
     */
    @Deprecated(
        message = "This method will be removed",
        replaceWith = ReplaceWith("Affise.getDeferredDeeplinkValue(key,callback)")
    )
    @JvmStatic
    fun getReferrerValue(key: ReferrerKey, callback: OnReferrerCallback?) {
        getDeferredDeeplinkValue(key, callback)
    }

    /**
     * Get referrer
     */
    @JvmStatic
    fun getReferrerUrl(callback: OnReferrerCallback?) {
        api?.storeInstallReferrerUseCase?.getReferrer(callback)
    }

    /**
     * Get referrer Value
     */
    @JvmStatic
    fun getReferrerUrlValue(key: ReferrerKey, callback: OnReferrerCallback?) {
        api?.storeInstallReferrerUseCase?.getReferrerValue(key, callback)
    }

    /**
     * Get referrer on server
     */
    @Deprecated(
        message = "This method will be removed",
        replaceWith = ReplaceWith("Affise.getDeferredDeeplink(callback)")
    )
    @JvmStatic
    fun getReferrerOnServer(callback: OnReferrerCallback?) {
        getDeferredDeeplink(callback)
    }

    /**
     * Get referrer on server value
     */
    @Deprecated(
        message = "This method will be removed",
        replaceWith = ReplaceWith("Affise.getDeferredDeeplinkValue(key,callback)")
    )
    @JvmStatic
    fun getReferrerOnServerValue(key: ReferrerKey, callback: OnReferrerCallback?) {
        getDeferredDeeplinkValue(key, callback)
    }

    /**
     * Get deferred deeplink on server
     */
    @JvmStatic
    fun getDeferredDeeplink(callback: OnReferrerCallback?) {
        api?.retrieveReferrerOnServerUseCase?.getReferrerOnServer(callback)
    }

    /**
     * Get deferred deeplink value on server
     */
    @JvmStatic
    fun getDeferredDeeplinkValue(key: ReferrerKey, callback: OnReferrerCallback?) {
        api?.retrieveReferrerOnServerUseCase?.getReferrerOnServerValue(key, callback)
    }

    /**
     * Get module status
     */
    @Deprecated(
        message = "Method moved to Affise.Module",
        replaceWith = ReplaceWith("Affise.Module.getStatus(module, onComplete)")
    )
    @JvmStatic
    fun getStatus(module: AffiseModules, onComplete: OnKeyValueCallback) {
        Module.getStatus(module, onComplete)
    }

    /**
     * Get installed modules
     */
    @Deprecated(
        message = "Method moved to Affise.Module",
        replaceWith = ReplaceWith("Affise.Module.getModulesInstalled()")
    )
    @JvmStatic
    fun getModulesInstalled(): List<AffiseModules> {
        return Module.getModulesInstalled()
    }

    /**
     * Get random User Id
     */
    @JvmStatic
    fun getRandomUserId(): String? {
        if (api?.postBackModelFactory == null) {
            return AffiseError.UUID_NOT_INITIALIZED
        }
        return api?.postBackModelFactory?.getProvider<RandomUserIdProvider>()?.provide()
    }

    /**
     * Get random Device Id
     */
    @JvmStatic
    fun getRandomDeviceId(): String? {
        if (api?.postBackModelFactory == null) {
            return AffiseError.UUID_NOT_INITIALIZED
        }
        return api?.postBackModelFactory?.getProvider<AffiseDeviceIdProvider>()?.provide()
    }

    /**
     * Get providers map
     */
    @JvmStatic
    fun getProviders(): Map<ProviderType, Any?> {
        return api?.postBackModelFactory?.getProvidersMap() ?: emptyMap()
    }

    /**
     * Is it first run
     */
    @JvmStatic
    fun isFirstRun(): Boolean {
        return api?.firstAppOpenUseCase?.isFirstRun() ?: true
    }

    /**
     * Send internal event
     */
    @JvmStatic
    internal fun sendInternalEvent(event: InternalEvent) {
        api?.storeInternalEventUseCase?.storeInternalEvent(event)
    }

    @JvmField
    val Module: AffiseAttributionModule = AffiseAttributionModule()

    @JvmField
    val Debug: AffiseDebugApi = AffiseDebug()
}