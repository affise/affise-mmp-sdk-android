package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.StoreUseCase

/**
 * Provider for parameter [ProviderType.STORE]
 *
 * @property app to retrieve package manager
 * @property logsManager for error logging
 * @property systemAppChecker to check system for preinstall
 */
class StoreProvider(
    private val useCase: StoreUseCase,
) : StringPropertyProvider() {

    override val order: Float = 5.0f
    override val key: ProviderType = ProviderType.STORE

    override fun provide(): String = useCase.getStore()
}