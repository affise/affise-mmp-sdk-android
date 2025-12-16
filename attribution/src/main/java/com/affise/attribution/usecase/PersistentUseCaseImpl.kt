package com.affise.attribution.usecase

import com.affise.attribution.modules.AffiseModuleApi
import com.affise.attribution.modules.AffiseModuleManager
import com.affise.attribution.modules.AffiseModules
import com.affise.attribution.modules.advertising.AdvertisingApi
import com.affise.attribution.modules.androidid.AndroidIdApi
import com.affise.attribution.utils.isValidUUID
import com.affise.attribution.utils.toFakeUUID

internal class PersistentUseCaseImpl : PersistentUseCase {

    private var moduleManager: AffiseModuleManager? = null
    private var affDeviceId: String? = null

    override fun init(moduleManager: AffiseModuleManager) {
        this.moduleManager = moduleManager
    }

    override fun getAffDeviceId(): String? {
        if (!affDeviceId.isNullOrBlank()) return affDeviceId
        affDeviceId =  uuidFromAndroidId() ?: uuidFromAdvertisingId()
        return affDeviceId
    }

    private fun <T : AffiseModuleApi> getModule(module: AffiseModules): T? {
        @Suppress("UNCHECKED_CAST")
        return moduleManager?.getModule(module) as? T
    }

    private fun uuidFromAndroidId(): String? {
        val moduleAndroidId: AndroidIdApi? = getModule(AffiseModules.AndroidId)
        val androidId = moduleAndroidId?.getAndroidId()
            ?.lowercase()
            ?.toFakeUUID()
        if (androidId.isNullOrBlank()) return null
        if (!androidId.isValidUUID()) return null
        return androidId
    }

    private fun uuidFromAdvertisingId(): String? {
        val moduleAdvertising: AdvertisingApi? = getModule(AffiseModules.Advertising)
        val advertisingId = moduleAdvertising?.getAdvertisingId()
            ?.lowercase()
        if (advertisingId.isNullOrBlank()) return null
        if (!advertisingId.isValidUUID()) return null
        return advertisingId
    }
}