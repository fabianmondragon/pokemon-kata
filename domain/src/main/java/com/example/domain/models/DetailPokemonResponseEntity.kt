package com.example.domain.models

data class DetailPokemonResponseEntity(
    val abilities: List<AbilityEntity>,
    val moves: List<MoveEntity>,
    val name: String,
    val sprites: SpritesEntity,
    val types: List<TypeEntity>,
    val weight: Int
)
