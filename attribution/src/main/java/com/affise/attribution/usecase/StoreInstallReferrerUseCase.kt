package com.affise.attribution.usecase

import android.app.Application
import com.affise.attribution.modules.AffiseModuleManager
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.modules.huawei.AffiseHuaweiApi
import com.affise.attribution.modules.rustore.AffiseRuStoreApi
import com.affise.attribution.modules.store.StoreApi
import com.affise.attribution.referrer.AffiseReferrerData
import com.affise.attribution.referrer.OnReferrerCallback
import com.affise.attribution.referrer.ReferrerKey
import com.affise.attribution.referrer.getPartnerKey
import com.affise.attribution.utils.getReferrerValue

class StoreInstallReferrerUseCase(
    private val app: Application,
    private val storeUseCase: StoreUseCase,
    private val googleInstallReferrerUseCase: GoogleInstallReferrerUseCase,
) {
    private var onReferrerSetupFinished: (() -> Unit)? = null
    private var onFinished: (() -> Unit)? = null

    private var callbacks: MutableMap<OnReferrerCallback, ReferrerKey?> = mutableMapOf()

    private var storeModule: StoreApi? = null

    fun onReferrerSetupFinished(onFinished: (() -> Unit)?): StoreInstallReferrerUseCase {
        this.onFinished = onFinished
        return this
    }

    fun init(moduleManager: AffiseModuleManager) {
        storeModule = when (storeUseCase.getStore()) {
            StoreUseCase.HUAWEI -> moduleManager.api<AffiseHuaweiApi>(AffiseModules.Huawei)
            StoreUseCase.RUSTORE -> moduleManager.api<AffiseRuStoreApi>(AffiseModules.RuStore)
            StoreUseCase.GOOGLE -> googleInstallReferrerUseCase
            else -> googleInstallReferrerUseCase
        }

        startInstallReferrerRetrieve {
            handleReferrerCallback()

            onFinished?.invoke()
            onReferrerSetupFinished?.invoke()
        }
    }

    private fun startInstallReferrerRetrieve(callback: (() -> Unit)?) {
        storeModule
            ?.startInstallReferrerRetrieve(callback)
            ?: callback?.invoke()
    }

    /**
     * Get install referrer
     * @return install referrer
     */
    fun getInstallReferrerData(): AffiseReferrerData? {
        return storeModule?.getInstallReferrerData()
    }

    private fun getStoreInstallReferrer(): String? {
        return getInstallReferrerData()?.installReferrer
    }

    /**
     * Get referrer value
     */
    fun getReferrer(callback: OnReferrerCallback?) {
        addCallback(callback)
        handleReferrer()
    }

    /**
     * Get referrer uri value by key
     */
    fun getReferrerValue(key: ReferrerKey, callback: OnReferrerCallback?) {
        addCallback(callback, key)
        handleReferrer()
    }

    private fun handleReferrer() {
        getInstallReferrerData()?.let {
            handleReferrerCallback()
            return
        }

        onReferrerSetupFinished = {
            handleReferrerCallback()
        }
    }

    @Synchronized
    private fun addCallback(callback: OnReferrerCallback?, key: ReferrerKey? = null) {
        callback?.let {
            callbacks[it] = key
        }
    }

    @Synchronized
    private fun handleReferrerCallback() {
        val referrer = getPartnerKeyOrReferrer()

        if (referrer.isNullOrEmpty()) return

        val iterator = callbacks.entries.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            val result = if (item.value != null) {
                referrer.getReferrerValue(item.value)
            } else {
                referrer
            }
            item.key.handleReferrer(result)
            iterator.remove()
        }
    }

    fun getPartnerKeyOrReferrer(): String? {
        //Check referrer in partner_key
        val partnerKey = app.getPartnerKey()

        //if partner_key is empty or null use installReferrer
        return if (partnerKey.isNullOrEmpty()) {
            getStoreInstallReferrer()
        } else {
            partnerKey
        }
    }
}