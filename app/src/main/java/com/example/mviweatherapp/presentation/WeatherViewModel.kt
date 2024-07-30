package com.example.mviweatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviweatherapp.domain.location.LocationTracker
import com.example.mviweatherapp.domain.repository.WeatherRepository
import com.example.mviweatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set


    fun onEvent(event: Event){
        when(event){
            is Event.LoadWeatherInfo -> loadWeatherInfo()
        }
    }

    private fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            locationTracker.getCurrentLocation().data?.let { locationData ->
                val result = repository.getWeatherData(
                    latitude = locationData.latitude,
                    longitude = locationData.longitude
                )
                when(result){
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: run {
                state  = state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = "Couldn't retrieve the location"
                )
            }
        }
    }
}