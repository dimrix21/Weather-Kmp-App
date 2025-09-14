package com.dim.weatherapp.weather.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    cityName: String,
    onSearchClicked: (String) -> Unit,
) {

    var currentValue by remember { mutableStateOf(cityName) }

    OutlinedTextField(
        value = currentValue,
        onValueChange = { currentValue = it },
        placeholder = { Text("Enter a city name", color = Color(0xFFB0B0B3)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = { onSearchClicked(currentValue) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)

    ) {
        Spacer(modifier = Modifier.width(4.dp))

        Text("Search")
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    WeatherAppTheme {
        SearchBar(cityName = "", onSearchClicked = {})
    }

}