package com.example.mviweatherapp.domain.repository

import com.example.mviweatherapp.domain.weather.WeatherInfo
import com.example.mviweatherapp.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}