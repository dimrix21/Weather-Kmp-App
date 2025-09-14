package com.dim.weatherapp.weather.ui.presenters.weather_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim.weatherapp.weather.domain.usecases.GetWeatherByCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            val weatherData = getWeatherByCityUseCase.invoke("Tokyo")
            _state.update {
                it.copy(
                    isLoading = false,
                    error = null
                )
            }

        }
    }
}