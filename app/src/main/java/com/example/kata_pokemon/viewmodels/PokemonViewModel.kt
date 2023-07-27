package com.example.kata_pokemon.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.PokemonItemEntity
import com.example.domain.models.PokemonResponse
import com.example.domain.usecases.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonListUseCase: PokemonListUseCase) :
    ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _listState:
            MutableStateFlow<List<PokemonItemEntity>> = MutableStateFlow(listOf())
    val listState = _listState.asStateFlow()
    fun getPokemons() {
        coroutineScope.launch {
            pokemonListUseCase.getPokemonList().collect { response ->
                when (response) {
                    is PokemonResponse.Success -> {
                        val result = response.data as List<PokemonItemEntity>
                        _listState.value = result
                    }
                    else -> {

                    }
                }
            }
        }
    }
}