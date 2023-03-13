package com.paul.service

import com.paul.data.Recipe
import com.paul.data.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeServiceTest(
    @Autowired val recipeService: RecipeService
) {

    @Test
    fun addRecipe() {
    }

    @Test
    fun getAllRecipe() {
        val responseBody = recipeService.getAllRecipe()
        assertThat(responseBody.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseBody.body is Response)
        val response = responseBody.body as Response
        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.message is List<*>)
    }

    @Test
    fun getSingleRecipe() {
        val responseBody = recipeService.getAllRecipe()
        assertThat(responseBody.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseBody.body is Response)
        val response = responseBody.body as Response
        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.message is Recipe)
    }
}