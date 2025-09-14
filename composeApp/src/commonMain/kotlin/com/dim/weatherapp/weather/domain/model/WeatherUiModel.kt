package com.dim.weatherapp.weather.domain.model

data class WeatherUiModel(
    val cityName: String,
    val temperature: String,
    val description: String,
    val humidity: String,
    val windSpeed: String,
    val iconUrl: String
)
