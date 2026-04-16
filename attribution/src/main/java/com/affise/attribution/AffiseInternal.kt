package com.affise.attribution

import android.app.Application
import android.util.Log
import com.affise.attribution.errors.AffiseError
import com.affise.attribution.events.Event
import com.affise.attribution.events.OnSendFailedCallback
import com.affise.attribution.events.OnSendSuccessCallback
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.internal.InternalEvent

internal object AffiseInternal {

    /**
     * Init [AffiseComponent]
     */
    @Synchronized
    internal fun start(initProperties: AffiseInitProperties, app: Application) {
        //Check creating AffiseComponent
        if (Affise.api == null) {
            try {
                //Create AffiseComponent
                Affise.api = AffiseComponent(
                    app = app,
                    initProperties = initProperties
                )
                initProperties.onInitSuccessHandler?.handle()
            } catch (e: Exception) {
                Log.w(this.javaClass.simpleName, "Affise SDK start error: $e")
                initProperties.onInitErrorHandler?.handle(e)
            }
        } else {
            initProperties.onInitErrorHandler?.handle(AffiseError.AlreadyInitialized())
            Log.w(this.javaClass.simpleName,AffiseError.ALREADY_INITIALIZED)
        }
    }

    /**
     * Store and send [event]
     */
    fun sendEvent(event: Event) {
        Affise.api?.storeEventUseCase?.storeEvent(event)
    }

    /**
     * Send now [event]
     */
    fun sendEventNow(event: Event, success: OnSendSuccessCallback, failed: OnSendFailedCallback) {
        Affise.api?.immediateSendToServerUseCase?.sendNow(event, success, failed)
    }

    /**
     * Send internal event
     */
    fun sendInternalEvent(event: InternalEvent) {
        Affise.api?.storeInternalEventUseCase?.storeInternalEvent(event)
    }
}