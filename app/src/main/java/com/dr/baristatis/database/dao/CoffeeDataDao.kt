package com.dr.baristatis.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dr.baristatis.model.MyCoffeeData

@Dao
interface CoffeeDataDao {
    @Query("SELECT * FROM coffee_data")
    suspend fun getAll(): List<MyCoffeeData>

    @Insert
    suspend fun insert(data: MyCoffeeData)

    @Delete
    suspend fun delete(data: MyCoffeeData)
}