package com.example.domain.models

sealed class PokemonResponse <out T>{
    data class Success <out T> (val data: T): PokemonResponse<T>()
    data class Error(val error: Throwable): PokemonResponse<Nothing>()
}