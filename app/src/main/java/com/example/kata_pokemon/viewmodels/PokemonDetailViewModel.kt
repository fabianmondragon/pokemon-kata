package com.example.kata_pokemon.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.PokemonResponse
import com.example.domain.usecases.GetDetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase
): ViewModel() {
    private val coroutineScope =  CoroutineScope(Dispatchers.IO)

    fun getDetailOfPokemon(nameOfPokemon: String){
        coroutineScope.launch {
            getDetailPokemonUseCase.getDetailOfPokemon(nameOfPokemon).collect{
                response -> when (response) {
                    is PokemonResponse.Success -> {
                    }
                    else -> {
                    }
                }
            }
        }

    }




}