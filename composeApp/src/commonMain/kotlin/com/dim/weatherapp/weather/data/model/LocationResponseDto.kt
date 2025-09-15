package com.dim.weatherapp.weather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponseDto(
    @SerialName("features")
    val features: List<FeatureDto>
)

@Serializable
data class FeatureDto(
    @SerialName("geometry")
    val geometry: GeometryDto,
    @SerialName("properties")
    val properties: PropertiesDto
)

@Serializable
data class GeometryDto(
    @SerialName("coordinates")
    val coordinates: List<Double>
)

@Serializable
data class PropertiesDto(
    @SerialName("name")
    val name: String
)
