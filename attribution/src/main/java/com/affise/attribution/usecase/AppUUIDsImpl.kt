package com.affise.attribution.usecase

import android.content.SharedPreferences
import com.affise.attribution.converter.StringToMD5Converter
import com.affise.attribution.errors.AffiseError
import com.affise.attribution.utils.SignType
import com.affise.attribution.utils.generateUUID
import com.affise.attribution.utils.saveString
import com.affise.attribution.utils.sing
import com.affise.attribution.utils.toFakeUUID

internal class AppUUIDsImpl(
    private val preferences: SharedPreferences,
    private val packageInfoUseCase: PackageInfoUseCase,
    private val persistentUseCase: PersistentUseCase,
    private val md5Converter: StringToMD5Converter,
) : AppUUIDs {

    /**
     * Get devise id
     * @return devise id
     */
    @Synchronized
    override fun getAffiseDeviseId(): String? {
        val prefDeviseId = preferences.getString(AppUUIDs.AFF_DEVICE_ID, null)
        if (!prefDeviseId.isNullOrBlank()) {
            return prefDeviseId
        }

        val affDeviceId = persistentUseCase.getAffDeviceId()
            ?.sing(SignType.ANDROID_ID)

        if (!affDeviceId.isNullOrBlank()) {
            return affDeviceId
        }

        val genUUID = generateUUID().toString()
            .sing(SignType.RANDOM)

        preferences.saveString(AppUUIDs.AFF_DEVICE_ID, genUUID)
        return preferences.getString(AppUUIDs.AFF_DEVICE_ID, AffiseError.UUID_NO_VALID_METHOD)
    }

    /**
     * Get alt devise id
     * @return alt devise id
     */
    @Synchronized
    override fun getAffiseAltDeviseId(): String? {
        return preferences
            .getString(
                AppUUIDs.AFF_ALT_DEVICE_ID,
                AffiseError.UUID_NO_VALID_METHOD
            )
    }

    /**
     * Get random user id
     * @return random user id
     */
    @Synchronized
    override fun getRandomUserId(): String? {
        val prefUserId = preferences.getString(AppUUIDs.RANDOM_USER_ID, null)
        if (!prefUserId.isNullOrBlank()) {
            return prefUserId
        }

        val affUserId = packageInfoUseCase.getFirstInstallTime()?.let { installTime ->
            md5Converter.convert(installTime.toString())
                .toFakeUUID()
                .sing(SignType.INSTALL_TIME)
        }

        if (!affUserId.isNullOrBlank()) {
            return affUserId
        }

        val genUUID = generateUUID().toString().sing(SignType.RANDOM)
        preferences.saveString(AppUUIDs.RANDOM_USER_ID, genUUID)
        return preferences.getString(AppUUIDs.RANDOM_USER_ID, AffiseError.UUID_NO_VALID_METHOD)
    }
}