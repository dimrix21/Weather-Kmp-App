package com.dim.weatherapp.weather.ui.presenters.weather_screen.components.search_bar

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim.weatherapp.weather.domain.usecases.GetCityOptionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class SearchBarUiState(
    val selectedCity: String = "",
    val searchQuery: String = "",
    val cityOptions: List<String> = emptyList(),
    val isDropdownVisible: Boolean = false
)

@OptIn(FlowPreview::class)
class SearchBarViewModel(
    private val getCityOptionsUseCase: GetCityOptionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchBarUiState())
    val uiState: StateFlow<SearchBarUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    private var searchJob: Job? = null

    init {
        setupSearch()
    }

    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(300L)
                .filter { it.isNotBlank() && it.length > 2 }
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }

        // Clear dropdown when query is too short
        viewModelScope.launch {
            _searchQuery
                .filter { it.length <= 2 }
                .collect {
                    clearDropdown()
                }
        }
    }

    private fun performSearch(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            try {

                val result = getCityOptionsUseCase(query)

                result.onSuccess { options ->
                    updateSuccessState(options)
                }

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun updateSuccessState(options: List<String>) {
        _uiState.update {
            it.copy(
                cityOptions = options,
                isDropdownVisible = options.isNotEmpty(),
            )
        }
    }

    private fun clearDropdown() {
        _uiState.update {
            it.copy(
                cityOptions = emptyList(),
                isDropdownVisible = false,
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        _searchQuery.value = query
    }

    fun onCitySelected(city: String) {
        _searchQuery.value = ""
        // Cancel any ongoing search
        searchJob?.cancel()

        _uiState.update {
            it.copy(
                selectedCity = city,
                searchQuery = "",
                cityOptions = emptyList(),
                isDropdownVisible = false,
            )
        }
    }

    public fun dismissDropdown() {
        _uiState.update {
            it.copy(
                cityOptions = emptyList(),
                isDropdownVisible = false,
            )
        }
    }


    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}