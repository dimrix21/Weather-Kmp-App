package com.dim.weatherapp.weather.data.remote

import com.dim.weatherapp.core.data.safeCall
import com.dim.weatherapp.weather.data.model.LocationResponseDto
import com.dim.weatherapp.weather.domain.repository.CoordinatesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://photon.komoot.io/api/"

class CoordinatesRepositoryImp(private val client: HttpClient) : CoordinatesRepository {

    override suspend fun getCoordinatesByCity(city: String): Result<LocationResponseDto> {
        return safeCall<LocationResponseDto> {
            client.get(BASE_URL) {
                parameter("q", city)
                parameter("limit", "1")
            }
        }

//        val response =
//
//        return response
    }
}