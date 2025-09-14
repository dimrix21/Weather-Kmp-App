package com.dim.weatherapp.weather.domain.repository

import com.dim.weatherapp.weather.data.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherByLocation(city : String): WeatherData

}