package com.dim.weatherapp.weather.data.remote

import com.dim.weatherapp.weather.data.model.WeatherResponseDto
import com.dim.weatherapp.weather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"


class WeatherRepositoryImp(private val client: HttpClient) : WeatherRepository {

    override suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): WeatherResponseDto {
        val response = client.get(BASE_URL) {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("appid", "27a7b679a3856bcb13837447f70187a2")

        }.body<WeatherResponseDto>()

        return response
    }
}

