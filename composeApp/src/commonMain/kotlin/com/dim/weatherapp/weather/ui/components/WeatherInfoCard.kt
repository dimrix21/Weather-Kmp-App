package com.dim.weatherapp.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.dim.weatherapp.core.presentation.theme.CardWeatherBackground
import com.dim.weatherapp.core.presentation.theme.WeatherIconBackground

@Composable
fun WeatherInfoCard(
    city: String,
    temperature: String,
    condition: String,
    humidity: String,
    windSpeed: String,
    weatherIcon: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardWeatherBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = city, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(WeatherIconBackground),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = weatherIcon,
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(48.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = temperature,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(text = condition, fontSize = 16.sp, color = Color(0xFFB0B0B3))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                WeatherDetailItem(
                    modifier = Modifier.weight(1f), title = "Humidity", value = humidity
                )
                WeatherDetailItem(
                    modifier = Modifier.weight(1f),
                    title = "Wind Speed",
                    value = windSpeed
                )
            }
        }
    }
}