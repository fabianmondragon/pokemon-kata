package com.example.data.datasources

import com.example.data.responses.PokemonListResponse
import com.example.domain.models.PokemonResponse
import com.example.data.services.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val pokemonService: PokemonService
) : RemoteDataSource {
    override suspend fun getPokemonList(): Flow<PokemonResponse<PokemonListResponse?>> {
        val response = pokemonService.getPokemonList(0, 150)
        if (response.isSuccessful) {
            return flow {
                emit(PokemonResponse.Success(response.body()))
            }
        }
        val error = IOException("Error ${response.code()}: ${response.message()}")
        return flow {
            emit(PokemonResponse.Error(error))
        }
    }
}
