package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class SpritesPokemonResponse (
    @SerializedName("sprites")
    val sprites: Sprites
        )