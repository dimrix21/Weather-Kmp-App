package com.dim.weatherapp.weather.ui.presenters.weather_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChipButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(text)
    }
}

@Preview()
@Composable
fun ChipButtonPreview() {
    WeatherAppTheme {
        ChipButton(text = "Click Me", onClick = {})
    }
}
