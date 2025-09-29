package com.affise.attribution

import android.content.SharedPreferences
import com.affise.attribution.debug.network.DebugNetworkUseCase
import com.affise.attribution.debug.validate.DebugValidateUseCase
import com.affise.attribution.deeplink.DeeplinkManagerImpl
import com.affise.attribution.events.EventsManager
import com.affise.attribution.events.StoreEventUseCase
import com.affise.attribution.init.AffiseInitProperties
import com.affise.attribution.init.InitPropertiesStorage
import com.affise.attribution.init.SetPropertiesWhenAppInitializedUseCase
import com.affise.attribution.internal.StoreInternalEventUseCase
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.modules.AffiseModuleManager
import com.affise.attribution.parameters.factory.PostBackModelFactory
import com.affise.attribution.referrer.RetrieveReferrerOnServerUseCase
import com.affise.attribution.session.SessionManager
import com.affise.attribution.test.CrashApplicationUseCase
import com.affise.attribution.usecase.EraseUserDataUseCaseImpl
import com.affise.attribution.usecase.FirstAppOpenUseCase
import com.affise.attribution.usecase.ImmediateSendToServerUseCase
import com.affise.attribution.usecase.PreferencesUseCaseImpl
import com.affise.attribution.usecase.PushTokenUseCase
import com.affise.attribution.usecase.SendGDPREventUseCaseImpl
import com.affise.attribution.usecase.StoreInstallReferrerUseCase
import com.affise.attribution.webBridge.WebBridgeManager

/**
 * Library api contract
 */
internal interface AffiseApi {
    val initProperties: AffiseInitProperties
    val setPropertiesWhenInitUseCase: SetPropertiesWhenAppInitializedUseCase
    val firstAppOpenUseCase: FirstAppOpenUseCase
    val sessionManager: SessionManager
    val eventsManager: EventsManager
    val storeEventUseCase: StoreEventUseCase
    val storeInternalEventUseCase: StoreInternalEventUseCase
    val sharedPreferences: SharedPreferences
    val logsManager: LogsManager
    val webBridgeManager: WebBridgeManager
    val deeplinkManager: DeeplinkManagerImpl
    val initPropertiesStorage: InitPropertiesStorage
//    val autoCatchingClickProvider: AutoCatchingClickProvider
    val preferencesUseCase: PreferencesUseCaseImpl
    val eraseUserDataUseCase: EraseUserDataUseCaseImpl
    val sendGDPREventUseCase: SendGDPREventUseCaseImpl
//    val metricsManager: MetricsManager
    val crashApplicationUseCase: CrashApplicationUseCase
    val storeInstallReferrerUseCase: StoreInstallReferrerUseCase
    val retrieveReferrerOnServerUseCase: RetrieveReferrerOnServerUseCase
    val moduleManager: AffiseModuleManager
    val postBackModelFactory: PostBackModelFactory
    val debugValidateUseCase: DebugValidateUseCase
    val debugNetworkUseCase: DebugNetworkUseCase
    val immediateSendToServerUseCase: ImmediateSendToServerUseCase
    val pushTokenUseCase: PushTokenUseCase

    fun isInitialized(): Boolean
}