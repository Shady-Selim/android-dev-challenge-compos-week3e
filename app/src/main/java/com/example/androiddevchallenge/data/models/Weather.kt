package com.example.androiddevchallenge.data.models

data class Weather(
    var latitude: Double,
    var longitude: Double,
    var timezone: String,
    var currently: Currently,
    var hourly: Hourly,
    var daily: Daily,
    var alerts: List<Alert>,
    var flags: Flags,
    var offset: Int
)