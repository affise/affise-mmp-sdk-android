package com.affise.app.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AffiseFab(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        shape = CircleShape,
        containerColor = containerColor,
        onClick = onClick,
        modifier = modifier
    ) {
        content()
    }
}