package com.paul.data

data class Recipe (
    var recipeName: String?,
    var recipeInstructions: String?,
    var recipeImage: String?,
    var ingredients: List<Ingredients>,
    var instructions: List<Instructions>
)

class Ingredients (
    var ingredientsDetails: String
)

class Instructions (
    var instructionsDetails: String
)