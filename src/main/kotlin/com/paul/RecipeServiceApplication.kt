package com.paul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecipeServiceApplication

fun main(args: Array<String>) {
	runApplication<RecipeServiceApplication>(*args)
}
