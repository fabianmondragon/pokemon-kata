package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class Chain(
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>
)
