package com.example.androiddevchallenge.data.models

data class Hourly(
    var summary: String,
    var icon: String,
    var data: List<Datum>
)
