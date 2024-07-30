package com.example.mviweatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(modifier: Modifier = Modifier, state: WeatherState) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(text = "Today", fontSize = 20.sp, color = Color.White)
            LazyRow {
                items(data){ weatherData ->
                    HourlyWeatherDisplay(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp),
                        weatherData = weatherData
                    )
                }
            }
        }
    }
}