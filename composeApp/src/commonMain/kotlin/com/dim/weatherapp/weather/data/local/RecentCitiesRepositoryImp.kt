package com.dim.weatherapp.weather.data.local

import com.dim.weatherapp.weather.domain.repository.RecentCitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecentCitiesRepositoryImp : RecentCitiesRepository {

    // Replace this with actual DataStore implementation
    private val recentCitiesList = mutableListOf<String>()
    private val recentCitiesFlow = MutableStateFlow<List<String>>(emptyList())

    override suspend fun addCity(city: String) {
        if (!recentCitiesList.contains(city)) {
            recentCitiesList.add(0, city) // Add to the beginning for most recent
            // Limit the list size if needed, e.g., to 10
            if (recentCitiesList.size > 10) {
                recentCitiesList.removeLast()
            }
            recentCitiesFlow.value = recentCitiesList.toList() // Emit the updated list
        }
    }

    override fun getRecentCities(): StateFlow<List<String>> {
        // In a real DataStore implementation, this would return a Flow from DataStore
        return recentCitiesFlow.asStateFlow()
    }
}