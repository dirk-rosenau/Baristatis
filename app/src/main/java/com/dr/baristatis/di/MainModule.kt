package com.dr.baristatis.di


import androidx.room.Room
import com.dr.baristatis.database.AppDatabase
import com.dr.baristatis.repository.CoffeeRepository
import com.dr.baristatis.ui.vm.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "baristatis-db")
            .build()
    }
    single { get<AppDatabase>().coffeeDataDao() }
    single { CoffeeRepository(get()) }
    viewModel { MainViewModel(get()) }
}
