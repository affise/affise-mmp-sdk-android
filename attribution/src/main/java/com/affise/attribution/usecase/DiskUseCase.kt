package com.affise.attribution.usecase

interface DiskUseCase {

    fun getTotalDisk(): Long

    fun getFreeDisk(): Long
}
