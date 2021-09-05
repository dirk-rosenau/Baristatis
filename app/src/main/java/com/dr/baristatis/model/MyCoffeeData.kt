package com.dr.baristatis.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_data")
data class MyCoffeeData(
    @PrimaryKey val id: Int,
    val name: String,
    val manufacturer: String,
    val prefferredBrewingTemperature: Float,
    val brewRatio: String,
    val remarks: String,
    val arabicaRatio: Float,
    val weightInPortafilter: Float
)