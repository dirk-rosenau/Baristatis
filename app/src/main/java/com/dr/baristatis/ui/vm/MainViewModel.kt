package com.dr.baristatis.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.repository.CoffeeRepository
import kotlinx.coroutines.launch

class MainViewModel(private val coffeeRepository: CoffeeRepository) : ViewModel() {

    var coffeeData by mutableStateOf<List<MyCoffeeData>?>(null) //'by' helps treat state as data

    init {
        loadCoffees()
    }

    fun loadCoffees() {
        viewModelScope.launch {
            coffeeData = coffeeRepository.getCoffees()
        }
    }

    //TODO method in dao
    fun getItem(itemId: Int) = coffeeData?.find { it.id == itemId }


    fun seed() {
        viewModelScope.launch {
            coffeeRepository.seed()
        }
    }
}