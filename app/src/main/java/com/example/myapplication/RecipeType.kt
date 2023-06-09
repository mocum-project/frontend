package com.example.myapplication

data class RecipeResponseType(
    val recipes:List<RecipeType>
)

data class RecipeType (
    val name: String,
    val category: String,
    val availableIngredients: List<String>,
    val missingIngredients: List<String>,
    val youtube: Youtube
)

data class Youtube(
    val videoUrl: String,
    val title: String,
    val thumbnailUrl: String
)

