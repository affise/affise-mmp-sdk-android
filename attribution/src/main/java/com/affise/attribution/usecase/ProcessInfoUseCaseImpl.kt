package com.affise.attribution.usecase

internal class ProcessInfoUseCaseImpl : ProcessInfoUseCase {

    override fun getCpuCores(): Long = Runtime.getRuntime().availableProcessors().toLong()
}
