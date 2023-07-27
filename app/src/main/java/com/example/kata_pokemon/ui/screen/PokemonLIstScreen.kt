package com.example.kata_pokemon.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kata_pokemon.ui.fragment.goToDetailOfPokemon
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
            BuildSearchBar(listOfPokemon, navController)
            BuildListScreen(listOfPokemon, goToDetailOfPokemon, navController)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildSearchBar(listOfPokemon: List<PokemonItemEntity>, navController: NavController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
            text = ""
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                        text = ""
                    })
            }
        }
    ) {
        val filteredPokemon = listOfPokemon.filter {
            it.name.contains(text, true)
        }
        filteredPokemon.forEach {
            Row(modifier =
            Modifier
                .padding(all = 14.dp)
                .clickable {
                    listOfPokemon.filter { pokemonItemEntity ->
                        pokemonItemEntity.name.equals(text)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = "History Icon"
                )
                Text(text = it.name,
                    modifier = Modifier
                        .clickable {
                            text = it.name
                            active = false
                            goToDetailOfPokemon(it, navController)
                        }
                )
            }
        }


    }
}

@Composable
fun BuildListScreen(
    listOfPokemon: List<PokemonItemEntity>,
    goToDetailOfPokemon: KFunction2<PokemonItemEntity?, NavController, Unit>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 50.dp, start = 8.dp, end = 8.dp)
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