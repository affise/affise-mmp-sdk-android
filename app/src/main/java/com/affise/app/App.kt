package com.affise.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.FirebaseApp


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        pref = getSharedPreferences(
            "com.affise.attribution",
            Context.MODE_PRIVATE
        )

        FirebaseApp.initializeApp(this)

        AffiseDemo.init(this)
    }

    companion object {
        var pref: SharedPreferences? = null
            private set
    }
}