package com.dim.weatherapp.weather.domain.usecases

import com.dim.weatherapp.weather.domain.mappers.toUiModel
import com.dim.weatherapp.weather.domain.model.WeatherUiModel
import com.dim.weatherapp.weather.domain.repository.CoordinatesRepository
import com.dim.weatherapp.weather.domain.repository.RecentCitiesRepository
import com.dim.weatherapp.weather.domain.repository.WeatherRepository

class GetWeatherByCityUseCase(
    private val weatherRepository: WeatherRepository,
    private val coordinatesRepository: CoordinatesRepository,
    private val recentCitiesRepository: RecentCitiesRepository // Added new repository
) {
    suspend operator fun invoke(city: String): WeatherUiModel {
        val coordinateDto = coordinatesRepository.getCoordinatesByCity(city)

        val coordinates = coordinateDto.features.firstOrNull()?.geometry?.coordinates
        val lat = coordinates?.getOrNull(1) ?: 0.0
        val lon = coordinates?.getOrNull(0) ?: 0.0

        val result = weatherRepository.getWeatherByLocation(lat, lon).toUiModel()

        recentCitiesRepository.addCity(city)

        return result
    }
}