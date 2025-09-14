package com.dim.weatherapp.weather.domain.usecases

import com.dim.weatherapp.weather.data.model.WeatherData
import com.dim.weatherapp.weather.domain.repository.WeatherRepository

class GetWeatherByCityUseCase(
    private val weatherRepository: WeatherRepository

) {
    suspend operator fun invoke(city: String): WeatherData {
        return weatherRepository.getWeatherByLocation(city)
    }

}