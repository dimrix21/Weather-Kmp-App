package com.dim.weatherapp.weather.ui.presenters.weather_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim.weatherapp.weather.domain.usecases.GetWeatherByCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)

    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(WeatherUiState.Loading)
            val weatherData = getWeatherByCityUseCase.invoke("Tel Aviv")
            _state.emit(WeatherUiState.Success(weatherUiModel = weatherData))

        }
    }
}