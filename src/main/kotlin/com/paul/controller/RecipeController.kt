package com.paul.controller

import com.paul.data.Recipe
import com.paul.service.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipe")
class RecipeController(
    private val recipeService: RecipeService
) {


    @PostMapping
    fun addRecipeEndpoint(@RequestBody recipe: Recipe): ResponseEntity<Any> {
        return recipeService.addRecipe(recipe)
    }

    @GetMapping
    fun fetchAllRecipesEndpoint(): ResponseEntity<Any> {
        return recipeService.getAllRecipe()
    }

    @GetMapping("/fetchRecipeById/{recipeId}")
    fun fetchRecipeByIdEndpoint(@PathVariable recipeId: Int): ResponseEntity<Any> {
        return recipeService.getSingleRecipe(recipeId)
    }
}