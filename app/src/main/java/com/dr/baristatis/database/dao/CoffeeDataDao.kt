package com.dr.baristatis.database.dao

import androidx.room.*
import com.dr.baristatis.model.MyCoffeeData
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDataDao {
    @Query("SELECT * FROM coffee_data")
    fun getAll(): Flow<List<MyCoffeeData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: MyCoffeeData)

    @Query("DELETE FROM coffee_data WHERE id = :id")
    suspend fun delete(id: Int)
}