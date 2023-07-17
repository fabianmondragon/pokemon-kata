package com.example.domain.usecases

import com.example.domain.models.PokemonResponse
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListUseCase @Inject constructor (private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonList(): Flow<PokemonResponse<Any?>> {
        return pokemonRepository.getListPokemon()
    }


}