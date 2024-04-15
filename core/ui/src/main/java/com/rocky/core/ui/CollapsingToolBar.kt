package com.rocky.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlin.math.roundToInt

@Composable
fun CollapsingToolBar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navigationIcon: @Composable (modifier: Modifier) -> Unit = {
        Icon(
            modifier = it,
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "navigationIcon",
        )
    },
    imageUrl: String,
    collapsingToolBarHeight: Dp = 300.dp,
    onNavigationIconClick: (() -> Unit)? = null,
    content: LazyListScope.() -> Unit
) {
    val toolBarHeight = 56.dp + paddingValues.calculateTopPadding()

    val maxHeightPx = with(LocalDensity.current) {
        collapsingToolBarHeight.roundToPx().toFloat() - toolBarHeight.roundToPx()
            .toFloat() - paddingValues.calculateTopPadding().toPx()
    }
    val minHeightPx = 0f
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-maxHeightPx, minHeightPx)
                if (toolbarOffsetHeightPx.floatValue == -maxHeightPx || toolbarOffsetHeightPx.floatValue == minHeightPx) {
                    return Offset.Zero
                }
                return available
            }
        }
    }

    Box(modifier = Modifier
        .height(collapsingToolBarHeight)
        .offset {
            IntOffset(x = 0, y = toolbarOffsetHeightPx.floatValue.roundToInt())
        }
        .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.height(collapsingToolBarHeight),
            model = imageUrl,
            contentDescription = "background",
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = -toolbarOffsetHeightPx.floatValue.roundToInt()
                    )
                }
                .height(toolBarHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationIcon(
                Modifier
                    .clickable {
                        onNavigationIconClick?.invoke()
                    }
                    .padding(top = 24.dp, start = 12.dp))
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .offset {
                IntOffset(
                    x = 0,
                    y = toolbarOffsetHeightPx.floatValue.roundToInt()
                            + collapsingToolBarHeight.roundToPx()
                            - paddingValues.calculateTopPadding().roundToPx()
                )
            }
            .nestedScroll(nestedScrollConnection)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = 24.dp,
                bottom = 24.dp + paddingValues.calculateBottomPadding() + paddingValues
                    .calculateTopPadding()
                    .times(2f),
                start = 24.dp,
                end = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        content()
    }
}