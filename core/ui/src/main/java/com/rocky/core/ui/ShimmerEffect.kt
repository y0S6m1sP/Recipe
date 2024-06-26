package com.rocky.core.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffect(): Modifier = composed {
    val size = remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "transition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.value.width.toFloat(),
        targetValue = 2 * size.value.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = "startOffsetX"
    )
    val startOffsetY by transition.animateFloat(
        initialValue = -2 * size.value.height.toFloat(),
        targetValue = 2 * size.value.height.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ),
        label = "startOffsetY"
    )


    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFC8C8C8),
                Color(0xFFB8B5B5),
                Color(0xFFC8C8C8),
            ),
            start = Offset(startOffsetX, startOffsetY),
            end = Offset(
                startOffsetX + size.value.width,
                startOffsetY + size.value.height
            )
        )
    ).onGloballyPositioned {
        size.value = it.size
    }
}