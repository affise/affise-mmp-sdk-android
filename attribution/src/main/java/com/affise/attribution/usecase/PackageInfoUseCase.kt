package com.affise.attribution.usecase

interface PackageInfoUseCase {

    fun getFirstInstallTime(): Long?

    fun getAppVersion(): String?

    fun getAppVersionRaw(): String?

    /**
     * Get initiating app package name
     */
    fun getInitiatingPackageName(): String?
}