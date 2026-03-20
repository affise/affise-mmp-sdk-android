package com.affise.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.affise.app.R
import com.affise.app.ui.components.AffiseTabPagerComponent
import com.affise.app.ui.screen.api.ApiScreen
import com.affise.app.ui.screen.events.EventsScreen
import com.affise.app.ui.screen.store.StoreScreen
import com.affise.app.ui.screen.web.WebScreen
import com.affise.app.ui.selectedTabState

@Composable
fun TabsView(modifier: Modifier = Modifier) {
    AffiseTabPagerComponent(
        modifier = modifier,
        selectedTabState = selectedTabState,
        titles = listOf(
            stringResource(R.string.api),
            stringResource(R.string.events),
            stringResource(R.string.web_events),
            stringResource(R.string.store),
        ),
        content = listOfNotNull(
            {
                ApiScreen()
            },
            {
                EventsScreen()
            },
            {
                WebScreen()
            },
            {
                StoreScreen()
            },
        )
    )
}
