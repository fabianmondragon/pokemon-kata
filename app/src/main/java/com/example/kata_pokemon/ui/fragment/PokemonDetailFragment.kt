package com.example.kata_pokemon.ui.fragment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kata_pokemon.ui.screen.ShowPokemonDetailScreen
import com.example.kata_pokemon.viewmodels.PokemonDetailViewModel

@Composable
fun PokemonDetailFragment(
    nameOfPokemon: String,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    pokemonDetailViewModel.getDetailOfPokemon(nameOfPokemon)
    showDetailPokemon()
}

@Composable
fun showDetailPokemon() {
    ShowPokemonDetailScreen()
}