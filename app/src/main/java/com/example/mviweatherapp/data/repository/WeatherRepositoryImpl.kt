package com.example.mviweatherapp.data.repository

import com.example.mviweatherapp.data.mappers.toWeatherInfo
import com.example.mviweatherapp.data.remote.WeatherApi
import com.example.mviweatherapp.domain.repository.WeatherRepository
import com.example.mviweatherapp.domain.weather.WeatherInfo
import com.example.mviweatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl@Inject constructor(private val weatherApi: WeatherApi): WeatherRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                ).toWeatherInfo()
            )
        }
        catch (e: Exception){
            e.printStackTrace()
            Resource.Error(
                message = e.message?: "An unknown error occurred"
            )
        }
    }
}