package com.example.data.datasources

import com.example.data.responses.PokemonListResponse
import com.example.domain.models.PokemonResponse
import com.example.data.services.PokemonService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val pokemonService: PokemonService
) : RemoteDataSource {
    override suspend fun getPokemonList(): Flow<PokemonResponse<PokemonListResponse?>> =
        pokemonService.getPokemonList(1, 100)
            .map { response ->
                if (response.isSuccessful) {
                    PokemonResponse.Success(response.body())
                } else {
                    val error = IOException("Error ${response.code()}: ${response.message()}")
                    PokemonResponse.Error(error)
                }
            }
}
