package com.paul.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerUIConfig {
    @Bean
    fun customOpenApi(): OpenAPI{
        return OpenAPI()
    }
}