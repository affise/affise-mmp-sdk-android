package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.BooleanPropertyProvider
import com.affise.attribution.usecase.StoreInstallReferrerUseCase

/**
 * Provider for parameter [ProviderType.REFERRER_UPDATED]
 *
 * @property useCase usecase to retrieve install version from
 */
class InstallReferrerUpdatedProvider(
    private val useCase: StoreInstallReferrerUseCase,
) : BooleanPropertyProvider() {

    override val order: Float = 34.1f
    override val key: ProviderType = ProviderType.REFERRER_UPDATED

    override fun provide(): Boolean = useCase.isInstallReferrerUpdated()
}