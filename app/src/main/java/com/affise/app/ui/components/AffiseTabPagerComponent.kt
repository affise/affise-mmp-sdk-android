package com.affise.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AffiseTabPagerComponent(
    titles: List<String>,
    content: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    selectedTabState: MutableIntState? = null,
) {
    val currentSelectedTabState = selectedTabState ?: remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { titles.size }

    LaunchedEffect(currentSelectedTabState.intValue) {
        pagerState.animateScrollToPage(currentSelectedTabState.intValue)
    }

    Column(modifier) {
        TabRow(
            modifier = modifier,
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    modifier = modifier,
                    selected = pagerState.currentPage == index,
                    onClick = { currentSelectedTabState.intValue = index },
                    text = {
                        Text(
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content.getOrNull(index)?.invoke()
            }
        }
    }
}
