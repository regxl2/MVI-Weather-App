package com.example.mviweatherapp.data.mappers

import com.example.mviweatherapp.data.remote.WeatherDataDto
import com.example.mviweatherapp.data.remote.WeatherDto
import com.example.mviweatherapp.domain.weather.WeatherData
import com.example.mviweatherapp.domain.weather.WeatherInfo
import com.example.mviweatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherData(): Map<Int, List<WeatherData>>{
    val mutableMap = (0..6).associateWith { mutableListOf<WeatherData>() }
    times.forEachIndexed{
        index, timeInString ->
        val weatherData = WeatherData(
            time = LocalDateTime.parse(timeInString,  DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperatures[index],
            humidity = relativeHumidities[index],
            pressure = pressures[index],
            windSpeed = windSpeeds[index],
            weatherType = WeatherType.fromWMO(weatherCodes[index]),
        )
        mutableMap[index/24]?.add(weatherData)
    }
    return mutableMap.toMap()
}

fun WeatherDto.toWeatherInfo(): WeatherInfo{
    val weatherDataMap = weatherDataDto.toWeatherData()
    val currentTime = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        weatherData ->
        val hour = if(currentTime.minute>30) currentTime.hour+1 else currentTime.hour
        weatherData.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}