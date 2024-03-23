package com.rocky.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun HalfScreenImage(modifier: Modifier = Modifier, model: String, contentDescription: String) {
    Column(modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(0.5f),
            model = model,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}