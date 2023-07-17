package com.example.data.responses

class PokemonListResponse (
    val count: Int,
    val next: String?,
    val previous: String,
    val results: List<PokemonResult>)

