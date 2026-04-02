package com.affise.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.affise.app.App
import com.affise.app.Prefs
import com.affise.app.ui.MainActivity.Companion.CUSTOM_PREDEFINED_DATA
import com.affise.app.ui.components.AffiseDialogsComponent
import com.affise.app.ui.components.AffiseFab
import com.affise.app.ui.components.DialogState
import com.affise.app.ui.screen.TabsView
import com.affise.app.ui.screen.predefined.PredefinedScreen
import com.affise.app.ui.screen.predefined.stringToDataList
import com.affise.app.ui.screen.predefined.toJsonString
import com.affise.app.ui.screen.settings.AffiseSettings
import com.affise.app.ui.screen.settings.SettingsScreen
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.attribution.Affise
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebase()
        prefs()
        enableEdgeToEdge()
        setContent {
            AffiseAttributionLibTheme {
                MainView()
            }
        }
        Affise.registerDeeplinkCallback {
            popDialog(
                title = "Deeplink",
                text = "\"${it.deeplink}\"\n\n" +
                        "scheme: \"${it.scheme}\"\n\n" +
                        "host: \"${it.host}\"\n\n" +
                        "path: \"${it.path}\"\n\n" +
                        "parameters: ${it.parameters}"
            )
        }
    }

    private fun firebase() {
        FirebaseMessaging.getInstance().token.apply {
            addOnSuccessListener {
                affiseSettings.pushToken.value = it
            }
            addOnFailureListener {
                affiseSettings.pushToken.value = "Failed to retrieve token: " + it.message
            }
        }
    }

    private fun prefs() {
        affiseSettings.appId.value = Prefs.string(App.AFFISE_APP_ID_KEY, App.DEMO_APP_ID)
        affiseSettings.secretKey.value = Prefs.string(App.SECRET_ID_KEY, App.DEMO_SECRET_KEY)
        affiseSettings.domain.value = Prefs.string(App.DOMAIN_KEY, App.DEMO_DOMAIN)
        affiseSettings.isProduction.value = Prefs.boolean(App.PRODUCTION_KEY)
        affiseSettings.metrics.value = Prefs.boolean(App.ENABLED_METRICS_KEY)
        affiseSettings.debugRequest.value = Prefs.boolean(App.DEBUG_REQUEST_KEY)
        affiseSettings.debugResponse.value = Prefs.boolean(App.DEBUG_RESPONSE_KEY)
        affiseSettings.useCustomPredefined.value = Prefs.boolean(USE_CUSTOM_PREDEFINED)
        affiseSettings.predefinedData =
            stringToDataList(Prefs.string(CUSTOM_PREDEFINED_DATA)).toMutableStateList()
    }

    companion object {
        const val USE_CUSTOM_PREDEFINED = "USE_CUSTOM_PREDEFINED"
        const val CUSTOM_PREDEFINED_DATA = "CUSTOM_PREDEFINED_DATA"
    }
}

val dialogs = mutableStateListOf<DialogState>()
val affiseSettings = AffiseSettings()
val settingsScreen = mutableStateOf(false)
val predefinedScreen = mutableStateOf(false)
val selectedTabState = mutableIntStateOf(0)

@Composable
fun MainView(modifier: Modifier = Modifier) {
    AffiseDialogsComponent(dialogs = dialogs)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        floatingActionButton = {
            FloatingActionButtons()
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                settingsScreen.value -> SettingsScreen(modifier)
                predefinedScreen.value -> PredefinedScreen(modifier)
                else -> TabsView(modifier)
            }
        }
    }
}

@Composable
fun FloatingActionButtons() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val usePredefinedColor = if (affiseSettings.useCustomPredefined.value) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.secondary
        }

        if (selectedTabState.intValue == 1 && !settingsScreen.value) {
            AffiseFab(
                containerColor = usePredefinedColor,
                onClick = {
                    predefinedScreen.value = !predefinedScreen.value
                    if (predefinedScreen.value == false) {
                        val customData = affiseSettings.predefinedData.toJsonString()
                        Prefs.applyString(MainActivity.CUSTOM_PREDEFINED_DATA, customData)
                    }
                }
            ) {
                if (predefinedScreen.value) {
                    Icon(Icons.Default.Check, contentDescription = "Done")
                } else {
                    Icon(Icons.Default.Edit, contentDescription = "Add")
                }
            }
        }

        if (!predefinedScreen.value) {
            AffiseFab(
                onClick = {
                    settingsScreen.value = !settingsScreen.value
                }
            ) {
                if (settingsScreen.value) {
                    Icon(Icons.Default.Check, contentDescription = "Check")
                } else {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Main Preview",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun MainPreview() {
    AffiseAttributionLibTheme {
        MainView()
    }
}

fun popDialog(
    title: String,
    text: String,
    onDismiss: () -> Unit = { },
) {
    dialogs.add(DialogState(title = title, text = text, onDismiss = onDismiss))
}
