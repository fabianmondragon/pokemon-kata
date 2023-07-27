package com.example.domain.models


data class PokemonEvolutionAndDetailEntity(
    val evolutionChainEntity: EvolutionChainEntity? = null,
    val detailPokemonEntity: DetailPokemonResponseEntity? = null,
    val locationResponseEntity: List<LocationEntity> = listOf()
)