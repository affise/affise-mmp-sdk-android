package com.affise.attribution.init

import com.affise.attribution.events.autoCatchingClick.AutoCatchingType
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.network.CloudConfig
import com.affise.attribution.settings.AffiseConfig
import com.affise.attribution.settings.OnInitErrorHandler
import com.affise.attribution.settings.OnInitSuccessHandler

/**
 * Model that holds properties required on library init
 */
data class AffiseInitProperties @JvmOverloads constructor(
    val affiseAppId: String?,
    val isProduction: Boolean = true,
    val partParamName: String? = null,
    val partParamNameToken: String? = null,
    val appToken: String? = null,
    val secretKey: String? = null,
    val autoCatchingClickEvents: List<AutoCatchingType>? = null,
    val enabledMetrics: Boolean = false,
    val domain: String? = CloudConfig.defaultDomain,
    val onInitSuccessHandler: OnInitSuccessHandler? = null,
    val onInitErrorHandler: OnInitErrorHandler? = null,
    val configValues: Map<AffiseConfig, Any> = emptyMap(),
    val disableModules: List<AffiseModules> = emptyList()
) {

    init {
        CloudConfig.setupDomain(domain)
    }

    constructor(
        affiseAppId: String,
        secretKey: String,
        isProduction: Boolean,
    ) : this(
        affiseAppId = affiseAppId.trim(),
        secretKey = secretKey.trim(),
        isProduction = isProduction,
        partParamName = null,
        partParamNameToken = null,
        appToken = null,
        autoCatchingClickEvents = null,
        enabledMetrics = false,
        domain = CloudConfig.defaultDomain
    )

    constructor(
        affiseAppId: String,
        secretKey: String
    ) : this(
        affiseAppId = affiseAppId.trim(),
        secretKey = secretKey.trim(),
        isProduction = true,
    )
}
