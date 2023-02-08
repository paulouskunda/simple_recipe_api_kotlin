package com.paul.data

data class Recipe (
    var recipeName: String?,
    var recipeInstructions: String?,
    var recipeImage: String?,
    var ingredients: List<Ingredients>
)