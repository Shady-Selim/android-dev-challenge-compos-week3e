package com.example.androiddevchallenge.data.models

import com.google.gson.annotations.SerializedName

data class Flags(
    var sources: List<String>,
    @SerializedName("meteoalarm-license")
    var meteoalarm_License: String,
    @SerializedName("nearest-station")
    var nearest_Station: Double,
    var units: String
)
