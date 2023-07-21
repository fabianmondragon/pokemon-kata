package com.example.data.services

import com.example.data.responses.PokemonListResponse
import com.example.data.responses.SpritesPokemonResponse
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

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): SpritesPokemonResponse

}