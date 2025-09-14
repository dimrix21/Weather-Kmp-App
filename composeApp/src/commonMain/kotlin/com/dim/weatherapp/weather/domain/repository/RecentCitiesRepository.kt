package com.dim.weatherapp.weather.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface RecentCitiesRepository {
    suspend fun addCity(city: String)

    fun getRecentCities(): StateFlow<List<String>>
}