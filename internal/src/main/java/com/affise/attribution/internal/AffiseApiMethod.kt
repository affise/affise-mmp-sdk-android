package com.affise.attribution.internal

enum class AffiseApiMethod(val method: String) {
    INIT("init"),
    SEND_EVENT("send_event"),
    SEND_EVENT_NOW("send_event_now"),
    ADD_PUSH_TOKEN("add_push_token"),
    REGISTER_WEB_VIEW("register_web_view"),
    UNREGISTER_WEB_VIEW("unregister_web_view"),
    SET_SECRET_ID("set_secret_id"),
    SET_AUTO_CATCHING_TYPES("set_auto_catching_types"),
    SET_OFFLINE_MODE_ENABLED("set_offline_mode_enabled"),
    IS_OFFLINE_MODE_ENABLED("is_offline_mode_enabled"),
    SET_BACKGROUND_TRACKING_ENABLED("set_background_tracking_enabled"),
    IS_BACKGROUND_TRACKING_ENABLED("is_background_tracking_enabled"),
    SET_TRACKING_ENABLED("set_tracking_enabled"),
    IS_TRACKING_ENABLED("is_tracking_enabled"),
    FORGET("forget"),
    SET_ENABLED_METRICS("set_enabled_metrics"),
    CRASH_APPLICATION("crash_application"),
    GET_RANDOM_USER_ID("get_random_user_id"),
    GET_RANDOM_DEVICE_ID("get_random_device_id"),
    GET_PROVIDERS("get_providers"),
    IS_FIRST_RUN("is_first_run"),

    // callback methods
    ON_INIT_SUCCESS_HANDLER("on_init_success_handler"),
    ON_INIT_ERROR_HANDLER("on_init_error_handler"),
    GET_REFERRER_URL_CALLBACK("get_referrer_url_callback"),
    GET_REFERRER_URL_VALUE_CALLBACK("get_referrer_url_value_callback"),
    GET_DEFERRED_DEEPLINK_CALLBACK("get_deferred_deeplink_callback"),
    GET_DEFERRED_DEEPLINK_VALUE_CALLBACK("get_deferred_deeplink_value_callback"),
    REGISTER_DEEPLINK_CALLBACK("register_deeplink_callback"),

    // debug
    DEBUG_VALIDATE_CALLBACK("debug_validate_callback"),
    DEBUG_NETWORK_CALLBACK("debug_network_callback"),
    DEBUG_VERSION_NATIVE("debug_version_native"),

    // affise builder
    AFFISE_BUILDER("affise_builder"),

    ////////////////////////////////////////
    // modules
    ////////////////////////////////////////
    GET_MODULES_INSTALLED("get_modules_installed"),
    GET_STATUS_CALLBACK("get_status_callback"),
    // AppsFlyer Module
    MODULE_APPSFLYER_LOG_EVENT("module_appsflyer_log_event"),
    // Link Module
    MODULE_LINK_LINK_RESOLVE_CALLBACK("module_link_link_resolve_callback"),
    // Subscription Module
    MODULE_SUBS_FETCH_PRODUCTS_CALLBACK("module_subs_fetch_products_callback"),
    MODULE_SUBS_PURCHASE_CALLBACK("module_subs_purchase_callback"),
    // TikTok Module
    MODULE_TIKTOK_EVENT("module_tiktok_event"),
    ////////////////////////////////////////
    // modules
    ////////////////////////////////////////
    ;

    companion object {
        @JvmStatic
        fun from(name: String?): AffiseApiMethod? = name?.let { method ->
            AffiseApiMethod.values().firstOrNull { it.method == method }
        }
    }
}