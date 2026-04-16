package com.affise.app.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.affise.app.AffiseDemo
import com.affise.app.Prefs
import com.affise.app.R
import com.affise.app.ui.affiseSettings
import com.affise.app.ui.components.AffiseButton
import com.affise.app.ui.components.AffiseSwitch
import com.affise.app.ui.components.AffiseTextField
import com.affise.app.ui.popDialog
import com.affise.app.ui.screen.predefined.PredefinedData
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.attribution.Affise
import com.affise.attribution.BuildConfig

data class AffiseSettings(
    val domain: MutableState<String> = mutableStateOf(""),
    val isProduction: MutableState<Boolean> = mutableStateOf(BuildConfig.DEBUG),
    val isOfflineModeEnabled: MutableState<Boolean> = mutableStateOf(Affise.isOfflineModeEnabled()),
    val isBackgroundTrackingEnabled: MutableState<Boolean> = mutableStateOf(Affise.isBackgroundTrackingEnabled()),
    val isTrackingEnabled: MutableState<Boolean> = mutableStateOf(Affise.isTrackingEnabled()),
    val metrics: MutableState<Boolean> = mutableStateOf(false),
    val debugRequest: MutableState<Boolean> = mutableStateOf(false),
    val debugResponse: MutableState<Boolean> = mutableStateOf(false),
    val appId: MutableState<String> = mutableStateOf(""),
    val secretKey: MutableState<String> = mutableStateOf(""),
    val pushToken: MutableState<String> = mutableStateOf(""),
    val useCustomPredefined: MutableState<Boolean> = mutableStateOf(false),
    var predefinedData: SnapshotStateList<PredefinedData> = mutableStateListOf(),
)

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxHeight(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus()
            },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AffiseTextField(
                affiseSettings.domain,
                label = stringResource(R.string.domain),
                readOnly = false,
                maxLines = 4,
                onValueChange = {
                    Prefs.applyString(AffiseDemo.DOMAIN_KEY, it)
                }
            )

            AffiseSwitch(
                affiseSettings.isProduction,
                label = stringResource(R.string.production),
            ) {
                Prefs.applyBoolean(AffiseDemo.PRODUCTION_KEY, it)
                popDialog("Warning", "Restart needed")
            }

            AffiseSwitch(
                affiseSettings.isOfflineModeEnabled,
                label = stringResource(R.string.offline_mode),
            ) {
                Affise.setOfflineModeEnabled(it)
            }

            AffiseSwitch(
                affiseSettings.isBackgroundTrackingEnabled,
                label = stringResource(R.string.background_tracking),
            ) {
                Affise.setBackgroundTrackingEnabled(it)
            }

            AffiseSwitch(
                affiseSettings.isTrackingEnabled,
                label = stringResource(R.string.tracking),
            ) {
                Affise.setTrackingEnabled(it)
            }

            AffiseSwitch(
                affiseSettings.metrics,
                label = stringResource(R.string.metrics),
            ) {
                Prefs.applyBoolean(AffiseDemo.ENABLED_METRICS_KEY, it)
            }

            AffiseSwitch(
                affiseSettings.debugRequest,
                label = stringResource(R.string.debug_request),
            ) {
                AffiseDemo.debugRequest = it
                Prefs.applyBoolean(AffiseDemo.DEBUG_REQUEST_KEY, it)
            }

            AffiseSwitch(
                affiseSettings.debugResponse,
                label = stringResource(R.string.debug_response),
            ) {
                AffiseDemo.debugResponse = it
                Prefs.applyBoolean(AffiseDemo.DEBUG_RESPONSE_KEY, it)
            }

            AffiseTextField(
                affiseSettings.appId,
                label = stringResource(R.string.app_id),
                copyAction = true,
            )

            AffiseTextField(
                affiseSettings.secretKey,
                label = stringResource(R.string.secret_key),
                copyAction = true,
            )

            AffiseTextField(
                affiseSettings.pushToken,
                label = stringResource(R.string.push_token),
                copyAction = true,
            )

            AffiseButton(text = stringResource(R.string.forget_all)) {
                Affise.forget("Demo App forget event")
            }

            AffiseButton(text = stringResource(R.string.crash_application)) {
                Affise.crashApplication()
            }

            Button(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                onClick = {
                },
            ) {
                Text("version v${BuildConfig.AFFISE_VERSION}")
            }
        }
    }
}

@Preview(showBackground = true, name = "Settings Screen Preview")
@Composable
fun SettingsScreenPreview() {
    AffiseAttributionLibTheme {
        SettingsScreen()
    }
}