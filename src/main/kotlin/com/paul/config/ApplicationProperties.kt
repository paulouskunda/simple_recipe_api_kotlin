package com.paul.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties(prefix = "config")
@ConstructorBinding
data class ApplicationProperties(
     val internalServerError: String,
     val illegalArguments: String,
     val badRequest: String,
     val methodNotAllowed: String
)