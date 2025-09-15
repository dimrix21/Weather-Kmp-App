package com.dim.weatherapp.weather.ui.presenters.weather_screen.components.search_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.dim.weatherapp.core.presentation.theme.WeatherAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchBar(
    onSearchClicked: (String) -> Unit,
    viewModel: SearchBarViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        // Search TextField
        Box {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { text ->
                    viewModel.onSearchQueryChanged(text)
                },
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
                        onSearchClicked(uiState.searchQuery)
                    }
                )
            )

            // Dropdown menu
            if (uiState.cityOptions.isNotEmpty() && uiState.isDropdownVisible) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 56.dp) // Adjust based on your text field height
                        .zIndex(1f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 200.dp) // Limit height to show max items
                    ) {
                        items(uiState.cityOptions) { city ->
                            Text(
                                text = city,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onCitySelected(city)
                                        keyboardController?.hide()
                                        onSearchClicked(city)
                                    }
                                    .padding(16.dp),
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                        }
                        item {
                            Text(
                                text = "Dismiss",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.dismissDropdown()
                                     }
                                    .padding(16.dp),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Button
        Button(
            onClick = {
                keyboardController?.hide()
                onSearchClicked(uiState.searchQuery)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            Text("Search")
        }
    }
}
