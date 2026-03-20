package com.affise.app.ui.screen.api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.affise.app.R
import com.affise.app.ui.components.AffiseButton
import com.affise.app.ui.components.AffiseTextField
import com.affise.app.ui.factories.apiFactory
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.attribution.Affise


private val outputState = mutableStateOf("")
private val urlState = mutableStateOf("")

private val apis: List<Pair<String, () -> Unit>> = apiFactory {
    output(it)
}

@Composable
fun ApiScreen(modifier: Modifier = Modifier) {
    Column {
        AffiseTextField(
            outputState,
            label = stringResource(R.string.api_output),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(items = apis) { api ->
                AffiseButton(
                    text = api.first,
                    onClick = api.second
                )
            }

            if (Affise.Module.Link.hasModule()) {
                item {
                    ModuleLink(modifier = modifier)
                }
            }
        }
    }
}

fun output(text: String) {
    if (outputState.value.isNotEmpty()) {
        outputState.value += "\n"
    }
    outputState.value += text
}

@Composable
fun ModuleLink(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AffiseTextField(
            urlState,
            readOnly = false,
            maxLines = 1,
            label = stringResource(R.string.resolve_url),
        )

        AffiseButton(
            text = stringResource(R.string.module_link_resolve).uppercase(),
            onClick = {
                Affise.Module.Link.resolve(urlState.value) {
                    output("linkResolve: $it")
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Api Screen Preview")
@Composable
fun ApiScreenPreview() {
    AffiseAttributionLibTheme {
        ApiScreen()
    }
}