package com.affise.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AffiseButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = colors,
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            maxLines = maxLines,
            overflow = overflow
        )
    }
}