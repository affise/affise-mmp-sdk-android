package com.affise.attribution.modules.store

import com.affise.attribution.referrer.AffiseReferrerData

interface StoreApi {
    fun getInstallReferrerData(): AffiseReferrerData?

    fun isInstallReferrerUpdated(): Boolean = false

    fun startInstallReferrerRetrieve(onFinished: (() -> Unit)? = null)
}