package com.affise.app.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.affise.app.R
import com.affise.app.ui.theme.AffiseAttributionLibTheme

data class DialogState(
    val title: String = "",
    val text: String = "",
    val onDismiss: () -> Unit = { },
)

@Composable
fun AffiseDialogsComponent(dialogs: SnapshotStateList<DialogState>) {
    LazyColumn {
        itemsIndexed(items = dialogs) { idx, dialog ->
            Dialog(dialog) {
                dialogs.removeAt(idx)
            }
        }
    }
}

@Composable
fun Dialog(dialog: DialogState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            dialog.onDismiss.invoke()
            onDismiss.invoke()
        },
        title = { Text(text = dialog.title) },
        text = { Text(text = dialog.text) },
        confirmButton = {
            Button(
                onClick = {
                    dialog.onDismiss.invoke()
                    onDismiss.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    color = Color.White
                )
            }
        }
    )
}

@Preview(showBackground = true, name = "Dialog Preview")
@Composable
fun DialogsComponentPreview() {
    val alertDialogs = remember {
        mutableStateListOf(DialogState(title = "Title", text = "Some text"))
    }

    AffiseAttributionLibTheme {
        AffiseDialogsComponent(alertDialogs)
    }
}