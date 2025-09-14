package com.dim.weatherapp.weather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecentSearchChips(recentSearches: List<String>, onRecentSearchClick: (String) -> Unit) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        recentSearches.forEach { search ->
            ChipButton(text = search, onClick = { onRecentSearchClick(search) })
        }
    }
}