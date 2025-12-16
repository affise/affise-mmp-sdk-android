package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase

/**
 * Provider for parameter [ProviderType.INSTALLED_TIME]
 *
 * @property useCase to retrieve first install time
 */
class InstalledTimeProvider(
    private val useCase: PackageInfoUseCase,
) : LongPropertyProvider() {

    override val order: Float = 6.0f
    override val key: ProviderType = ProviderType.INSTALLED_TIME

    override fun provide(): Long? = useCase.getFirstInstallTime()
}