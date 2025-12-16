package com.affise.attribution.usecase

import android.content.SharedPreferences
import com.affise.attribution.session.CurrentActiveActivityCountProvider
import com.affise.attribution.utils.SignType
import com.affise.attribution.utils.checkSaveString
import com.affise.attribution.utils.generateUUID
import com.affise.attribution.utils.saveBoolean
import com.affise.attribution.utils.saveLong
import com.affise.attribution.utils.sing
import com.affise.attribution.utils.timestamp
import java.util.Date

class FirstAppOpenUseCase(
    private val preferences: SharedPreferences,
    private val activityCountProvider: CurrentActiveActivityCountProvider,
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
            //Create affAltDevId
            checkSaveString(AppUUIDs.AFF_ALT_DEVICE_ID) {
                generateUUID().toString().sing(SignType.RANDOM)
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

    companion object {
        private const val FIRST_OPENED = "FIRST_OPENED"
        private const val FIRST_OPENED_DATE_KEY = "FIRST_OPENED_DATE_KEY"
    }
}