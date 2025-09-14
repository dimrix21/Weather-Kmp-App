package com.dim.weatherapp.weather.domain.mappers

import com.dim.weatherapp.weather.data.model.WeatherResponseDto
import com.dim.weatherapp.weather.domain.model.WeatherUiModel

fun WeatherResponseDto.toUiModel(): WeatherUiModel {
    return WeatherUiModel(
        cityName = name,
        temperature = "${kelvinToCelsius(main.temp)}°C",
        description = weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "",
        humidity = "${main.humidity}%",
        windSpeed = "${wind.speed} m/s",
        iconUrl = "https://openweathermap.org/img/wn/${weather.firstOrNull()?.icon}@2x.png"
    )
}

private fun kelvinToCelsius(kelvin: Double): Int = (kelvin - 273.15).toInt()