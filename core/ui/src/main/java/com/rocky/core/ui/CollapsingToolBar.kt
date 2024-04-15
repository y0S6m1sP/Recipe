package com.rocky.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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

private val toolBarHeight = 80.dp

@Composable
fun CollapsingToolBar(
    modifier: Modifier = Modifier,
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
    val maxHeightPx = with(LocalDensity.current) {
        collapsingToolBarHeight.roundToPx().toFloat() - toolBarHeight.roundToPx().toFloat()
    }
    val minHeightPx = 0f
    val toolbarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-maxHeightPx, minHeightPx)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = collapsingToolBarHeight)
        ) {
            content()
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
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    navigationIcon(Modifier.clickable {
                        onNavigationIconClick?.invoke()
                    })
                }
            }
        }
    }
}