package com.paul.service

import com.paul.data.Ingredients
import com.paul.data.Instructions
import com.paul.data.Recipe
import com.paul.data.Response
import com.paul.entity.IngredientsEntity
import com.paul.entity.InstructionsEntity
import com.paul.entity.RecipeEntity
import com.paul.repository.IngredientsRepository
import com.paul.repository.InstructionsRepository
import com.paul.repository.RecipeRepository
import com.paul.util.RecipeUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class RecipeService (
    private val ingredientsRepository: IngredientsRepository,
    private val recipeRepository: RecipeRepository,
    private val instructionsRepository: InstructionsRepository
) {

    val logger = RecipeUtil().logger<RecipeService>()

    fun addRecipe(recipe:Recipe): ResponseEntity<*>{
        val recipeEntity = recipeRepository.save(RecipeEntity(null, recipe.recipeName,
            recipe.recipeInstructions, recipe.recipeImage, null))
        logger.info("Recipe Saved {}", recipeEntity)

        recipeEntity.ingredientsEntity=addIngredients(recipe, recipeEntity)
        recipeEntity.instructionsEntity = addInstructions(recipe, recipeEntity)
        return ResponseEntity.ok(Response(HttpStatus.OK.value(), recipeEntity))
    }

    fun getAllRecipe(): ResponseEntity<*>{
        return ResponseEntity.ok(Response(HttpStatus.OK.value(), recipeRepository.findAll()))
    }

    fun getSingleRecipe(recipeId: Int): ResponseEntity<*>{

        val responseEntity: ResponseEntity<*>
        val singleRecipes = recipeRepository.findById(recipeId)
        logger.info("Single Fetched Recipe")
        responseEntity = if (singleRecipes.isPresent){
            logger.info("Recipe found with Id {} Recipe found -> {}", recipeId , singleRecipes)
            ResponseEntity.ok(Response(HttpStatus.OK.value(), singleRecipes.get()))
        }else{
            logger.info("We did not found a recipe with ID {}", recipeId)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response(HttpStatus.NOT_FOUND.value(), "No recipe was found with ID :[$recipeId]"))
        }
        return responseEntity
    }


    fun addIngredients(recipe: Recipe, recipeEntity: RecipeEntity): List<IngredientsEntity>{
        val ingredientsList = mutableListOf<IngredientsEntity>()

        recipe.ingredients.forEach {
            val ingredientsEntity = ingredientsRepository.save(IngredientsEntity(null,recipeEntity,null
                ,it.ingredientsDetails))
            ingredientsList.add(ingredientsEntity)
            logger.info("Saved Ingredients {}", ingredientsEntity)
        }
        logger.info("Saved ingredients size {}", ingredientsList.size)
        return  ingredientsList
    }

    fun addInstructions(recipe: Recipe, recipeEntity: RecipeEntity): List<InstructionsEntity>{
        val instructionsDetailsList = mutableListOf<InstructionsEntity>()

        recipe.instructions.forEach {
            val instructionsEntity = instructionsRepository.save(InstructionsEntity(null, recipeEntity,
                null, it.instructionsDetails))
            instructionsDetailsList.add(instructionsEntity)
            logger.info("Saved Instruction {}", instructionsEntity)

        }
        logger.info("Saved Instructions size {}", instructionsDetailsList.size)


        return instructionsDetailsList
    }



}