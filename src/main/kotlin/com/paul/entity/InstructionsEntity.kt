package com.paul.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name ="instructions")
data class InstructionsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var instructionsId: Int? = null,
    @ManyToOne
    @JoinColumn(name = "recipeId")
    @JsonIgnore
    var recipeKey: RecipeEntity? = null,
    @CreationTimestamp
    @JsonIgnore
    var createdAt: Date? = null,
    var instructionDetails: String? = null
)