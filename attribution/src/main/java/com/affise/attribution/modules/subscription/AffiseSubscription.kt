package com.affise.attribution.modules.subscription

import android.app.Activity
import com.affise.attribution.modules.AffiseModuleApiWrapper
import com.affise.attribution.modules.AffiseModules


internal class AffiseSubscription
    : AffiseModuleApiWrapper<AffiseSubscriptionApi>(AffiseModules.Subscription),
    AffiseModuleSubscriptionApi {

    /**
     * Subscription Module fetch products for purchase from [productsIds]
     * (billingclient wrapper)
     */
    override fun fetchProducts(
        productsIds: List<String>,
        callback: AffiseResultCallback<AffiseProductsResult>,
    ) {
        moduleApi
            ?.fetchProducts(productsIds, callback)
            ?: callback.handle(AffiseResult.Error(AffiseSubscriptionError.NotInitialized()))
    }

    /**
     * Subscription Module purchase product
     * (billingclient wrapper)
     */
    override fun purchase(
        activity: Activity,
        product: AffiseProduct,
        type: AffiseProductType?,
        callback: AffiseResultCallback<AffisePurchasedInfo>,
    ) {
        moduleApi
            ?.purchase(activity, product, type, callback)
            ?: callback.handle(AffiseResult.Error(AffiseSubscriptionError.NotInitialized()))
    }

    /**
     * Subscription Module purchase product
     * (billingclient wrapper)
     */
    override fun purchase(
        activity: Activity,
        productId: String,
        offerToken: String?,
        type: AffiseProductType?,
        callback: AffiseResultCallback<AffisePurchasedInfo>,
    ) {
        moduleApi
            ?.purchase(activity, productId, offerToken, type, callback)
            ?: callback.handle(AffiseResult.Error(AffiseSubscriptionError.NotInitialized()))
    }
}