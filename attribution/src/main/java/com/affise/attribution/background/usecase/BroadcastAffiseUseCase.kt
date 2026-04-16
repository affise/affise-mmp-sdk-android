package com.affise.attribution.background.usecase

import android.app.Application
import com.affise.attribution.AffiseComponent
import com.affise.attribution.executors.ExecutorServiceProviderImpl
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.logs.AffiseThreadUncaughtExceptionHandlerImpl
import com.affise.attribution.usecase.SendDataToServerUseCase

internal class BroadcastAffiseUseCase(
    app: Application,
    initProperties: AffiseInitProperties
) : AffiseComponent(app, initProperties) {

    override fun initSendDataToServerUseCase(): SendDataToServerUseCase =
        BroadcastSendDataUseCase(
            postBackModelFactory,
            cloudRepository,
            eventsRepository,
            internalEventsRepository,
            ExecutorServiceProviderImpl("Broadcast Sending Worker"),
            logsRepository,
            metricsRepository,
            logsManager,
            preferencesUseCase,
            firstAppOpenUseCase
        )

    override fun init() {
        sendGDPREventUseCase.sendForgetMeEvent()
        setPropertiesWhenInitUseCase.init(initProperties)
        deeplinkManager.init()

        AffiseThreadUncaughtExceptionHandlerImpl(
            Thread.getDefaultUncaughtExceptionHandler(),
            logsManager
        )
            .also(Thread::setDefaultUncaughtExceptionHandler)

        moduleManager.init(
            dependencies = listOf(
                buildConfigPropertiesProvider,
                stringToMD5Converter,
                stringToSHA1Converter,
                providersToJsonStringConverter,
                httpClient,
                postBackModelFactory,
                postBackModelToJsonStringConverter,
                sharedPreferences,
                initProperties
            )
        )
        persistentUseCase.init(moduleManager)
        firstAppOpenUseCase.onAppCreated()

        storeInstallReferrerUseCase.init(moduleManager)
    }
}