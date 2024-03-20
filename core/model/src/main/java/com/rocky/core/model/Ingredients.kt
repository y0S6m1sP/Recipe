package com.rocky.core.model

data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String? = null,
    val strType: String? = null
)
