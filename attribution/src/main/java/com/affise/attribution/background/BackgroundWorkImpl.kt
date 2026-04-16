package com.affise.attribution.background

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.session.CurrentActiveActivityCountProvider
import com.affise.attribution.utils.toJSONObject

internal class BackgroundWorkImpl(
    private val context: Context,
    private val activityCountProvider: CurrentActiveActivityCountProvider? = null,
) : BackgroundWork {
    private val alarmManager: AlarmManager? = context
        .getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    private val sharedPref = context
        .getSharedPreferences(AffiseBroadcastReceiver.SERVICE_PREF, Context.MODE_PRIVATE)

    override fun init(initProperties: AffiseInitProperties) {
        setInitProperties(initProperties)
        taskOnStop()
    }

    private fun taskOnStop() {
        activityCountProvider?.addActivityStopListener {
            createTask()
        }
    }

    override fun createTask(delay: Long, requestCode: Int) {
        if (alarmManager == null) {
            return
        }

        try {
            createAlarm(delay, requestCode)
        } catch (e: Exception) {
//            println("Affise BackgroundWork error $e")
        }
    }

    private fun createAlarm(delay: Long, requestCode: Int) {
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + delay,
            createPendingIntent(requestCode)
        )
    }

    fun setInitProperties(initProperties: AffiseInitProperties?) {
        initProperties?.let {
            sharedPref.edit()
                ?.putString(
                    AffiseBroadcastReceiver.PREF_KEY,
                    it.toJSONObject().toString(0)
                )
                ?.apply()
        }
    }

    fun clearInitProperties() {
        sharedPref.edit()
            ?.remove(AffiseBroadcastReceiver.PREF_KEY)
            ?.apply()
    }

    private fun createPendingIntent(requestCode: Int): PendingIntent = PendingIntent
        .getBroadcast(
            context,
            requestCode, // one time
            AffiseBroadcastReceiver.intent(context),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
}