package com.affise.app.ui.screen.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.affise.app.ui.affiseSettings
import com.affise.app.ui.components.AffiseButton
import com.affise.app.ui.factories.DefaultEventsFactory
import com.affise.app.ui.factories.SimpleEventsFactory
import com.affise.app.ui.screen.predefined.applyPredefines
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.app.ui.utils.toNormalCase
import com.affise.attribution.events.Event
import com.affise.attribution.events.subscription.BaseSubscriptionEvent


private val defaultEvents: List<Event> = DefaultEventsFactory().createEvents()
private val simpleEvents: List<Event> = SimpleEventsFactory().createEvents()

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
) {
    val events: List<Event> = if (affiseSettings.useCustomPredefined.value) {
        simpleEvents
    } else {
        defaultEvents
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(items = events) { event ->
            ButtonEvent(event)
        }
    }
}


@Composable
fun ButtonEvent(event: Event, modifier: Modifier = Modifier) {
    val color = when (event) {
        is BaseSubscriptionEvent -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    val name = when (event) {
        is BaseSubscriptionEvent -> event.subtype.replace("_", " ")
        else -> event.getName().toNormalCase()
    }.uppercase()

    AffiseButton(
        modifier = modifier,
        text = name,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        onClick = {
            applyCustomPredefines(event)

            // Send event
            event.send()
//            // or Send event now
//            event.sendNow({
//                // handle event send success
//            }) { errorResponse ->
//                // handle event send failed
//                // 🟥Warning:🟥 event is NOT cached for later send
//            }
        }
    )
}

fun applyCustomPredefines(event: Event) {
    if (!affiseSettings.useCustomPredefined.value) return

    event.applyPredefines(affiseSettings.predefinedData.toList())
}

@Preview(showBackground = true, name = "Buttons Screen Preview")
@Composable
fun EventsScreenPreview() {
    AffiseAttributionLibTheme {
        EventsScreen()
    }
}