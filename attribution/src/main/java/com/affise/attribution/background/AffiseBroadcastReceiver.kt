package com.affise.attribution.background

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.affise.attribution.Affise
import com.affise.attribution.background.usecase.BroadcastAffiseUseCase
import com.affise.attribution.background.usecase.EventsCheckerUseCase
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.utils.toAffiseInitProperties
import com.affise.attribution.utils.toJSONObject

class AffiseBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//        }

        try {
            init(context)
        } catch (e: Exception) {
//            println("Affise Receiver error $e")
        }
    }

    private fun init(context: Context) {
        if (Affise.api != null) {
            return
        }

        val initProperties = getInitProperties(context)
        if (initProperties == null) {
            return
        }

        if (!EventsCheckerUseCase(context, initProperties).hasEvents()) {
            return
        }

        sendData(context, initProperties)

        // Set task
        BackgroundWorkImpl(context).createTask()
    }

    private fun sendData(context: Context, initProperties: AffiseInitProperties) {
        val app = context.applicationContext as Application
        val affise = BroadcastAffiseUseCase(app, initProperties)

        affise.sendDataToServerUseCase.send(withDelay = false, sendEmpty = false)
    }

    private fun getInitProperties(context: Context): AffiseInitProperties? {
        val sharedPref = context.getSharedPreferences(SERVICE_PREF, Context.MODE_PRIVATE)
        val jsonString = sharedPref.getString(PREF_KEY, null)?.toJSONObject()
        return jsonString?.toAffiseInitProperties()
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, AffiseBroadcastReceiver::class.java)

        internal const val PREF_KEY = "init_properties"
        internal const val SERVICE_PREF = "com.affise.attribution.service"
    }
}