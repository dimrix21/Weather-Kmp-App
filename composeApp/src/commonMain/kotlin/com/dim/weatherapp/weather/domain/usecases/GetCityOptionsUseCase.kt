package com.dim.weatherapp.weather.domain.usecases

import com.dim.weatherapp.weather.domain.repository.CoordinatesRepository

class GetCityOptionsUseCase(
    private val coordinatesRepository: CoordinatesRepository,
) {
    suspend operator fun invoke(city: String): Result<List<String>> {
        val locationResponse = coordinatesRepository.getNamesByText(city)
            .getOrElse { error ->
                return Result.failure(error)
            }

        locationResponse.features.forEach {
            println("names : ${it.properties.name}")
        }

        return Result.success(locationResponse.features.map { it.properties.name })
    }
}