package com.example.mviweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mviweatherapp.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(modifier: Modifier = Modifier, state: WeatherState, backgroundColor: Color) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            modifier = modifier
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardColors(
                containerColor = backgroundColor,
                contentColor = Color.White,
                disabledContentColor = Color.LightGray,
                disabledContainerColor = Color.Gray
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.End), color = Color.White, text = "Today ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }"
                )
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )
                Text(text = "${data.temperatureCelsius}°C", fontSize = 48.sp, color = Color.White)
                Text(text = data.weatherType.weatherDesc, fontSize = 20.sp, color = Color.White)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_pressure)
                    )
                    WeatherDataDisplay(
                        value = data.humidity,
                        unit = "%",
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_pressure)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/hr",
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_pressure)
                    )
                }
            }
        }
    }
}
