package com.paul.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.paul.data.Ingredients
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*


@Entity
@Table(name ="ingredients")
data class IngredientsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ingredientsId: Int? = null,
    @ManyToOne
    @JoinColumn(name = "recipeId")
    @JsonIgnore
    var recipe: RecipeEntity? = null,
    @CreationTimestamp
    var createdAt: Date? = null,
    var ingredientsDetail: String? = null

)