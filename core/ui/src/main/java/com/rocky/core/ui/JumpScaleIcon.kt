package com.rocky.core.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun JumpScaleIcon(
    modifier: Modifier = Modifier,
    defaultScaleDp: Dp,
    jumpScaleDp: Dp,
    imageVector: ImageVector,
    tint: Color,
    onClick: () -> Unit
) {
    var change by remember { mutableStateOf(false) }

    val iconSize by animateDpAsState(
        targetValue = if (change) jumpScaleDp else defaultScaleDp,
        animationSpec = tween(200),
        label = "iconSize"
    )

    if (iconSize == jumpScaleDp) {
        change = false
    }

    Box(
        modifier = modifier
            .clickable {
                change = true
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = imageVector,
            contentDescription = "icon",
            tint = tint
        )
    }
}