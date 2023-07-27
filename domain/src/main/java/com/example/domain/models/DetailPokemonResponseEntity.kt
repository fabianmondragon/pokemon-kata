package com.example.domain.models

data class DetailPokemonResponseEntity(
    var abilities: List<AbilityEntity> = listOf(),
    var moves: List<MoveEntity> = listOf(),
    var name: String = "",
    var sprites: SpritesEntity? = null,
    var types: List<TypeEntity> = listOf(),
    var weight: Int = 0,
    var id: String = ""
)
