package com.dr.baristatis.repository

import com.dr.baristatis.model.MyCoffeeData

class CoffeeRepository {
    fun getCoffees() = listOf(
        MyCoffeeData(1,"Berliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(2,"Espresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(3,"Shitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(4,"Kackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(1,"aaaBerliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(2,"aaaEspresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(3,"aaaShitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(4,"aaaKackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(1,"bbbBerliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(2,"bbbEspresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(3,"bbbbShitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(4,"bbbKackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f)

    )

}