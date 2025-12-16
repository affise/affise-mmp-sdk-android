package com.affise.attribution.usecase

import android.app.Application
import android.content.pm.PackageInfo
import android.os.Build
import com.affise.attribution.logs.LogsManager

internal class PackageInfoUseCaseImpl(
    private val app: Application,
    private val logsManager: LogsManager,
) : PackageInfoUseCase {

    private val packageInfo: PackageInfo?
        get() = try {
            app.packageManager.getPackageInfo(app.packageName, 0)
        } catch (e: Exception) {

            //log error
            logsManager.addDeviceError(e)
            null
        }

    override fun getFirstInstallTime(): Long? {
        return packageInfo?.firstInstallTime
    }

    override fun getAppVersion(): String? {
        return packageInfo?.versionName
    }

    @Suppress("DEPRECATION")
    override fun getAppVersionRaw(): String? {
        return packageInfo?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.longVersionCode
            } else {
                it.versionCode
            }
        }?.toString()
    }

    /**
     * Get initiating app package name
     */
    @Suppress("DEPRECATION")
    override fun getInitiatingPackageName(): String? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                app.packageManager.getInstallSourceInfo(app.packageName).initiatingPackageName
            } else {
                app.packageManager.getInstallerPackageName(app.packageName)
            }
        } catch (throwable: Throwable) {
            //log error
            logsManager.addDeviceError(throwable)

            null
        }
    }
}