package com.affise.attribution.background.usecase

import com.affise.attribution.Affise
import com.affise.attribution.events.EventsParams
import com.affise.attribution.events.EventsRepository
import com.affise.attribution.executors.ExecutorServiceProvider
import com.affise.attribution.internal.InternalEventsRepository
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.logs.LogsRepository
import com.affise.attribution.metrics.MetricsRepository
import com.affise.attribution.network.CloudConfig
import com.affise.attribution.network.CloudRepository
import com.affise.attribution.parameters.factory.PostBackModelFactory
import com.affise.attribution.usecase.FirstAppOpenUseCase
import com.affise.attribution.usecase.PreferencesUseCase
import com.affise.attribution.usecase.SendDataToServerUseCaseImpl

internal class BroadcastSendDataUseCase(
    val postBackModelFactory: PostBackModelFactory,
    val cloudRepository: CloudRepository,
    val eventsRepository: EventsRepository,
    val internalEventsRepository: InternalEventsRepository,
    val sendServiceProvider: ExecutorServiceProvider,
    val logsRepository: LogsRepository,
    val metricsRepository: MetricsRepository,
    val logsManager: LogsManager,
    val preferencesUseCase: PreferencesUseCase,
    val firstAppOpenUseCase: FirstAppOpenUseCase
) : SendDataToServerUseCaseImpl(
    postBackModelFactory,
    cloudRepository,
    eventsRepository,
    internalEventsRepository,
    sendServiceProvider,
    logsRepository,
    metricsRepository,
    logsManager,
    preferencesUseCase,
    firstAppOpenUseCase
) {

    fun hasEvents(): Boolean {
        for (url in CloudConfig.getUrls()) {
            val events = eventsRepository.hasEvents(url)
            val internalEvents = internalEventsRepository.hasEvents(url)
            if (events || internalEvents) {
                return true
            }
        }
        return false
    }

    private fun hasDataToSend(url: String): Boolean {
        return eventsRepository.hasEvents(url)
                || internalEventsRepository.hasEvents(url)
                || logsRepository.hasLogs(url)
                || metricsRepository.hasMetrics(url)
    }

    override fun send(url: String, sendEmpty: Boolean) {
        do {
            if (!hasDataToSend(url)) {
                return
            }

            //Get events
            val events = eventsRepository.getEvents(url)

            //Get logs
            val logs = logsRepository.getLogs(url)

            //Get metrics
            val metrics = metricsRepository.getMetrics(url)

            //Get internal events
            val internalEvents = internalEventsRepository.getEvents(url)

            //Generate data
            val data = postBackModelFactory.create(events, logs, metrics, internalEvents)

            try {
                if (Affise.api != null) {
                    return
                }

                //Send data for single url
                cloudRepository.send(listOf(data), url)

                //Remove sent events
                eventsRepository.deleteEvent(events.map { it.id }, url)

                //Remove sent logs
                logsRepository.deleteLogs(logs.map { it.id }, url)

                //Remove sent metrics
                metricsRepository.deleteMetrics(url)

                //Remove sent internal events
                internalEventsRepository.deleteEvent(internalEvents.map { it.id }, url)
            } catch (cloudException: Throwable) {
                //Log error
                logsManager.addNetworkError(cloudException)

                return
            }
        } while (events.size == EventsParams.EVENTS_SEND_COUNT)
    }

}