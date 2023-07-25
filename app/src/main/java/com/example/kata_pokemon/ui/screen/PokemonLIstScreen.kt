package com.example.kata_pokemon.ui.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.PokemonItemEntity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.navigation.NavController
import com.example.kata_pokemon.utils.LoadImageFromUrl
import kotlin.reflect.KFunction2


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun showPokemonListScreen(
    listOfPokemon: List<PokemonItemEntity>,
    goToDetailOfPokemon: KFunction2<PokemonItemEntity?, NavController, Unit>,
    navController: NavController,
) {

    Scaffold(
        content = {
            BuildListScreen(listOfPokemon, goToDetailOfPokemon, navController)
        }
    )

}

@Composable
fun BuildListScreen(
    listOfPokemon: List<PokemonItemEntity>,
    goToDetailOfPokemon: KFunction2<PokemonItemEntity?, NavController, Unit>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(listOfPokemon) { pokemon ->
            BuildItemUI(pokemon = pokemon, goToDetailOfPokemon, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildItemUI(
    pokemon: PokemonItemEntity,
    goToDetailOfPokemon: KFunction2<PokemonItemEntity?, NavController, Unit>,
    navController: NavController
) {
    Row {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { goToDetailOfPokemon(pokemon, navController) },
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = LoadImageFromUrl(url = pokemon.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pokemon.name)
        }


    }
}

@Composable
@Preview
fun buildListScreenPreview() {
    //BuildListScreen()

}