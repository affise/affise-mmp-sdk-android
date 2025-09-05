package com.affise.attribution

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.affise.attribution.build.BuildConfigPropertiesProviderImpl
import com.affise.attribution.converter.*
import com.affise.attribution.converter.JsonObjectToMetricsEventConverter
import com.affise.attribution.build.BuildConfigPropertiesProvider
import com.affise.attribution.debug.network.DebugNetworkUseCase
import com.affise.attribution.debug.network.DebugNetworkUseCaseImpl
import com.affise.attribution.debug.validate.DebugValidateUseCase
import com.affise.attribution.debug.validate.DebugValidateUseCaseImpl
import com.affise.attribution.executors.ExecutorServiceProviderImpl
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.deeplink.DeeplinkClickRepository
import com.affise.attribution.deeplink.DeeplinkClickRepositoryImpl
import com.affise.attribution.deeplink.DeeplinkManagerImpl
import com.affise.attribution.deeplink.InstallReferrerToDeeplinkUriConverter
import com.affise.attribution.events.*
import com.affise.attribution.init.*
import com.affise.attribution.internal.*
import com.affise.attribution.logs.*
import com.affise.attribution.metrics.*
import com.affise.attribution.modules.AffiseModuleManager
import com.affise.attribution.network.CloudRepository
import com.affise.attribution.network.CloudRepositoryImpl
import com.affise.attribution.network.HttpClient
import com.affise.attribution.network.HttpClientImpl
import com.affise.attribution.parameters.providers.UserAgentProvider
import com.affise.attribution.parameters.base.PropertiesProviderFactory
import com.affise.attribution.parameters.factory.PostBackModelFactory
import com.affise.attribution.preferences.ApplicationLifecyclePreferencesRepositoryImpl
import com.affise.attribution.preferences.ApplicationLifetimePreferencesRepositoryImpl
import com.affise.attribution.referrer.AffiseReferrerDataToStringConverter
import com.affise.attribution.referrer.RetrieveReferrerOnServerUseCase
import com.affise.attribution.session.CurrentActiveActivityCountProvider
import com.affise.attribution.session.CurrentActiveActivityCountProviderImpl
import com.affise.attribution.session.SessionManager
import com.affise.attribution.session.SessionManagerImpl
import com.affise.attribution.storages.*
import com.affise.attribution.test.CrashApplicationUseCase
import com.affise.attribution.test.CrashApplicationUseCaseImpl
import com.affise.attribution.usecase.*
import com.affise.attribution.utils.ActivityActionsManager
import com.affise.attribution.utils.ActivityActionsManagerImpl
import com.affise.attribution.utils.SystemAppChecker
import com.affise.attribution.webBridge.WebBridgeManager

