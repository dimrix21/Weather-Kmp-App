package com.dim.weatherapp.weather.ui.presenters.weather_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dim.weatherapp.core.presentation.theme.TextTitleColor
import com.dim.weatherapp.weather.domain.model.WeatherUiModel
import com.dim.weatherapp.weather.ui.components.RecentSearchChips
import com.dim.weatherapp.weather.ui.components.SearchBar
import com.dim.weatherapp.weather.ui.components.WeatherInfoCard
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import weatherapp.composeapp.generated.resources.Res
import weatherapp.composeapp.generated.resources.ic_cloud


@Composable
fun WeatherScreenRoot(
    viewModel: WeatherViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val recent: List<String> by viewModel.getRecentCities().collectAsState()
    var currentSearch by remember { mutableStateOf("") }

    LaunchedEffect(recent) {
        println("recent: $recent")
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enter a city name to get the current weather conditions",
            fontSize = 14.sp,
            color = TextTitleColor,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            cityName = currentSearch,
            onSearchClicked = { city ->
                currentSearch = city
                viewModel.getWeatherByCity(city)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        when (val uiState = state) {
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }

            is WeatherUiState.Success -> {
                WeatherDetails(
                    weatherUiModel = uiState.weatherUiModel
                )
            }

            is WeatherUiState.Error -> {
                Text(
                    text = "Error: ${uiState.message}",
                    color = Color.Red
                )

            }
        }

        if (recent.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Searches",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            RecentSearchChips(
                recentSearches = recent,
                onRecentSearchClick = { city ->
                    currentSearch = city
                    viewModel.getWeatherByCity(city)
                }
            )
        }

    }
}

@Composable
fun WeatherDetails(
    weatherUiModel: WeatherUiModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherInfoCard(
            city = weatherUiModel.cityName,
            temperature = weatherUiModel.temperature,
            condition = weatherUiModel.description,
            humidity = weatherUiModel.humidity,
            windSpeed = weatherUiModel.windSpeed,
            weatherIcon = weatherUiModel.iconUrl
        )

    }
}
