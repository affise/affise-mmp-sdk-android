package com.affise.attribution.module.rustore

import android.content.SharedPreferences
import com.affise.attribution.module.rustore.referrer.RuStoreReferrerDataToStringConverter
import com.affise.attribution.module.rustore.referrer.RuStoreReferrerUseCase
import com.affise.attribution.module.rustore.referrer.StringToRuStoreReferrerDataConverter
import com.affise.attribution.modules.AffiseModule
import com.affise.attribution.modules.rustore.AffiseRuStoreApi
import com.affise.attribution.referrer.AffiseReferrerData


internal class RuStoreModule : AffiseModule(), AffiseRuStoreApi {

    override val version: String = BuildConfig.AFFISE_VERSION

    private var ruStoreReferrerUseCase: RuStoreReferrerUseCase? = null

    override fun start() {
        ruStoreReferrerUseCase = RuStoreReferrerUseCase(
            get<SharedPreferences>(),
            application,
            logsManager,
            RuStoreReferrerDataToStringConverter(),
            StringToRuStoreReferrerDataConverter(logsManager)
        )
    }

    override fun getInstallReferrerData(): AffiseReferrerData? {
        return ruStoreReferrerUseCase?.getInstallReferrerData()
    }

    override fun isInstallReferrerUpdated(): Boolean {
        return ruStoreReferrerUseCase?.isInstallReferrerUpdated() ?: false
    }

    override fun startInstallReferrerRetrieve(onFinished: (() -> Unit)?) {
        ruStoreReferrerUseCase?.startInstallReferrerRetrieve(onFinished)
    }
}