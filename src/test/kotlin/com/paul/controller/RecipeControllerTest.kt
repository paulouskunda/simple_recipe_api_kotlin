package com.paul.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.paul.data.Recipe
import com.paul.data.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

internal class RecipeControllerTest(
    @Autowired val restTemplate: TestRestTemplate
) {

    @Test
    fun addRecipeEndpoint() {

    }

    @Test
    fun fetchAllRecipesEndpoint() {
        val entity = restTemplate.getForEntity<Response>("/api/recipe")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.statusCode).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun fetchRecipeByIdEndpoint() {
        val entity = restTemplate.getForEntity<Response>("/api/recipe/fetchRecipeById/2")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.statusCode).isEqualTo(HttpStatus.OK.value())
    }
}