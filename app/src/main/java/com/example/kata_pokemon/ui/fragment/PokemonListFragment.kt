package com.example.kata_pokemon.ui.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kata_pokemon.ui.screen.showPokemonListScreen
import com.example.kata_pokemon.viewmodels.PokemonViewModel


@Composable
fun PokemonListFragment(
    pokemonViewModel: PokemonViewModel = hiltViewModel()
) {
    pokemonViewModel.getPokemons()
    val listOfPokemons by pokemonViewModel.listState.collectAsState(initial = emptyList())
    showPokemonListScreen(listOfPokemons)
}