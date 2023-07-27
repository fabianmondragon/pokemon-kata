package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("name")
    val name: String
)