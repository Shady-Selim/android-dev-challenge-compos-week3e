package com.example.androiddevchallenge.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.models.Weather
import com.example.androiddevchallenge.network.ApiBuilder
import com.example.androiddevchallenge.repo.MainRepo
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val repo: MainRepo = MainRepo(ApiBuilder.retrofitApi)

    val weather: MutableLiveData<Weather> by lazy {
        MutableLiveData<Weather>()
    }

    fun getWeather(gLocation: String): MutableLiveData<Weather> {
        viewModelScope.launch {
            try {
                weather.postValue(repo.getWeather(gLocation))
            } catch (ex: Exception) {
                Log.e("Get Weather:", ex.message.toString())
            }
        }
        return weather
    }
}