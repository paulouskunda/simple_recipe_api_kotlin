package com.paul.controller

import com.paul.data.Recipe
import com.paul.service.RecipeService
import com.paul.util.RecipeUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
@RequestMapping("/api/recipe")
class RecipeController(
    private val recipeService: RecipeService
) {

    val logger = RecipeUtil().logger<RecipeController>()

    @PostMapping
    fun addRecipeEndpoint(@RequestBody recipe: Recipe): ResponseEntity<*> {
        logger.info("Add new Recipe {}", recipe)
        return recipeService.addRecipe(recipe)
    }

    @GetMapping
    fun fetchAllRecipesEndpoint(): ResponseEntity<*> {
        logger.info("Fetch All Recipes")
        return recipeService.getAllRecipe()
    }

    @GetMapping("/fetchRecipeById/{recipeId}")
    fun fetchRecipeByIdEndpoint(@PathVariable recipeId: Int): ResponseEntity<*> {
        logger.info("Fetch Recipe by Recipe Id {}", recipeId)
        return recipeService.getSingleRecipe(recipeId)
    }
}