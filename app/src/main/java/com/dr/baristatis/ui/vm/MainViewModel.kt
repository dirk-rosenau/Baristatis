package com.dr.baristatis.ui.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.repository.CoffeeRepository
import kotlinx.coroutines.launch

class MainViewModel(private val coffeeRepository: CoffeeRepository) : ViewModel() {

    val coffeeDataList = mutableStateListOf<MyCoffeeData>()

    init {
        loadCoffees()
    }

    private fun loadCoffees() {
        viewModelScope.launch {
            coffeeDataList.addAll(coffeeRepository.getCoffees())
        }
    }

    //TODO method in dao
    fun getItem(itemId: Int) = coffeeDataList.find { it.id == itemId }


    fun seed() {
        viewModelScope.launch {
            coffeeRepository.seed()
        }
    }

    fun saveCoffee(coffeeData: MyCoffeeData) {
        viewModelScope.launch {
            coffeeDataList.add(coffeeData)
            coffeeRepository.saveCoffee(coffeeData)
        }
    }
}