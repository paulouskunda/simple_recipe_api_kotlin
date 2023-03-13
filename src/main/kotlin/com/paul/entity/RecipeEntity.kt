package com.paul.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import java.util.Date
import javax.persistence.*
import javax.xml.stream.events.ProcessingInstruction

@Entity
@Table(name ="recipe")
data class RecipeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var recipeId: Int? = null,
    var recipeName: String? = null,
    var recipeInstructions: String? = null,
    var recipeImage: String? = null,
    @CreationTimestamp
    var createdAt: Date? = null,
    @OneToMany(mappedBy = "recipe") var ingredientsEntity: List<IngredientsEntity>? = null,
    @OneToMany(mappedBy = "recipeKey") var instructionsEntity: List<InstructionsEntity>? = null
)