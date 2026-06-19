package com.affise.attribution.module.advertising

import com.affise.attribution.converter.StringToMD5Converter
import com.affise.attribution.executors.ExecutorServiceProviderImpl
import com.affise.attribution.module.advertising.advertising.AdvertisingIdManager
import com.affise.attribution.module.advertising.advertising.AdvertisingIdManagerImpl
import com.affise.attribution.module.advertising.parameters.AdvertiserTrackingEnabledProvider
import com.affise.attribution.module.advertising.parameters.GoogleAdvertisingIdMd5Provider
import com.affise.attribution.module.advertising.parameters.GoogleAdvertisingIdProvider
import com.affise.attribution.module.advertising.parameters.GoogleAdvertisingPersonalizationProvider
import com.affise.attribution.module.advertising.parameters.ApplicationTrackingEnabledProvider
import com.affise.attribution.modules.AffiseModule
import com.affise.attribution.modules.advertising.AdvertisingApi
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.PropertyProvider
import com.affise.attribution.parameters.providers.EmptyStringProvider


class AdvertisingModule : AffiseModule(), AdvertisingApi {

    override val version: String = BuildConfig.AFFISE_VERSION

    private val stringToMD5Converter: StringToMD5Converter? by lazy {
        get<StringToMD5Converter>()
    }

    private val googleAdvertisingIdProvider: PropertyProvider<*> by lazy {
        GoogleAdvertisingIdProvider(advManager)
    }

    private val googleAdvertisingIdMd5Provider: PropertyProvider<*> by lazy {
        GoogleAdvertisingIdMd5Provider(advManager, stringToMD5Converter)
    }

    private val googleAdvertisingPersonalizationProvider: PropertyProvider<*> by lazy {
        GoogleAdvertisingPersonalizationProvider(advManager)
    }

    private val advertiserTrackingEnabledProvider: PropertyProvider<*> by lazy {
        AdvertiserTrackingEnabledProvider(advManager)
    }

    private val applicationTrackingEnabledProvider: PropertyProvider<*>? by lazy {
        ApplicationTrackingEnabledProvider(advManager)
    }

    private val advManager: AdvertisingIdManager by lazy {
        AdvertisingIdManagerImpl(
            ExecutorServiceProviderImpl("GAID Worker"),
            logsManager
        )
    }

    private var providers: List<PropertyProvider<*>> = emptyList()

    override fun start() {
        advManager.init(application)

        providers = listOfNotNull(
            googleAdvertisingIdProvider,
            googleAdvertisingIdMd5Provider,
            googleAdvertisingPersonalizationProvider,
            advertiserTrackingEnabledProvider,
            applicationTrackingEnabledProvider,
            EmptyStringProvider(ProviderType.ADID, 29.0f),
            EmptyStringProvider(ProviderType.ALTSTR_ADID, 31.7f),
            EmptyStringProvider(ProviderType.FIREOS_ADID, 31.8f),
            EmptyStringProvider(ProviderType.COLOROS_ADID, 31.9f),
        )
    }

    override fun providers(): List<PropertyProvider<*>> = providers

    override fun getAdvertisingId(): String? = advManager.getAdvertisingId()
}
