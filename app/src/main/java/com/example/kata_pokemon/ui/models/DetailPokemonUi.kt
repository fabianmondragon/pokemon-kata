package com.example.kata_pokemon.ui.models

data class DetailPokemonUi(
    val abilities: String,
    var moves: String,
    var name: String = "",
    var sprites: String = "",
    var types: String,
    var weight: Int = 0,
    var url: String = "",
    var id: String = "",
    val location: String,
    val evolution: String

)