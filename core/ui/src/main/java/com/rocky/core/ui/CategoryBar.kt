package com.rocky.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun CategoryBar(
    modifier: Modifier = Modifier,
    onLabelSelected: ((String) -> Unit)? = null
) {
    val selectedItem = remember { mutableIntStateOf(0) }
    val labelList = listOf("breakfast", "lunch", "dinner", "snack")
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(labelList) { index, label ->
            CategoryItem(
                text = label,
                isSelected = selectedItem.intValue == index,
                onLabelSelected = {
                    selectedItem.intValue = index
                    onLabelSelected?.invoke(it)
                })
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onLabelSelected: ((String) -> Unit)? = null
) {
    Text(
        modifier = modifier
            .clickable {
                onLabelSelected?.invoke(text)
            },
        text = text,
        fontSize = 16.sp,
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    )
}