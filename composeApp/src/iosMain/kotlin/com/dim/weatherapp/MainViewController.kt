package com.dim.weatherapp

import androidx.compose.ui.window.ComposeUIViewController
import com.dim.weatherapp.app.App
import com.dim.weatherapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }

