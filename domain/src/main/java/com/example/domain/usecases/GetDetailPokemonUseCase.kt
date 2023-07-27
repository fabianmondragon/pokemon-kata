package com.example.domain.usecases

import com.example.domain.models.PokemonEvolutionAndDetailEntity
import com.example.domain.models.PokemonResponse
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun getDetailOfPokemon(pokemonId: String): Flow<PokemonResponse<PokemonEvolutionAndDetailEntity>> {
        return pokemonRepository.fetchPokemonDetailAndEvolution(pokemonId)
    }
}