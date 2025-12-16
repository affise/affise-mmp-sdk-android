package com.affise.attribution.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class SystemAppChecker(
    private val app: Application
) {

    /**
     * Check app if it system app
     *
     * @return is app is system
     */
    fun isPreinstallApp() = isSystemAppByFLAG() || isSystemPreloaded()

    /**
     * Check if application is preloaded.
     *
     * @return `true` if package is preloaded.
     */
    @Suppress("DEPRECATION")
    private fun isSystemPreloaded() = try {
        app.packageManager.getApplicationInfo(app.packageName, 0).let {
            it.sourceDir.startsWith("/system/app/") || it.sourceDir.startsWith("/system/priv-app/")
        }
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

    /**
     * Check if application is installed in the device's system image
     *.
     * @return is app is system
     */
    @Suppress("DEPRECATION")
    private fun isSystemAppByFLAG() = try {
        val applicationFlags = app.packageManager.getApplicationInfo(app.packageName, 0).flags
        val systemFlags = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP

        // Check if FLAG_SYSTEM or FLAG_UPDATED_SYSTEM_APP are set.
        (applicationFlags and systemFlags) != 0
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

    /**
     * Check if app has flag in SystemProperties
     */
    @SuppressLint("PrivateApi")
    fun getSystemProperty(key: String) = try {
        Class.forName("android.os.SystemProperties")
            .getDeclaredMethod("get", String::class.java)
            .invoke(null, key)
            ?.toString()
    } catch (throwable: Throwable) {
        null
    }
}