package com.dr.baristatis.repository

import com.dr.baristatis.MyCoffeeData

class CoffeeRepository {
    fun getCoffees() = listOf(
        MyCoffeeData("Berliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData("Espresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData("Shitsmack", "Balzac", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData("Kackbraun", "Starbucks", 93f, "1:2", "", 100, 0, 16f)
    )

}