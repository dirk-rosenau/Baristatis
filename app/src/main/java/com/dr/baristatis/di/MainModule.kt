package com.dr.baristatis.di


import com.dr.baristatis.repository.CoffeeRepository
import com.dr.baristatis.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { CoffeeRepository() }
    viewModel { MainViewModel(get()) }
}
