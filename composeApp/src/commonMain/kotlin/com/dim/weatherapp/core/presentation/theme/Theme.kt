package com.dim.weatherapp.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppDimens {
    val paddingSmall = 8.dp
    val paddingMedium = 16.dp
    val paddingLarge = 24.dp

    val textSizeSmall = 12.sp
    val textSizeMedium = 16.sp
    val textSizeLarge = 20.sp
}

private val DarkColorScheme = darkColorScheme(

)


private val LightColorScheme = lightColorScheme(
    primary = ButtonBackground,
    onPrimary = TextColor,

    background = BackgroundColor,
    onBackground = TextColor,

    secondary = ButtonSecondaryBackground,
    onSecondary = TextSecondary,

    )

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}