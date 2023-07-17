package com.example.data

import com.example.data.datasources.RemoteDataSource
import com.example.data.utils.MapperDataPokemon
import com.example.domain.models.PokemonResponse
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : PokemonRepository {

    override suspend fun getListPokemon(): Flow<PokemonResponse<Any?>> {

        val transformedData = remoteDataSource.getPokemonList().map { response ->
            when (response) {
                is PokemonResponse.Success -> {
                    val pokemonListEntity =
                        MapperDataPokemon
                            .convertPokemonListResponseToPokemonListEntity(response.data!!)
                    PokemonResponse.Success(pokemonListEntity)
                }
                else -> response
            }
        }
        return transformedData
    }
}