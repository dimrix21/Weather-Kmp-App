package com.dim.weatherapp.weather.ui.presenters.weather_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    cityName: String,
    onSearchClicked: (String) -> Unit,
) {
    var textFieldValue by remember(cityName) {
        mutableStateOf(
            TextFieldValue(
                text = cityName,
                selection = TextRange(cityName.length) // cursor בסוף
            )
        )
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { textFieldValue = it },
        placeholder = { Text("Enter a city name", color = Color(0xFFB0B0B3)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearchClicked(textFieldValue.text)
            }
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = {
            keyboardController?.hide()
            onSearchClicked(textFieldValue.text)
        },
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