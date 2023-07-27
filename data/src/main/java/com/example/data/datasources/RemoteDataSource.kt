package com.example.data.datasources

import com.example.data.responses.*
import com.example.domain.models.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getPokemonList(): Flow<PokemonResponse<PokemonListResponse?>>
    suspend fun getDetailOfPokemon(pokemonId: String): Flow<PokemonResponse<DetailPokemonResponse?>>
    suspend fun getEvolutionChain(id: String): Flow<PokemonResponse<EvolutionChainResponse?>>
    suspend fun getLocationArea(id: String): Flow<PokemonResponse<List<LocationResponse?>?>>
    suspend fun getSpecies(id: String): Flow<PokemonResponse<String>>

}