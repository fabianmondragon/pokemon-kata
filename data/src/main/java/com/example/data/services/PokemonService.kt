package com.example.data.services

import com.example.data.responses.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getDetailOfPokemon(@Path("id") name: String): Response<DetailPokemonResponse>

    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") id: String): Response<SpeciesResponse>

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") id: String): Response<EvolutionChainResponse>

    @GET("pokemon/{id}/encounters")
    suspend fun getLocations(@Path("id") id: String): Response<List<LocationResponse>>

}