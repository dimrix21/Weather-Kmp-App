package com.dim.weatherapp

import android.app.Application
import com.dim.weatherapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApplication)
        }
    }
}