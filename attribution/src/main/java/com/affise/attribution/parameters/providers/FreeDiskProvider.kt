package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.DiskUseCase

class FreeDiskProvider(
    private val useCase: DiskUseCase,
) : LongPropertyProvider() {

    override val order: Float = 42.6f
    override val key: ProviderType = ProviderType.FREE_DISK

    override fun provide(): Long = useCase.getFreeDisk()
}
