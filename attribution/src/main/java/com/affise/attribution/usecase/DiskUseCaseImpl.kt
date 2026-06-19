package com.affise.attribution.usecase

import android.app.Application
import android.os.StatFs

internal class DiskUseCaseImpl(
    private val app: Application,
) : DiskUseCase {

    override fun getTotalDisk(): Long = StatFs(app.filesDir.absolutePath).totalBytes

    override fun getFreeDisk(): Long = StatFs(app.filesDir.absolutePath).availableBytes
}
