package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class EvolvesTo(
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>?,

    @SerializedName("species")
    val species: Species
)