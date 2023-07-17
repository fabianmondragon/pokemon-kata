package com.example.data.datasources

import com.example.data.responses.PokemonListResponse
import com.example.domain.models.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getPokemonList(): Flow<PokemonResponse<PokemonListResponse?>>
}