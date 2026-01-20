package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.StoreInstallReferrerUseCase

/**
 * Provider for parameter [ProviderType.REFERRER]
 *
 * @property useCase usecase to retrieve install begin time
 */
class InstallReferrerProvider(
    private val useCase: StoreInstallReferrerUseCase
) : StringPropertyProvider() {

    override val order: Float = 34.0f
    override val key: ProviderType = ProviderType.REFERRER

    override fun provide(): String? {
        return useCase.getPartnerKeyOrReferrer()
    }
}