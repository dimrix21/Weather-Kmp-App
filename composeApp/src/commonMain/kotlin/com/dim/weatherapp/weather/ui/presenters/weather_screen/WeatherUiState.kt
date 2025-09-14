package com.dim.weatherapp.weather.ui.presenters.weather_screen

import com.dim.weatherapp.weather.domain.model.WeatherUiModel


sealed interface WeatherUiState {

    data object Loading : WeatherUiState
    data class Success(
        val weatherUiModel: WeatherUiModel
    ) : WeatherUiState

    data class Error(val message: String) : WeatherUiState
}