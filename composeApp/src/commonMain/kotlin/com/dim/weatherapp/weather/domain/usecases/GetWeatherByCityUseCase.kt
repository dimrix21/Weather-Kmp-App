package com.dim.weatherapp.weather.domain.usecases

import com.dim.weatherapp.weather.domain.mappers.toUiModel
import com.dim.weatherapp.weather.domain.model.WeatherUiModel
import com.dim.weatherapp.weather.domain.repository.CoordinatesRepository
import com.dim.weatherapp.weather.domain.repository.RecentCitiesRepository
import com.dim.weatherapp.weather.domain.repository.WeatherRepository

class GetWeatherByCityUseCase(
    private val weatherRepository: WeatherRepository,
    private val coordinatesRepository: CoordinatesRepository,
    private val recentCitiesRepository: RecentCitiesRepository
) {
    suspend operator fun invoke(city: String): Result<WeatherUiModel> {
        val coordinates = coordinatesRepository.getCoordinatesByCity(city)
            .getOrElse { error ->
                return Result.failure(error)
            }

        val coordinatesLocation = coordinates.features.getOrNull(0)?.geometry?.coordinates

        if (coordinatesLocation == null) {
            return Result.failure(Exception("Invalid coordinates"))
        }

        val lat = coordinatesLocation.getOrNull(1) ?: 0.0
        val lon = coordinatesLocation.getOrNull(0) ?: 0.0

        return weatherRepository.getWeatherByLocation(lat, lon)
            .onSuccess {
                recentCitiesRepository.addCity(it.name)
            }
            .map { it.toUiModel() }
    }
}