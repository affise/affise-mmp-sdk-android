package com.affise.attribution.modules.attribution

import android.app.Activity
import com.affise.attribution.Affise
import com.affise.attribution.AffiseApi
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.modules.OnKeyValueCallback
import com.affise.attribution.modules.appsflyer.AffiseAppsFlyer
import com.affise.attribution.modules.appsflyer.AffiseModuleAppsFlyerApi
import com.affise.attribution.modules.link.AffiseLink
import com.affise.attribution.modules.link.AffiseLinkCallback
import com.affise.attribution.modules.link.AffiseModuleLinkApi
import com.affise.attribution.modules.subscription.AffiseModuleSubscriptionApi
import com.affise.attribution.modules.subscription.AffiseProduct
import com.affise.attribution.modules.subscription.AffiseProductType
import com.affise.attribution.modules.subscription.AffiseProductsResult
import com.affise.attribution.modules.subscription.AffisePurchasedInfo
import com.affise.attribution.modules.subscription.AffiseResultCallback
import com.affise.attribution.modules.subscription.AffiseSubscription
import com.affise.attribution.modules.tiktok.AffiseModuleTikTokApi
import com.affise.attribution.modules.tiktok.AffiseTikTok

class AffiseAttributionModule(
    @JvmField val AppsFlyer: AffiseModuleAppsFlyerApi = AffiseAppsFlyer(),
    @JvmField val Link: AffiseModuleLinkApi = AffiseLink(),
    @JvmField val Subscription: AffiseModuleSubscriptionApi = AffiseSubscription(),
    @JvmField val TikTok: AffiseModuleTikTokApi = AffiseTikTok(),
): AffiseAttributionModuleApi {

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
        return api?.moduleManager?.getModules() ?: emptyList()
    }

    /**
     * Module Link url Resolve
     */
    @Deprecated(
        message = "Method moved to Affise.Module.Link",
        replaceWith = ReplaceWith("Affise.Module.Link.resolve(url, callback)")
    )
    fun linkResolve(url: String, callback: AffiseLinkCallback) {
        Affise.Module.Link.resolve(url, callback)
    }

    /**
     * Get Subscription Module status
     * (billingclient wrapper)
     */
    @Deprecated(
        message = "Method moved to Affise.Module.Subscription",
        replaceWith = ReplaceWith("Affise.Module.Subscription.hasModule()")
    )
    fun hasSubscriptionModule(): Boolean {
        return Affise.Module.Subscription.hasModule()
    }

    /**
     * Subscription Module fetch products for purchase from [productsIds]
     * (billingclient wrapper)
     */
    @Deprecated(
        message = "Method moved to Affise.Module.Subscription",
        replaceWith = ReplaceWith("Affise.Module.Subscription.fetchProducts(productsIds, callback)")
    )
    fun fetchProducts(
        productsIds: List<String>, callback: AffiseResultCallback<AffiseProductsResult>,
    ) {
        Affise.Module.Subscription.fetchProducts(productsIds, callback)
    }

    /**
     * Subscription Module purchase product
     * (billingclient wrapper)
     */
    @Deprecated(
        message = "Method moved to Affise.Module.Subscription",
        replaceWith = ReplaceWith("Affise.Module.Subscription.purchase(activity, product, type, callback)")
    )
    fun purchase(
        activity: Activity,
        product: AffiseProduct,
        type: AffiseProductType? = null,
        callback: AffiseResultCallback<AffisePurchasedInfo>,
    ) {
        Affise.Module.Subscription.purchase(activity, product, type, callback)
    }

    /**
     * Subscription Module purchase product
     * (billingclient wrapper)
     */
    @Deprecated(
        message = "Method moved to Affise.Module.Subscription",
        replaceWith = ReplaceWith("Affise.Module.Subscription.purchase(activity, productId, offerToken, type, callback)")
    )
    fun purchase(
        activity: Activity,
        productId: String,
        offerToken: String? = null,
        type: AffiseProductType? = null,
        callback: AffiseResultCallback<AffisePurchasedInfo>,
    ) {
        Affise.Module.Subscription.purchase(activity, productId, offerToken, type, callback)
    }
}