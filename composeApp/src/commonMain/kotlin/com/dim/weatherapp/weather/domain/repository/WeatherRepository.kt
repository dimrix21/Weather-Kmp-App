package com.dim.weatherapp.weather.domain.repository

import com.dim.weatherapp.weather.data.model.WeatherResponseDto

interface WeatherRepository {
    suspend fun getWeatherByLocation(lat: Double, lon: Double): Result<WeatherResponseDto>

}