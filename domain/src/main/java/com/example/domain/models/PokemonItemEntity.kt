package com.example.domain.models

data class PokemonItemEntity(
    val name: String,
    val url: String,
    val image: String,
    val id: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name",
            "$name ",
            "${name.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}