package com.example.domain.models

data class PokemonListEntity (
    val count: Int,
    val next: String?,
    val previous: String,
    val results: List<PokemonItemEntity>
        )