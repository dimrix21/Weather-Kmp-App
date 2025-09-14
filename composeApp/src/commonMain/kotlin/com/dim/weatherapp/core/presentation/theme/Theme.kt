package com.dim.weatherapp.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
    // Button colors (primary button)
    primary = ButtonBackground,           // Default button background
    onPrimary = TextColor,               // Default button text color

    // Background colors
    background = BackgroundColor,        // Main background color
    onBackground = TextColor,      // Default text color on background

    // Surface colors (cards, sheets, etc.)
    surface = Color(0xFFFFFBFE),           // Surface background
    onSurface = Color(0xFF1C1B1F),         // Text color on surfaces

    secondary = ButtonSecondaryBackground,
    onSecondary = TextSecondary,

    // Container colors (filled buttons, chips, etc.)
    primaryContainer = Color(0xFFEADDFF),  // Filled button container
    onPrimaryContainer = Color(0xFF21005D), // Text on filled button container

    // Other useful colors
    tertiary = Color(0xFF7D5260),
    onTertiary = Color.White,
    surfaceVariant = Color(0xFFE7E0EC),    // Alternative surface color
    onSurfaceVariant = Color(0xFF49454F),  // Text on surface variant
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