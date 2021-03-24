package com.example.androiddevchallenge.repo

import com.example.androiddevchallenge.data.models.Weather
import com.example.androiddevchallenge.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepo(val api: Api) {
    suspend fun getWeather(gLocation: String): Weather = withContext(Dispatchers.IO) {
        api.getWeatherAsync(gLocation)
    }
}