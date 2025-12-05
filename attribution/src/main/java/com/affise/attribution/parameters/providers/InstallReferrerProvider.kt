package com.affise.attribution.parameters.providers

import android.app.Application
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.StoreInstallReferrerUseCase

/**
 * Provider for parameter [ProviderType.REFERRER]
 *
 * @property app to get partner_key in assets
 * @property storeInstallReferrerUseCase usecase to retrieve install begin time
 */
class InstallReferrerProvider(
    private val app: Application,
    private val storeInstallReferrerUseCase: StoreInstallReferrerUseCase
) : StringPropertyProvider() {

    override val order: Float = 34.0f
    override val key: ProviderType = ProviderType.REFERRER

    override fun provide(): String? {
        return storeInstallReferrerUseCase.getPartnerKeyOrReferrer()
    }
}