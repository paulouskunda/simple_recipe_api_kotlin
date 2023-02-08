package com.paul.service

import com.paul.data.Recipe
import com.paul.data.Response
import com.paul.entity.IngredientsEntity
import com.paul.entity.RecipeEntity
import com.paul.repository.IngredientsRepository
import com.paul.repository.RecipeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RecipeService (
    private val ingredientsRepository: IngredientsRepository,
    private val recipeRepository: RecipeRepository
) {

    fun addRecipe(recipe:Recipe): ResponseEntity<Any>{
        val recipeEntity = recipeRepository.save(RecipeEntity(null, recipe.recipeName,
            recipe.recipeInstructions, recipe.recipeImage, null))
        val ingredientsList = mutableListOf<IngredientsEntity>()
        recipe.ingredients.forEach {
            val ingredientsEntity = ingredientsRepository.save(IngredientsEntity(null,recipeEntity,null
                ,it.ingredientsDetails))
            ingredientsList.add(ingredientsEntity)
        }
        recipeEntity.ingredientsEntity=ingredientsList
        return ResponseEntity.ok(Response(HttpStatus.OK.value(), recipeEntity.toString()))
    }

    fun getAllRecipe(): ResponseEntity<Any>{
        return ResponseEntity.ok(Response(HttpStatus.OK.value(), recipeRepository.findAll()))
    }

    fun getSingleRecipe(recipeId: Int): ResponseEntity<Any>{
        val responseEntity: ResponseEntity<Any>
        val singleRecipes = recipeRepository.findById(recipeId)
        responseEntity = if (singleRecipes.isPresent){
            ResponseEntity.ok(Response(HttpStatus.OK.value(), singleRecipes.get()))
        }else{
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response(HttpStatus.NOT_FOUND.value(), "No recipe was found with ID :[$recipeId]"))
        }
        return responseEntity
    }




}