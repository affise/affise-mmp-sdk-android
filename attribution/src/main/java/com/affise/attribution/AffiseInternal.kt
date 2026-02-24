package com.affise.attribution

import com.affise.attribution.events.Event
import com.affise.attribution.events.OnSendFailedCallback
import com.affise.attribution.events.OnSendSuccessCallback
import com.affise.attribution.internal.InternalEvent

internal object AffiseInternal {

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