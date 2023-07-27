package com.example.kata_pokemon.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kata_pokemon.ui.models.DetailPokemonUi
import com.example.kata_pokemon.utils.LoadSvgFromUrl


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPokemonDetailScreen(respond: DetailPokemonUi) {
    Scaffold(
        content = { BuildDetailScreen(respond) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildDetailScreen(respond: DetailPokemonUi) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = LoadSvgFromUrl(url = respond.url),
            contentDescription = null
        )
        Text(text = "Name of pokemon: ${respond.name}")
        Text(text = "Types: ${respond.types}")
        Text(text = "Abilities: ${respond.abilities}")
        Text(text = "Location: ${respond.location}")
        Text(text = "evolution: ${respond.evolution}")

    }
}
