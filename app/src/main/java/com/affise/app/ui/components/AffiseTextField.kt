package com.affise.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffiseTextField(
    value: MutableState<String>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = true,
    label: String = "",
    maxLines: Int = 4,
    copyAction: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
            onValueChange.invoke(it)
        },
        readOnly = readOnly,
        minLines = 1,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color(0x00ffffff),
            focusedIndicatorColor = Color(0x00ffffff),
        ),
        placeholder = {
            if (label.isNotEmpty()) {
                Text(label)
            }
        },
        trailingIcon = {
            trailingIcon?.invoke()

            if (value.value.isNotEmpty()) {
                if (copyAction) {
                    val clipboardManager: ClipboardManager = LocalClipboardManager.current
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                clipboardManager.setText(AnnotatedString(value.value))
                            }
                    )
                } else {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                value.value = ""
                                onValueChange.invoke("")
                            }
                    )
                }
            }
        },
    )
}