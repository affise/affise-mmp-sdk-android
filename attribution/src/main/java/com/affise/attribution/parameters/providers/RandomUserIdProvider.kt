package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.StringPropertyProvider
import com.affise.attribution.usecase.AppUUIDs

/**
 * Provider for parameter [ProviderType.RANDOM_USER_ID]
 *
 * @property useCase to retrieve random user id
 */
class RandomUserIdProvider(
    private val useCase: AppUUIDs
) : StringPropertyProvider() {

    override val order: Float = 49.0f
    override val key: ProviderType = ProviderType.RANDOM_USER_ID

    override fun provide(): String? = useCase.getRandomUserId()
}