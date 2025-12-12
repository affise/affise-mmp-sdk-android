package com.affise.attribution.usecase

import android.content.SharedPreferences
import com.affise.attribution.errors.AffiseError
import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.session.CurrentActiveActivityCountProvider
import com.affise.attribution.utils.*
import com.affise.attribution.utils.generateUUID
import com.affise.attribution.utils.timestamp
import com.affise.attribution.utils.saveBoolean
import com.affise.attribution.utils.saveLong
import java.util.*

class FirstAppOpenUseCase(
    private val preferences: SharedPreferences,
    private val activityCountProvider: CurrentActiveActivityCountProvider,
    private val persistentUseCase: PersistentUseCase
) {

    private var firstRun: Boolean = false

    private var isFirstOpenValue: Boolean = preferences.getBoolean(FIRST_OPENED, true)

    /**
     * Check preferences for have first opened date and generate properties if no data
     */
    fun onAppCreated() {
        if (preferences.getLong(FIRST_OPENED_DATE_KEY, 0) == 0L) {
            onAppFirstOpen()
        }
        checkSaveUUIDs()

        firstRun = preferences.getBoolean(FIRST_OPENED, true)

        //init session observer
        activityCountProvider.init()
    }

    /**
     * Generate properties on app first open
     */
    private fun onAppFirstOpen() {
        //Create first open date
        val firstOpenDate = timestamp()

        checkSaveUUIDs()

        //Save properties
        preferences.saveBoolean(FIRST_OPENED, true)
        preferences.saveLong(FIRST_OPENED_DATE_KEY, firstOpenDate)
    }

    private fun checkSaveUUIDs() {
        preferences.apply {
            //Create affDevId
            checkSaveString(AFF_DEVICE_ID) {
                persistentUseCase.getAffDeviceId() ?: generateUUID().toString()
            }
            //Create affAltDevId
            checkSaveString(AFF_ALT_DEVICE_ID) {
                generateUUID().toString()
            }
            //Create randomUserId
            checkSaveString(ProviderType.RANDOM_USER_ID.provider) {
                generateUUID().toString()
            }
        }
    }

    /**
     * Get first open
     * @return is first open
     */
    fun isFirstOpen() = isFirstOpenValue

    /**
     * First open completed
     */
    fun completeFirstOpen() {
        isFirstOpenValue = false
        //Save properties
        preferences.edit().apply {
            putBoolean(FIRST_OPENED, isFirstOpenValue)
        }.apply()
    }

    /**
     * Get first run
     * @return is first run
     */
    fun isFirstRun(): Boolean = firstRun

    /**
     * Get first open date
     * @return first open date
     */
    fun getFirstOpenDate() = preferences
        .getLong(FIRST_OPENED_DATE_KEY, 0)
        .let { if (it == 0L) null else Date(it) }

    /**
     * Get devise id
     * @return devise id
     */
    fun getAffiseDeviseId() = preferences
        .getString(
            AFF_DEVICE_ID,
            AffiseError.ERROR_READING_FROM_PREFERENCES
        )

    /**
     * Get alt devise id
     * @return alt devise id
     */
    fun getAffiseAltDeviseId() = preferences
        .getString(
            AFF_ALT_DEVICE_ID,
            AffiseError.ERROR_READING_FROM_PREFERENCES
        )

    /**
     * Get random user id
     * @return random user id
     */
    fun getRandomUserId() = preferences
        .getString(
            ProviderType.RANDOM_USER_ID.provider,
            AffiseError.ERROR_READING_FROM_PREFERENCES
        )

    companion object {
        private const val FIRST_OPENED = "FIRST_OPENED"
        private const val FIRST_OPENED_DATE_KEY = "FIRST_OPENED_DATE_KEY"
        private const val AFF_DEVICE_ID = "AFF_DEVICE_ID"
        private const val AFF_ALT_DEVICE_ID = "AFF_ALT_DEVICE_ID"
    }
}