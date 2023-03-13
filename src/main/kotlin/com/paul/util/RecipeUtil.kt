package com.paul.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RecipeUtil {
    inline fun <reified T> logger(): Logger {
        return LoggerFactory.getLogger(T::class.java)
    }
}