package com.dim.weatherapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import com.dim.weatherapp.weather.ui.presenters.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    WeatherAppTheme {
        MainScreen(Modifier.fillMaxSize())
    }
}
