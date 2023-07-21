package com.example.data.responses

import OfficialArtwork
import com.google.gson.annotations.SerializedName

data class Other (
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

