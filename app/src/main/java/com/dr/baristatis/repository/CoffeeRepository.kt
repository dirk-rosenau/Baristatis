package com.dr.baristatis.repository

import com.dr.baristatis.database.dao.CoffeeDataDao
import com.dr.baristatis.model.MyCoffeeData

class CoffeeRepository(private val coffeeDataDao: CoffeeDataDao) {

    suspend fun seed() {
        mockCoffees().forEach {
            coffeeDataDao.insert(it)
        }
    }

    suspend fun getCoffees() = coffeeDataDao.getAll()

    private fun mockCoffees() = listOf(
        MyCoffeeData(1, "Berliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(2, "Espresso Superior", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(3, "Shitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(4, "Kackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(5, "aaaBerliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(
            6,
            "aaaEspresso Superior",
            "Berliner Kaffeerösterei",
            93f,
            "1:2",
            "",
            0.8f,
            16f
        ),
        MyCoffeeData(7, "aaaShitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(8, "aaaKackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(9, "bbbBerliner Perle", "Berliner Kaffeerösterei", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(
            10,
            "bbbEspresso Superior",
            "Berliner Kaffeerösterei",
            93f,
            "1:2",
            "",
            0.8f,
            16f
        ),
        MyCoffeeData(11, "bbbbShitsmack", "Balzac", 93f, "1:2", "", 0.8f, 16f),
        MyCoffeeData(12, "bbbKackbraun", "Starbucks", 93f, "1:2", "", 0.8f, 16f)

    )

}