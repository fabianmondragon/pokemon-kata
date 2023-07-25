package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: MoveDetail
)