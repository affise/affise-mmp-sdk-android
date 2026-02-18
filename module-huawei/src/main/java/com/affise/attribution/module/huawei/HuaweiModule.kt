package com.affise.attribution.module.huawei

import android.content.SharedPreferences
import com.affise.attribution.converter.StringToMD5Converter
import com.affise.attribution.executors.ExecutorServiceProviderImpl
import com.affise.attribution.module.huawei.oaid.OaidManager
import com.affise.attribution.module.huawei.oaid.OaidManagerImpl
import com.affise.attribution.module.huawei.parameters.OaidMD5Provider
import com.affise.attribution.module.huawei.parameters.OaidProvider
import com.affise.attribution.module.huawei.referrer.HuaweiReferrerDataToStringConverter
import com.affise.attribution.module.huawei.referrer.HuaweiReferrerUseCase
import com.affise.attribution.module.huawei.referrer.StringToHuaweiReferrerDataConverter
import com.affise.attribution.modules.AffiseModule
import com.affise.attribution.modules.huawei.AffiseHuaweiApi
import com.affise.attribution.parameters.base.PropertyProvider
import com.affise.attribution.referrer.AffiseReferrerData


internal class HuaweiModule : AffiseModule(), AffiseHuaweiApi {

    override val version: String = BuildConfig.AFFISE_VERSION

    private var huaweiReferrerUseCase: HuaweiReferrerUseCase? = null

    private val stringToMD5Converter: StringToMD5Converter? by lazy {
        get<StringToMD5Converter>()
    }

    private val oaidManager: OaidManager by lazy {
        OaidManagerImpl(
            logsManager,
            ExecutorServiceProviderImpl("OAID Worker"),
        )
    }

    private val oaidProvider: PropertyProvider<*>? by lazy {
        OaidProvider(oaidManager)
    }

    private val oaidMD5Provider: PropertyProvider<*> by lazy {
        OaidMD5Provider(oaidManager, stringToMD5Converter)
    }

    private var providers: List<PropertyProvider<*>> = emptyList()

    override fun start() {
        oaidManager.init(application)

        huaweiReferrerUseCase = HuaweiReferrerUseCase(
            get<SharedPreferences>(),
            application,
            logsManager,
            HuaweiReferrerDataToStringConverter(),
            StringToHuaweiReferrerDataConverter(logsManager)
        )

        providers = listOfNotNull(
            oaidProvider,
            oaidMD5Provider,
        )
    }

    override fun getInstallReferrerData(): AffiseReferrerData? {
        return huaweiReferrerUseCase?.getInstallReferrerData()
    }

    override fun isInstallReferrerUpdated(): Boolean {
        return huaweiReferrerUseCase?.isInstallReferrerUpdated() ?: false
    }

    override fun startInstallReferrerRetrieve(onFinished: (() -> Unit)?) {
        huaweiReferrerUseCase?.startInstallReferrerRetrieve(onFinished)
    }

    override fun providers(): List<PropertyProvider<*>> = providers
}