package com.rocky.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rocky.core.model.Ingredients

@Composable
fun IngredientItem(modifier: Modifier = Modifier, ingredient: Ingredients) {
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = ingredient.getImageUrl(),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = ingredient.strIngredient)
        Spacer(modifier = Modifier.weight(1f))
        ingredient.strDescription?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun IngredientLoadingItem(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.width(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect(),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun IngredientsItemPreview() {
    IngredientItem(ingredient = Ingredients("1", "Flour", "200g"))
}