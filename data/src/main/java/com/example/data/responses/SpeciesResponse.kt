package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("evolution_chain")
    val evolutionChainResponse: EvolutionChainResponse
)