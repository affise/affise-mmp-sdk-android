package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.ProcessInfoUseCase

class CpuCoresProvider(
    private val useCase: ProcessInfoUseCase,
) : LongPropertyProvider() {

    override val order: Float = 42.4f
    override val key: ProviderType = ProviderType.CPU_CORES

    override fun provide(): Long = useCase.getCpuCores()
}
