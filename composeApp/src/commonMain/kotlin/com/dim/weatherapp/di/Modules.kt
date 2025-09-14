package com.dim.weatherapp.di


import com.dim.weatherapp.core.data.HttpClientFactory
import com.dim.weatherapp.weather.data.remote.CoordinatesRepositoryImp
import com.dim.weatherapp.weather.data.remote.WeatherRepositoryImp
import com.dim.weatherapp.weather.domain.repository.CoordinatesRepository
import com.dim.weatherapp.weather.domain.repository.WeatherRepository
import com.dim.weatherapp.weather.domain.usecases.GetWeatherByCityUseCase
import com.dim.weatherapp.weather.ui.presenters.weather_screen.WeatherViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// for getting engine for ktor client from each platform module
expect val platformModule: Module

// Use shared modules for non-platform specific dependencies
val sharedModules = module {

    single { HttpClientFactory.create(get()) }

    single<WeatherRepository> { WeatherRepositoryImp(get()) }
    single<CoordinatesRepository> { CoordinatesRepositoryImp(get()) }
    single<GetWeatherByCityUseCase> { GetWeatherByCityUseCase(get(), get()) }
    viewModel { WeatherViewModel(get()) }

}
