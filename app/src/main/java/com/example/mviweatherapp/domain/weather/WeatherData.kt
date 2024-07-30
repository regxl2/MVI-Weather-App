package com.example.mviweatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val pressure: Double,
    val humidity: Int,
    val temperatureCelsius: Double,
    val time: LocalDateTime,
    val windSpeed: Double,
    val weatherType: WeatherType
)