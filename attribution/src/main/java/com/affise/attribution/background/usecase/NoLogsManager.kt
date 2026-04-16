package com.affise.attribution.background.usecase

import com.affise.attribution.logs.LogsManager

class NoLogsManager: LogsManager {
    override fun addNetworkError(throwable: Throwable) {
    }

    override fun addDeviceError(throwable: Throwable) {
    }

    override fun addUserError(throwable: Throwable) {
    }

    override fun addSdkError(throwable: Throwable) {
    }

    override fun addDeviceError(message: String) {
    }
}