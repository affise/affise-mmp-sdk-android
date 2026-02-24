package com.affise.attribution.modules.attribution

import com.affise.attribution.Affise
import com.affise.attribution.AffiseApi
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.modules.OnKeyValueCallback
import com.affise.attribution.modules.appsflyer.AffiseAppsFlyer
import com.affise.attribution.modules.appsflyer.AffiseModuleAppsFlyerApi
import com.affise.attribution.modules.link.AffiseLink
import com.affise.attribution.modules.link.AffiseModuleLinkApi
import com.affise.attribution.modules.subscription.AffiseModuleSubscriptionApi
import com.affise.attribution.modules.subscription.AffiseSubscription
import com.affise.attribution.modules.tiktok.AffiseModuleTikTokApi
import com.affise.attribution.modules.tiktok.AffiseTikTok

class AffiseAttributionModule(
    @JvmField val AppsFlyer: AffiseModuleAppsFlyerApi = AffiseAppsFlyer(),
    @JvmField val Link: AffiseModuleLinkApi = AffiseLink(),
    @JvmField val Subscription: AffiseModuleSubscriptionApi = AffiseSubscription(),
    @JvmField val TikTok: AffiseModuleTikTokApi = AffiseTikTok(),
) : AffiseAttributionModuleApi {

    private val api: AffiseApi?
        get() = Affise.api

    /**
     * Get module status
     */
    override fun getStatus(module: AffiseModules, onComplete: OnKeyValueCallback) {
        api?.moduleManager?.status(module, onComplete)
    }

    /**
     * Get installed modules
     */
    override fun getModulesInstalled(): List<AffiseModules> {
        return api?.moduleManager?.getModulesNames() ?: emptyList()
    }
}