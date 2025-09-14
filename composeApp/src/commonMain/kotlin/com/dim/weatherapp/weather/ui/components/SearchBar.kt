package com.dim.weatherapp.weather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    cityName: String,
    onCityNameChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = cityName,
        onValueChange = onCityNameChange,
        placeholder = { Text("Enter a city name", color = Color(0xFFB0B0B3)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    WeatherAppTheme {
        SearchBar(cityName = "", onCityNameChange = {})
    }

}