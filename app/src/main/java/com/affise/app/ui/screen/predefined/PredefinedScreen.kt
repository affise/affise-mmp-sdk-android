package com.affise.app.ui.screen.predefined

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.affise.app.Prefs
import com.affise.app.R
import com.affise.app.ui.MainActivity
import com.affise.app.ui.affiseSettings
import com.affise.app.ui.components.AffiseButton
import com.affise.app.ui.components.AffiseSwitch
import com.affise.app.ui.components.AffiseTextField
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.attribution.events.parameters.Predefined
import com.affise.attribution.events.parameters.PredefinedFloat
import com.affise.attribution.events.parameters.PredefinedLong

data class PredefinedData(val predefined: Predefined, val data: Any)

@Composable
fun PredefinedScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight(1f)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AffiseSwitch(
            affiseSettings.useCustomPredefined,
            label = stringResource(R.string.use_custom_predefined),
        ) {
            Prefs.applyBoolean(MainActivity.USE_CUSTOM_PREDEFINED, it)
        }

        NewPredefined()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            itemsIndexed(items = affiseSettings.predefinedData) { idx, predefined ->
                PredefinedCard(idx, predefined)
            }
        }
    }
}

@Composable
fun PredefinedCard(index: Int, predefined: PredefinedData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp), // Custom corner shape
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Shadow effect
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), // Adds a border
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
//                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${predefined.predefined.typeOfPredefined()}.",
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    )
                    Text(
                        predefined.predefined.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Text(
                    "${predefined.data}",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            IconButton(
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error),
                onClick = {
                    affiseSettings.predefinedData.remove(predefined)
                }
            ) {
                Icon(Icons.Filled.Clear, contentDescription = "Favorite")
            }
        }
    }
}

val predefineSelected = mutableStateOf<Predefined>(PredefinedFloat.REVENUE)

@Composable
fun NewPredefined() {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val predefineValue = mutableStateOf("")

        PredefinedSelect(predefineSelected = predefineSelected)

        AffiseTextField(
            predefineValue,
            readOnly = false,
            label = stringResource(R.string.predefined_value),
            keyboardOptions = when (predefineSelected.value) {
                is PredefinedFloat, is PredefinedLong -> KeyboardOptions(keyboardType = KeyboardType.Number)
                else -> KeyboardOptions.Default
            },
        )

        AffiseButton(
            text = stringResource(R.string.add_predefined),
            onClick = {
                val predefine = predefineSelected.value

                affiseSettings.predefinedData
                    .firstOrNull { it.predefined == predefine }
                    ?.let {
                        affiseSettings.predefinedData.remove(it)
                    }

                val predefineData = predefine.toPredefineData(predefineValue.value)

                if (predefineData != null) {
                    affiseSettings.predefinedData.add(PredefinedData(predefine, predefineData))
                } else {
                    Toast.makeText(
                        context,
                        "Wrong data type for \"${predefine.typeOfPredefined()}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}

@Composable
fun PredefinedSelect(
    predefineSelected: MutableState<Predefined>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = TextFieldDefaults.colors().unfocusedPlaceholderColor,
                containerColor = TextFieldDefaults.colors().unfocusedContainerColor,
            ),
            onClick = {
                expanded = !expanded
            }
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${predefineSelected.value.typeOfPredefined()}.",
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                )
                Text(
                    predefineSelected.value.toString(),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(0.8f),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            allPredefined.forEach {
                val name = it.nameOfPredefined()
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "${it.typeOfPredefined()}.",
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                            )
                            Text(
                                it.toString(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    },
                    onClick = {
                        predefineSelected.value = it
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Predefined Screen Preview")
@Composable
fun PredefinedScreenPreview() {
    AffiseAttributionLibTheme {
        PredefinedScreen()
    }
}