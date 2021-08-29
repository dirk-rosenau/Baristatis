package com.dr.baristatis.model

data class MyCoffeeData(
    val id: Int,
    val name: String,
    val manufacturer: String,
    val prefferredBrewingTemperature: Float,
    val brewRatio: String,
    val remarks: String,
    val arabicaRatio: Float,
    val weightInPortafilter: Float
)