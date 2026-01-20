package com.affise.attribution.usecase

import android.app.Application
import android.app.UiModeManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Configuration
import android.os.Build
import android.provider.Settings
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

    override fun getPackageAppName(): String? {
        return app.packageName
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

    override fun getMCC(): String {
        return app.resources.configuration.mcc.toString()
    }

    override fun getMNC(): String {
        return app.resources.configuration.mnc.toString()
    }

    override fun getDeviceName(): String? {
        return Settings.Global
            .getString(app.contentResolver, "device_name")
    }

    override fun getDeviceType(): String? {
        return detectDeviceTypeByUIMode()
            ?: if (isTablet()) "tablet" else "smartphone"
    }

    /**
     * Check configuration if is tablet
     * @return is tablet or not
     */
    private fun isTablet(): Boolean {
        val size = (app.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
        return (size >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

    /**
     * Check mode typ if is television or car
     * @return mode type name
     */
    private fun detectDeviceTypeByUIMode(): String? {
        val manager = app.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager ?: return null

        return when (manager.currentModeType) {
            Configuration.UI_MODE_TYPE_TELEVISION -> "tv"
            Configuration.UI_MODE_TYPE_CAR -> "car"
            else -> null
        }
    }
}