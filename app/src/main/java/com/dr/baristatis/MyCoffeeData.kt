package com.dr.baristatis

data class MyCoffeeData(
    val name: String,
    val manufacturer: String,
    val prefferredBrewingTemperature: Float,
    val brewRatio: String,
    val remarks: String,
    val arabicaRatio: Int,
    val robustaRatio: Int,
    val weightInPortafilter: Float
)