package com.rocky.core.model

data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String? = null,
    val strType: String? = null
) {
    fun getImageUrl() = "https://www.themealdb.com/images/ingredients/${strIngredient}.png"
}
