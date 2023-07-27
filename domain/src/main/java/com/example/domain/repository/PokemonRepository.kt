package com.example.domain.repository

import com.example.domain.models.PokemonEvolutionAndDetailEntity
import com.example.domain.models.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getListPokemon(): Flow<PokemonResponse<Any?>>
    suspend fun fetchPokemonDetailAndEvolution(pokemonId: String): Flow<PokemonResponse<PokemonEvolutionAndDetailEntity>>

}