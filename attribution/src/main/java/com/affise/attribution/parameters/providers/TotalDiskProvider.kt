package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.DiskUseCase

class TotalDiskProvider(
    private val useCase: DiskUseCase,
) : LongPropertyProvider() {

    override val order: Float = 42.5f
    override val key: ProviderType = ProviderType.TOTAL_DISK

    override fun provide(): Long = useCase.getTotalDisk()
}
