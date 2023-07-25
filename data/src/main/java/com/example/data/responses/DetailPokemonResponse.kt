package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @SerializedName("abilities")
    val abilities: List<Ability>,

    @SerializedName("moves")
    val moves: List<Move>,

    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprites: Sprites,

    @SerializedName("types")
    val types: List<Type>,

    @SerializedName("weight")
    val weight: Int
)
