package com.dr.baristatis.repository

import com.dr.baristatis.model.MyCoffeeData

class CoffeeRepository {
    fun getCoffees() = listOf(
        MyCoffeeData(1,"Berliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData(2,"Espresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData(3,"Shitsmack", "Balzac", 93f, "1:2", "", 100, 0, 16f),
        MyCoffeeData(4,"Kackbraun", "Starbucks", 93f, "1:2", "", 100, 0, 16f)
    )

}