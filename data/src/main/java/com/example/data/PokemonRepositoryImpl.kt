package com.example.data

import com.example.data.datasources.RemoteDataSource
import com.example.data.responses.LocationResponse
import com.example.data.utils.MapperDataPokemon
import com.example.data.utils.StringUtil
import com.example.domain.models.*
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
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

    @OptIn(FlowPreview::class)
    private suspend fun getDetailOfPokemon(pokemonId: String): Flow<PokemonResponse<Any?>> {
        val transformedData = remoteDataSource.getDetailOfPokemon(pokemonId).map { response ->
            when (response) {
                is PokemonResponse.Success -> {
                    val responseEntity =
                        MapperDataPokemon.convertDetailPokemonResponseToDetailPokemonEntity(response.data!!)
                    PokemonResponse.Success(responseEntity)
                }
                else -> response
            }
        }
        return transformedData
    }

    private suspend fun callToChainEvolution(id: String): Flow<PokemonResponse<Any?>?> {
        return getSpecies(id).flatMapConcat { speciesResponse ->
            when (speciesResponse) {
                is PokemonResponse.Success -> {
                    val urlSpecies = speciesResponse.data as String
                    remoteDataSource.getEvolutionChain(StringUtil.getId(urlSpecies))
                        .map { response ->
                            when (response) {
                                is PokemonResponse.Success -> {
                                    val responseEntity =
                                        MapperDataPokemon.convertToEvolutionChainResponseToEvolutionChainEntity(
                                            response.data!!
                                        )
                                    PokemonResponse.Success(responseEntity)
                                }
                                else -> response
                            }
                        }
                }
                else -> flowOf(speciesResponse)
            }
        }
    }

    private suspend fun getLocation(id: String): Flow<PokemonResponse<Any?>> {
        val transformData = remoteDataSource
            .getLocationArea(id).map { response ->
                when (response) {
                    is PokemonResponse.Success -> {
                        val responseEntity =
                            MapperDataPokemon.convertListLocationResponseToListLocationEntity(
                                response.data as List<LocationResponse>
                            )
                        PokemonResponse.Success(responseEntity)
                    }
                    else -> {
                        response
                    }
                }
            }
        return transformData
    }

    private suspend fun getSpecies(id: String): Flow<PokemonResponse<Any?>?> {
        val transformData = remoteDataSource.getSpecies(id)
            .map { response ->
                when (response) {
                    is PokemonResponse.Success -> {
                        PokemonResponse.Success(response.data)
                    }
                    else -> {
                        response
                    }
                }
            }
        return transformData
    }


    override suspend fun fetchPokemonDetailAndEvolution(pokemonId: String): Flow<PokemonResponse<PokemonEvolutionAndDetailEntity>> {
        val pokemonFlow: Flow<PokemonResponse<Any?>> = getDetailOfPokemon(pokemonId)
        val evolutionFlow: Flow<PokemonResponse<Any?>> =
            callToChainEvolution(pokemonId) as Flow<PokemonResponse<Any?>>
        val locationFlow: Flow<PokemonResponse<Any?>> = getLocation(pokemonId)
        val combinedFlow: Flow<Triple<PokemonResponse<Any?>, PokemonResponse<Any?>, PokemonResponse<Any?>>> =
            pokemonFlow.combine(evolutionFlow) { pokemonResponse, evolutionResponse ->
                pokemonResponse to evolutionResponse
            }.combine(locationFlow) { (pokemonResponse, evolutionResponse), locationResponse ->
                Triple(pokemonResponse, evolutionResponse, locationResponse)
            }

        return combinedFlow.map { (pokemonResponse, evolutionResponse, locationResponse) ->

            PokemonResponse.Success(
                PokemonEvolutionAndDetailEntity(
                    evolutionChainEntity = when (evolutionResponse) {
                        is PokemonResponse.Success -> evolutionResponse.data
                        else -> {
                            pokemonResponse
                        }
                    } as EvolutionChainEntity,
                    detailPokemonEntity = when (pokemonResponse) {
                        is PokemonResponse.Success -> pokemonResponse.data
                        else -> {
                            pokemonResponse
                        }
                    } as DetailPokemonResponseEntity,
                    locationResponseEntity = when (locationResponse) {
                        is PokemonResponse.Success -> locationResponse.data
                        else -> {
                            pokemonResponse
                        }
                    } as List<LocationEntity>

                )
            )
        }

    }


}

