package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @SerializedName("chain")
    val chain: Chain,
    @SerializedName("url")
    val url: String = ""
)