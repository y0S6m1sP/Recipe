package com.rocky.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
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
    val labelList = listOf("Breakfast", "Lunch", "Dinner", "Snack")
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
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
            }
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.secondaryContainer else unselectedChipBackground)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = if (isSelected) MaterialTheme.colorScheme.secondary else onUnselectedChip
    )
}