package com.dim.weatherapp.weather.domain.repository

import com.dim.weatherapp.weather.data.model.LocationResponseDto

interface CoordinatesRepository {
    suspend fun getCoordinatesByCity(city: String): Result<LocationResponseDto>

    suspend fun getNamesByText(text: String): Result<LocationResponseDto>
}