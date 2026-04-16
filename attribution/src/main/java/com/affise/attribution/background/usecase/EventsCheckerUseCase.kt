package com.affise.attribution.background.usecase

import android.content.Context
import com.affise.attribution.converter.ConverterToBase64
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.network.CloudConfig
import com.affise.attribution.storages.EventsStorage
import com.affise.attribution.storages.EventsStorageImpl
import com.affise.attribution.storages.InternalEventsStorage
import com.affise.attribution.storages.InternalEventsStorageImpl

internal class EventsCheckerUseCase(
    context: Context,
    initProperties: AffiseInitProperties
) {

    private val logsManager: LogsManager by lazy {
        NoLogsManager()
    }
    private val converterToBase64: ConverterToBase64 by lazy {
        ConverterToBase64()
    }

    private val eventsStorage: EventsStorage = EventsStorageImpl(context, logsManager)
    private val internalEventsStorage: InternalEventsStorage =
        InternalEventsStorageImpl(context, logsManager)

    init {
        CloudConfig.setupDomain(initProperties.domain)
    }

    fun hasEvents(): Boolean {
        for (url in CloudConfig.getUrls()) {
            val urlHash = converterToBase64.convert(url)
            val events = eventsStorage.hasEvents(urlHash)
            val internalEvents = internalEventsStorage.hasEvents(urlHash)
            if (events || internalEvents) {
                return true
            }
        }
        return false
    }
}