package com.dim.weatherapp.weather.ui.presenters.weather_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dim.weatherapp.core.presentation.theme.TextTitleColor
import com.dim.weatherapp.weather.ui.components.RecentSearchChips
import com.dim.weatherapp.weather.ui.components.SearchBar
import com.dim.weatherapp.weather.ui.components.WeatherInfoCard
import org.jetbrains.compose.resources.painterResource
import weatherapp.composeapp.generated.resources.Res
import weatherapp.composeapp.generated.resources.ic_cloud


@Composable
fun WeatherScreenRoot(
    cityName: String,
    onCityNameChange: (String) -> Unit,
    recentSearches: List<String>,
    onRecentSearchClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    temperature: String,
    condition: String,
    humidity: String,
    windSpeed: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
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
            cityName = cityName,
            onCityNameChange = onCityNameChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)

        ) {
            Spacer(modifier = Modifier.width(4.dp))

            Text("Search")
        }

        Spacer(modifier = Modifier.height(24.dp))

        WeatherInfoCard(
            city = cityName,
            temperature = temperature,
            condition = condition,
            humidity = humidity,
            windSpeed = windSpeed,
            weatherIcon = painterResource(Res.drawable.ic_cloud)
        )

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
            onRecentSearchClick = onRecentSearchClick
        )

    }
}