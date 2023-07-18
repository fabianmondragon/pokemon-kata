package com.example.kata_pokemon.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.PokemonResponse
import com.example.domain.usecases.PokemonListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonViewModel @Inject constructor(private val pokemonListUseCase: PokemonListUseCase) :
    ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    fun getPokemons() {
        coroutineScope.launch {
            pokemonListUseCase.getPokemonList().collect{
                response -> when (response){
                    is PokemonResponse.Success -> {

                    }
                    else-> {

                    }
                }
            }
        }
    }
}