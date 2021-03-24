package com.example.androiddevchallenge.data.models

data class Daily(
    var summary: String,
    var icon: String,
    var data: List<Datum>
)
