package com.example.mviweatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "relative_humidity_2m")
    val relativeHumidities: List<Int>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "time")
    val times: List<String>,
    @field:Json(name = "weather_code")
    val weatherCodes: List<Int>,
    @field: Json(name = "wind_speed_10m")
    val windSpeeds: List<Double>
)