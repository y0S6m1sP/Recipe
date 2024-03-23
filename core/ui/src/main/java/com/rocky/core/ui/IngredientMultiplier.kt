package com.rocky.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IngredientMultiplier(
    modifier: Modifier = Modifier,
    onMultiplierChange: ((Int) -> Unit)? = null
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(
                text = stringResource(id = R.string.ingredients),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            /*Text(
                text = stringResource(id = R.string.servings),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp
            )*/
        }
        Spacer(modifier = Modifier.weight(1f))
        /*Multiplier(Modifier.width(160.dp)) {
            onMultiplierChange?.invoke(it)
        }*/
    }
}

/**
 * TheMealDB return string of measure which is not easy to multiply.
 * complete this feature after the basic feature is done.
 */
@Composable
fun Multiplier(
    modifier: Modifier = Modifier,
    onMultiplierChange: ((Int) -> Unit)? = null
) {
    val multiplier = remember { mutableIntStateOf(1) }
    Row(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MultiplierControlItem(imageRes = R.drawable.ic_minus) {
            if (multiplier.intValue > 1) {
                multiplier.intValue--
                onMultiplierChange?.invoke(multiplier.intValue)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${multiplier.intValue}")
        Spacer(modifier = Modifier.weight(1f))
        MultiplierControlItem(imageRes = R.drawable.ic_plus) {
            multiplier.intValue++
            onMultiplierChange?.invoke(multiplier.intValue)
        }
    }
}

@Composable
private fun MultiplierControlItem(
    modifier: Modifier = Modifier,
    imageRes: Int,
    onItemClick: () -> Unit
) {
    Image(
        modifier = modifier.clickable {
            onItemClick()
        },
        painter = painterResource(id = imageRes),
        contentDescription = null
    )
}