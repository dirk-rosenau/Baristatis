package com.dr.baristatis.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_data")
data class MyCoffeeData(
    @PrimaryKey val id: Int?,
    val name: String,
    val manufacturer: String,
    val prefferredBrewingTemperature: Int? = null,
    val brewRatio: String? = null,
    val remarks: String? = null,
    val arabicaRatio: Float? = null,
    val weightInPortafilter: Float? = null,
    val deggreeOfGrinding: Int?,
    val rating: Float? = null,
    val grinderTime: Float? = null,
)