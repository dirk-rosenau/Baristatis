package com.dr.baristatis.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dr.baristatis.model.MyCoffeeData

@Dao
interface CoffeeDataDao {
    @Query("SELECT * FROM coffee_data")
    fun getAll(): List<MyCoffeeData>

    @Insert
    fun insert(note: MyCoffeeData)

    @Delete
    fun delete(note: MyCoffeeData)
}