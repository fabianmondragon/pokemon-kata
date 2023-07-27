package com.example.data.datasources

import com.example.data.responses.*
import com.example.domain.models.PokemonResponse
import com.example.data.services.PokemonService
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val pokemonService: PokemonService
) : RemoteDataSource {
    override suspend fun getPokemonList(): Flow<PokemonResponse<PokemonListResponse?>> {
        val response = pokemonService.getPokemonList(0, 150)
        if (response.isSuccessful) {
            return flow {

                response.body()?.results?.forEach {
                    it.image = getImage(it.url)
                }
                emit(PokemonResponse.Success(response.body()))
            }
        }
        val error = IOException("Error ${response.code()}: ${response.message()}")
        return flow {
            emit(PokemonResponse.Error(error))
        }
    }

    private fun getImage(url: String): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    override suspend fun getDetailOfPokemon(pokemonId: String): Flow<PokemonResponse<DetailPokemonResponse?>> {
        val response = pokemonService.getDetailOfPokemon(pokemonId)
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

    override suspend fun getEvolutionChain(id: String): Flow<PokemonResponse<EvolutionChainResponse?>> {
        val response = pokemonService.getEvolutionChain(id)
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

    override suspend fun getLocationArea(id: String): Flow<PokemonResponse<List<LocationResponse?>?>> {
        val response = pokemonService.getLocations(id)
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

    override suspend fun getSpecies(id: String): Flow<PokemonResponse<String>> {
        val response = pokemonService.getSpecies(id)
        if (response.isSuccessful) {
            return flow {
                response.body()?.evolutionChainResponse?.let { PokemonResponse.Success(it.url) }
                    ?.let { emit(it) }
            }
        }
        val error = IOException("Error ${response.code()}: ${response.message()}")
        return flow {
            emit(PokemonResponse.Error(error))
        }
    }


}
