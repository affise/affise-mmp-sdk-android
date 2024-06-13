package com.affise.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.affise.attribution.Affise
import com.google.firebase.FirebaseApp


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        pref = getSharedPreferences(
            "com.affise.attribution",
            Context.MODE_PRIVATE
        )

        FirebaseApp.initializeApp(this)

        // Initialize https://github.com/affise/sdk-android#initialize
        Affise
            .settings(
                affiseAppId = Prefs.string(AFFISE_APP_ID_KEY, DEMO_APP_ID),
                secretKey = Prefs.string(SECRET_ID_KEY, DEMO_SECRET_KEY)
            )
            //To enable debug methods set Production to false
            .setProduction(Prefs.boolean(PRODUCTION_KEY))
            // Custom domain example
            // Url trailing slash is irrelevant
            .setDomain(Prefs.string(DOMAIN_KEY, DEMO_DOMAIN))
            .start(this) // Start Affise SDK

        // Get module status https://github.com/affise/sdk-android#get-module-state
//        Affise.getStatus(AffiseModules.Status) {
//            println("Status: $it")
//        }

        // Get referrer parameter https://github.com/affise/sdk-android#get-referrer-parameter
//        Affise.getReferrerValue(ReferrerKey.AD_ID) {
//            println("Referrer AD_ID: $it")
//        }

        // Get referrer https://github.com/affise/sdk-android#get-referrer
//        Affise.getReferrer {
//            println("Referrer: $it")
//        }

        // Get providers https://github.com/affise/sdk-android#get-providers
//        val providers = Affise.getProviders().entries.associate { it.key.provider to it.value }
//        println("Providers: ${JSONObject(providers).toString(4)}")

        // Debug: Validate credentials https://github.com/affise/sdk-android#validate-credentials
//        Affise.Debug.validate {
//            println("Affise: validate = $it")
//        }

        debugRequest = Prefs.boolean(DEBUG_REQUEST_KEY)
        debugResponse = Prefs.boolean(DEBUG_RESPONSE_KEY)
        // Debug: network request/response
        Affise.Debug.network { request, response ->
            if (debugRequest) {
                println("Affise: $request")
            }
            if (debugResponse) {
                println("Affise: $response")
            }
        }
    }

    companion object {
        const val DEMO_APP_ID = "129"
        const val DEMO_SECRET_KEY = "93a40b54-6f12-443f-a250-ebf67c5ee4d2"
        const val DEMO_DOMAIN = "https://tracking.affattr.com"

        const val AFFISE_APP_ID_KEY = "AFFISE_APP_ID_KEY"
        const val SECRET_ID_KEY = "SECRET_ID_KEY"
        const val PRODUCTION_KEY = "PRODUCTION_KEY"
        const val DOMAIN_KEY = "DOMAIN_KEY"
        const val AUTO_CATCHING_TYPES_KEY = "AUTO_CATCHING_TYPES_KEY"
        const val ENABLED_METRICS_KEY = "ENABLED_METRICS_KEY"
        const val DEBUG_REQUEST_KEY = "DEBUG_REQUEST_KEY"
        const val DEBUG_RESPONSE_KEY = "DEBUG_RESPONSE_KEY"

        var debugRequest = false
        var debugResponse = false
        var pref: SharedPreferences? = null
            private set
    }
}