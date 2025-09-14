package com.dim.weatherapp.weather.ui.presenters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dim.weatherapp.core.presentation.theme.TopBarBackground
import com.dim.weatherapp.weather.ui.presenters.weather_screen.WeatherScreenRoot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var cityName by remember { mutableStateOf("Tokyo") } // Example city, can be dynamic
    val recentSearches =
        listOf("Tokyo", "Rome", "Israel", "Las Vegas", "Tel Aviv") // Sample data
    // Placeholder for actual weather data - you'll fetch this based on cityName
    val temperature = "26°C"
    val condition = "Broken Clouds"
    val humidity = "78%"
    val windSpeed = "5.66 m/s"



    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Weather Dashboard", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TopBarBackground
                )
            )
        },
    ) { paddingValues ->
        WeatherScreenRoot(
            cityName = cityName,
            onCityNameChange = { cityName = it },
            recentSearches = recentSearches,
            onRecentSearchClick = { cityName = it },
            onSearchClick = { /* TODO: Implement search functionality based on cityName */ },
            temperature = temperature,
            condition = condition,
            humidity = humidity,
            windSpeed = windSpeed,
            modifier = Modifier
                .background(Color(0xFF2C2C2E))
                .padding(paddingValues)
        )
    }
}
