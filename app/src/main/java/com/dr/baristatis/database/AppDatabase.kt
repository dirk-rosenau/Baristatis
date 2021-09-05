package com.dr.baristatis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dr.baristatis.database.dao.CoffeeDataDao
import com.dr.baristatis.model.MyCoffeeData

@Database(entities = [MyCoffeeData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeDataDao(): CoffeeDataDao
}
