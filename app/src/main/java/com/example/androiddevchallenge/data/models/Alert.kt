package com.example.androiddevchallenge.data.models

data class Alert(
    var title: String,
    var regions: List<String>,
    var severity: String,
    var time: Int,
    var expires: Int,
    var description: String,
    var uri: String
)
