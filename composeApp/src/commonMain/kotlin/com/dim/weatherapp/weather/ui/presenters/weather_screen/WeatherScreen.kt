package com.dim.weatherapp.weather.ui.presenters.weather_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dim.weatherapp.core.presentation.theme.TextTitleColor
import com.dim.weatherapp.weather.domain.model.WeatherUiModel
import com.dim.weatherapp.weather.ui.presenters.weather_screen.components.RecentSearchChips
import com.dim.weatherapp.weather.ui.presenters.weather_screen.components.WeatherInfoCard
import com.dim.weatherapp.weather.ui.presenters.weather_screen.components.search_bar.SearchBar
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun WeatherScreenRoot(
    viewModel: WeatherViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val recentSearches: List<String> by viewModel.getRecentCities().collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "Enter a city name to get the current weather conditions",
            style = MaterialTheme.typography.bodyMedium,
            color = TextTitleColor,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar with Button
        SearchBar(
            onSearchClicked = { city ->
                viewModel.getWeatherByCity(city)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Weather Details via search result
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

        // Recent Searches
        if (recentSearches.isNotEmpty()) {
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
                recentSearches = recentSearches,
                onRecentSearchClick = { city ->
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
