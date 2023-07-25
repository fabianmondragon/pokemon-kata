package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class MoveDetail(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)