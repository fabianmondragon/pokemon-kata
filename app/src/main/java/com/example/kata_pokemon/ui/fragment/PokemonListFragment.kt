package com.example.kata_pokemon.ui.fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.models.PokemonItemEntity
import com.example.kata_pokemon.ui.screen.showPokemonListScreen
import com.example.kata_pokemon.utils.Route
import com.example.kata_pokemon.viewmodels.PokemonViewModel

@Composable
fun PokemonListFragment(
    pokemonViewModel: PokemonViewModel = hiltViewModel(),
    navController: NavController
) {
    pokemonViewModel.getPokemons()
    val listOfPokemons by pokemonViewModel.listState.collectAsState(initial = emptyList())
    showPokemonListScreen(listOfPokemons, ::goToDetailOfPokemon, navController)
}

fun goToDetailOfPokemon(itemEntity: PokemonItemEntity? = null, navController: NavController) {
    if (itemEntity != null) {
        navController.navigate("${Route.Detail.route}/${itemEntity.name}/${itemEntity.id}")
    }
}