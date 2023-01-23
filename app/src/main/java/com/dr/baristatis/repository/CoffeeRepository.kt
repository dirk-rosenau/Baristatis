package com.dr.baristatis.repository

import com.dr.baristatis.database.dao.CoffeeDataDao
import com.dr.baristatis.model.MyCoffeeData

class CoffeeRepository(private val coffeeDataDao: CoffeeDataDao) {

    fun getCoffees() = coffeeDataDao.getAll()

    suspend fun saveCoffee(coffeeData: MyCoffeeData) {
        coffeeDataDao.insert(coffeeData)
    }

    suspend fun deleteCoffee(id: Int) {
        coffeeDataDao.delete(id)
    }

}