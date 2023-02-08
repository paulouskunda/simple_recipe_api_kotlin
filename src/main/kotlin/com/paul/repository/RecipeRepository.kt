package com.paul.repository

import com.paul.entity.RecipeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository : JpaRepository<RecipeEntity, Int> {
}