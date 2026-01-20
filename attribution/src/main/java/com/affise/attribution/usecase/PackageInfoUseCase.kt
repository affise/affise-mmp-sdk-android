package com.affise.attribution.usecase

interface PackageInfoUseCase {

    fun getPackageAppName(): String?

    fun getFirstInstallTime(): Long?

    fun getAppVersion(): String?

    fun getAppVersionRaw(): String?

    /**
     * Get initiating app package name
     */
    fun getInitiatingPackageName(): String?

    fun getMCC(): String

    fun getMNC(): String

    fun getDeviceName(): String?

    fun getDeviceType(): String?
}