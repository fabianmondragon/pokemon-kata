package com.example.kata_pokemon.ui.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.PokemonEvolutionAndDetailEntity
import com.example.kata_pokemon.ui.models.DetailPokemonUi
import com.example.kata_pokemon.ui.screen.ShowPokemonDetailScreen
import com.example.kata_pokemon.viewmodels.PokemonDetailViewModel

@Composable
fun PokemonDetailFragment(
    nameOfPokemon: String,
    pokemonId: String,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val respond by pokemonDetailViewModel.detailPokemonUi.collectAsState(initial = PokemonEvolutionAndDetailEntity())

    pokemonDetailViewModel.getDetailOfPokemon(pokemonId)
    val dataToDetail = organizeData(respond, pokemonDetailViewModel)
    if (dataToDetail != null) {
        showDetailPokemon(dataToDetail, pokemonId)
    }
}

@Composable
fun showDetailPokemon(
    respond: DetailPokemonUi,
    pokemonId: String
) {
    if (respond.name.isNotEmpty())
        ShowPokemonDetailScreen(respond)
}

fun organizeData(
    respond: PokemonEvolutionAndDetailEntity,
    pokemonDetailViewModel: PokemonDetailViewModel
) = pokemonDetailViewModel.organizeData(respond)
