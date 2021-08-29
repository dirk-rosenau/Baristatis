package com.dr.baristatis.ui.vm

import androidx.lifecycle.ViewModel
import com.dr.baristatis.repository.CoffeeRepository

class MainViewModel(private val coffeeRepository: CoffeeRepository) : ViewModel() {
    fun getCoffees() = coffeeRepository.getCoffees()
    fun getItem(itemId: Int) = coffeeRepository.getCoffees().find { it.id == itemId }
}