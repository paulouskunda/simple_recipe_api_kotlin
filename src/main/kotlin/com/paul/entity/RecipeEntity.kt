package com.paul.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.CreationTimestamp
import java.util.Date
import javax.persistence.*

@Entity
@Table(name ="recipe")
class RecipeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    var recipeId: Int? = null,
    @JsonProperty
    var recipeName: String? = null,
    @JsonProperty
    var recipeInstructions: String? = null,
    @JsonProperty
    var recipeImage: String? = null,
    @CreationTimestamp
    @JsonProperty
    var createdAt: Date? = null,
    @OneToMany(mappedBy = "recipe") var ingredientsEntity: List<IngredientsEntity>? = null
)