internal class AffiseComponent(
    private val app: Application,
    initProperties: AffiseInitProperties,
) : AffiseApi {

    /**
     * PostBackModelFactory
     */
    override val postBackModelFactory: PostBackModelFactory by lazy {
        PropertiesProviderFactory(
            buildConfigPropertiesProvider,
            app,
            firstAppOpenUseCase,
            storeInstallReferrerUseCase,
            sessionManager,
            sharedPreferences,
            initPropertiesStorage,
            stringToMD5Converter,
            stringToSHA256Converter,
            logsManager,
            isDeeplinkClickRepository,
            deviceUseCase,
            remarketingUseCase,
            storeUseCase,
            pushTokenUseCase,
        ).create()
    }

    private val stringToMD5Converter: StringToMD5Converter by lazy {
        StringToMD5Converter(logsManager)
    }

    private val stringToSHA1Converter: StringToSHA1Converter by lazy {
        StringToSHA1Converter()
    }

    private val stringToSHA256Converter: StringToSHA256Converter by lazy {
        StringToSHA256Converter()
    }

    private val converterToBase64: ConverterToBase64 by lazy {
        ConverterToBase64()
    }

    private val providersToJsonStringConverter: ProvidersToJsonStringConverter by lazy {
        ProvidersToJsonStringConverter()
    }

    private val postBackModelToJsonStringConverter: PostBackModelToJsonStringConverter by lazy {
        PostBackModelToJsonStringConverter(indexUseCase)
    }

    private val buildConfigPropertiesProvider: BuildConfigPropertiesProvider by lazy {
        BuildConfigPropertiesProviderImpl()
    }

    private val indexUseCase: IndexUseCase by lazy {
        IndexUseCaseImpl(sharedPreferences)
    }

    /**
     * ActivityCountProvider
     */
    private val activityCountProvider: CurrentActiveActivityCountProvider by lazy {
        CurrentActiveActivityCountProviderImpl(activityActionsManager)
    }

    /**
     * Provides [EventsStorage]
     */
    private val eventsStorage: EventsStorage by lazy {
        EventsStorageImpl(app, logsManager)
    }

    /**
     * Provides [InternalEventsStorage]
     */
    private val internalEventsStorage: InternalEventsStorage by lazy {
        InternalEventsStorageImpl(app, logsManager)
    }

    /**
     * Provides [Converter] from [Event] to [SerializedEvent]
     */
    private val eventToSerializedEventConverter: Converter<Event, SerializedEvent> by lazy {
        EventToSerializedEventConverter(indexUseCase)
    }

    /**
     * Provides [Converter] from [InternalEvent] to [SerializedEvent]
     */
    private val internalEventToSerializedEventConverter: Converter<InternalEvent, SerializedEvent> by lazy {
        InternalEventToSerializedEventConverter()
    }

    /**
     * EventsRepository
     */
    private val eventsRepository: EventsRepository by lazy {
        EventsRepositoryImpl(
            converterToBase64,
            eventToSerializedEventConverter,
            logsManager,
            eventsStorage
        )
    }

    /**
     * InternalEventsRepository
     */
    private val internalEventsRepository: InternalEventsRepository by lazy {
        InternalEventsRepositoryImpl(
            converterToBase64,
            internalEventToSerializedEventConverter,
            logsManager,
            internalEventsStorage
        )
    }

    private val logStorage: LogsStorage by lazy {
        LogsStorageImpl(app)
    }

    /**
     * LogsRepository
     */
    private val logsRepository: LogsRepository by lazy {
        LogsRepositoryImpl(converterToBase64, LogToSerializedLogConverter(), logStorage)
    }

    private val metricsStorage: MetricsStorage by lazy {
        MetricsStorageImpl(app, JsonObjectToMetricsEventConverter())
    }

    /**
     * MetricsRepository
     */
    private val metricsRepository: MetricsRepository by lazy {
        MetricsRepositoryImpl(
            converterToBase64,
            eventToSerializedEventConverter,
            metricsStorage
        )
    }

    /**
     * MetricsUseCase
     */
//    private val metricsUseCase: MetricsUseCase by lazy {
//        MetricsUseCaseImpl(ExecutorServiceProviderImpl("Metrics Worker"), metricsRepository)
//    }

    /**
     * MetricsManager
     */
//    override val metricsManager: MetricsManager by lazy {
//        MetricsManagerImpl(activityActionsManager, metricsUseCase, stringToSHA1Converter)
//    }

    /**
     * Provides [CrashApplicationUseCase]
     */
    override val crashApplicationUseCase: CrashApplicationUseCase by lazy {
        CrashApplicationUseCaseImpl()
    }

    /**
     * HttpClient
     */
    private val httpClient: HttpClient by lazy {
        HttpClientImpl()
    }

    private val userAgentProvider: UserAgentProvider? by lazy {
        postBackModelFactory.getProvider<UserAgentProvider>()
    }

    /**
     * CloudRepository
     */
    private val cloudRepository: CloudRepository by lazy {
        CloudRepositoryImpl(
            httpClient,
            userAgentProvider,
            postBackModelToJsonStringConverter
        )
    }

    private val gdprEventRepository: GDPREventRepository by lazy {
        GDPREventRepository(sharedPreferences, eventToSerializedEventConverter)
    }

    /**
     * SendDataToServerUseCase
     */
    private val sendDataToServerUseCase: SendDataToServerUseCase by lazy {
        SendDataToServerUseCaseImpl(
            postBackModelFactory,
            cloudRepository,
            eventsRepository,
            internalEventsRepository,
            ExecutorServiceProviderImpl("Sending Worker"),
            logsRepository,
            metricsRepository,
            logsManager,
            preferencesUseCase,
            firstAppOpenUseCase
        )
    }

    override val immediateSendToServerUseCase: ImmediateSendToServerUseCase by lazy {
        ImmediateSendToServerUseCaseImpl(
            ExecutorServiceProviderImpl("Sending Now Worker"),
            cloudRepository,
            postBackModelFactory,
            eventToSerializedEventConverter,
            logsManager,
        )
    }

    override val pushTokenUseCase: PushTokenUseCase by lazy {
        PushTokenUseCaseImpl(sharedPreferences)
    }

    /**
     * StoreLogsUseCase
     */
    private val storeLogsUseCase: StoreLogsUseCase by lazy {
        StoreLogsUseCaseImpl(
            ExecutorServiceProviderImpl("Log Worker"),
            logsRepository
        )
    }

    /**
     * Provides [IsFirstForUserStorage]
     */
    private val isFirstForUserStorage: IsFirstForUserStorage by lazy {
        IsFirstForUserStorageImpl(app, logsManager)
    }

    /**
     * Provides [IsFirstForUserUseCase]
     */
    private val isFirstForUserUseCase: IsFirstForUserUseCase by lazy {
        IsFirstForUserUseCaseImpl(isFirstForUserStorage)
    }

    /**
     * Provides [DeviceUseCase]
     */
    private val deviceUseCase: DeviceUseCase by lazy {
        DeviceUseCaseImpl()
    }

    /**
     * Provides [RemarketingUseCase]
     */
    private val remarketingUseCase: RemarketingUseCase by lazy {
        RemarketingUseCaseImpl()
    }

    private val persistentUseCase: PersistentUseCase by lazy {
        PersistentUseCaseImpl()
    }

    /**
     * SetPropertiesWhenInitUseCase
     */
    override val setPropertiesWhenInitUseCase: SetPropertiesWhenAppInitializedUseCase by lazy {
        SetPropertiesWhenAppInitializedUseCaseImpl(initPropertiesStorage)
    }

    /**
     * FirstAppOpenUseCase
     */
    override val firstAppOpenUseCase: FirstAppOpenUseCase by lazy {
        FirstAppOpenUseCase(sharedPreferences, activityCountProvider, persistentUseCase)
    }

    /**
     * StoreEventUseCase
     */
    override val storeEventUseCase: StoreEventUseCase by lazy {
        StoreEventUseCaseImpl(
            ExecutorServiceProviderImpl("Event Worker"),
            eventsRepository,
            eventsManager,
            preferencesUseCase,
            activityCountProvider,
            logsManager,
            isFirstForUserUseCase
        )
    }

    /**
     * StoreInternalEventUseCase
     */
    override val storeInternalEventUseCase: StoreInternalEventUseCase by lazy {
        StoreInternalEventUseCaseImpl(
            ExecutorServiceProviderImpl("Internal Event Worker"),
            internalEventsRepository
        )
    }

    /**
     * RetrieveInstallReferrerUseCase
     */
    private val googleInstallReferrerUseCase by lazy {
        GoogleInstallReferrerUseCase(
            sharedPreferences,
            AffiseReferrerDataToStringConverter(),
            StringToAffiseReferrerDataConverter(logsManager),
            app,
            deeplinkManager,
            logsManager,
            InstallReferrerToDeeplinkUriConverter(),
        )
    }

    /**
     * StoreInstallReferrerUseCase
     */
    override val storeInstallReferrerUseCase by lazy {
        StoreInstallReferrerUseCase(
            app,
            storeUseCase,
            googleInstallReferrerUseCase
        )
    }

    /**
     * RetrieveReferrerOnServerUseCase
     */
    override val retrieveReferrerOnServerUseCase by lazy {
        RetrieveReferrerOnServerUseCase(
            moduleManager
        )
    }

    /**
     * InitPropertiesStorage
     */
    override val initPropertiesStorage: InitPropertiesStorage = InitPropertiesStorageImpl()

    /**
     * provides [PreferencesUseCaseImpl]
     */
    override val preferencesUseCase: PreferencesUseCaseImpl by lazy {
        PreferencesUseCaseImpl(
            ApplicationLifecyclePreferencesRepositoryImpl(),
            ApplicationLifetimePreferencesRepositoryImpl(sharedPreferences)
        )
    }

    /**
     * provides [EraseUserDataUseCaseImpl]
     */
    override val eraseUserDataUseCase: EraseUserDataUseCaseImpl by lazy {
        EraseUserDataUseCaseImpl(eventsRepository, gdprEventRepository)
    }

    /**
     * DeeplinkClickRepository
     */
    private val isDeeplinkClickRepository: DeeplinkClickRepository = DeeplinkClickRepositoryImpl()

    /**
     * ActivityActionsListeners
     */
    private val activityActionsManager: ActivityActionsManager by lazy {
        ActivityActionsManagerImpl(
            app,
            logsManager,
            initProperties,
        )
    }

    private val storeUseCase: StoreUseCase by lazy {
        StoreUseCaseImpl(
            app,
            logsManager,
            SystemAppChecker(app)
        )
    }

    /**
     * SharedPreferences
     */
    override val sharedPreferences: SharedPreferences by lazy {
        app.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * LogsManager
     */
    override val logsManager: LogsManager by lazy {
        LogsManagerImpl(storeLogsUseCase)
    }

    /**
     * SessionManager
     */
    override val sessionManager: SessionManager by lazy {
        SessionManagerImpl(
            sharedPreferences,
            activityCountProvider
        )
    }

    /**
     * EventsManager
     */
    override val eventsManager: EventsManager by lazy {
        EventsManager(sendDataToServerUseCase, activityCountProvider)
    }

    /**
     * WebBridgeManager
     */
    override val webBridgeManager: WebBridgeManager by lazy {
        WebBridgeManager(
            storeEventUseCase,
            moduleManager
        )
    }

    /**
     * DeeplinkManager
     */
    override val deeplinkManager: DeeplinkManagerImpl by lazy {
        DeeplinkManagerImpl(
            initPropertiesStorage,
            isDeeplinkClickRepository,
            activityActionsManager
        )
    }

    /**
     * AutoCatchingClickProvider
     */
//    override val autoCatchingClickProvider: AutoCatchingClickProvider = AutoCatchingClickProvider(
//        storeEventUseCase, activityActionsManager
//    )

    override val sendGDPREventUseCase: SendGDPREventUseCaseImpl by lazy {
        SendGDPREventUseCaseImpl(
            gdprEventRepository,
            ExecutorServiceProviderImpl("GDPR Event Worker"),
            cloudRepository,
            postBackModelFactory,
            eraseUserDataUseCase
        )
    }

    override val moduleManager: AffiseModuleManager by lazy {
        AffiseModuleManager(
            application = app,
            logsManager = logsManager,
            postBackModelFactory = postBackModelFactory,
            initProperties = initProperties,
        )
    }

    override val debugValidateUseCase: DebugValidateUseCase by lazy {
        DebugValidateUseCaseImpl(
            initProperties = initProperties,
            postBackModelFactory = postBackModelFactory,
            logsManager = logsManager,
            httpClient = httpClient,
            sendServiceProvider = ExecutorServiceProviderImpl("Debug Validate Worker"),
            converter = providersToJsonStringConverter,
        )
    }

    override val debugNetworkUseCase: DebugNetworkUseCase by lazy {
        DebugNetworkUseCaseImpl(
            initProperties = initProperties,
            httpClient = httpClient,
        )
    }

    private var isReady: Boolean = false

    /**
     * Init properties
     */
    init {
        try {
            sendGDPREventUseCase.sendForgetMeEvent()
            sessionManager.init()
            setPropertiesWhenInitUseCase.init(initProperties)
            deeplinkManager.init()
    //        autoCatchingClickProvider.init(initProperties.autoCatchingClickEvents)
    //        metricsManager.setEnabledMetrics(initProperties.enabledMetrics)

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

            storeInstallReferrerUseCase.onReferrerSetupFinished {
                eventsManager.init()
            }
                .init(moduleManager)

            isReady = true
            initProperties.onInitSuccessHandler?.handle()
        } catch (e: Exception) {
            initProperties.onInitErrorHandler?.handle(e)
        }
    }

    override fun isInitialized(): Boolean = isReady

    companion object {
        private const val PREFERENCES_FILE_NAME = "com.affise.attribution"
    }
}