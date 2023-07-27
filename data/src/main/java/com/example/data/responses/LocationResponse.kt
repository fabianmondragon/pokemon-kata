package com.example.data.responses

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("location_area")
    val locationArea: LocationArea
)