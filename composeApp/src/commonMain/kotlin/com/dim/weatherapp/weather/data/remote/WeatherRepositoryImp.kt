package com.dim.weatherapp.weather.data.remote

import com.dim.weatherapp.weather.data.model.WeatherData
import com.dim.weatherapp.weather.domain.repository.WeatherRepository

class WeatherRepositoryImp : WeatherRepository {

    override suspend fun getWeatherByLocation(city: String): WeatherData {
        return WeatherData(
            detail = "Sunny"
        )
    }
}