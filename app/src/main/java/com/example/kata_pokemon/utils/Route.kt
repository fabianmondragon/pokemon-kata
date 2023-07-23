package com.example.kata_pokemon.utils

sealed class Route (val route: String) {
    object List: Route("ListScreen")
    object Detail: Route("DetailScreen")
}