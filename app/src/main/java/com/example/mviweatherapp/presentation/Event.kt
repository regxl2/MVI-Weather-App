package com.example.mviweatherapp.presentation

sealed class Event {
    data object LoadWeatherInfo: Event()
}