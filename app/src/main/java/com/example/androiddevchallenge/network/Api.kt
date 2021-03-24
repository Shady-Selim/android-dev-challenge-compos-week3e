package com.example.androiddevchallenge.network

import com.example.androiddevchallenge.BuildConfig
import com.example.androiddevchallenge.data.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("forecast/${BuildConfig.Weather_Api_Key}/{location}")
    suspend fun getWeatherAsync(@Path("location") gLocation: String): Weather //59.337239,18.062381
}