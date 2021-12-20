package com.dr.baristatis.ui.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.repository.CoffeeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val coffeeRepository: CoffeeRepository) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(Idle)
    val state: StateFlow<MainViewState> = _state

    sealed class MainViewState
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class Finished(val data: List<MyCoffeeData>) : MainViewState()
    data class Error(val message: String) : MainViewState()

    val coffees = coffeeRepository.getCoffees().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    //TODO method in dao
    //fun getItem(itemId: Int) = coffeeDataList.find { it.id == itemId }

    fun getItem(itemId: Int) = coffees.value.find { it.id == itemId }

    fun saveCoffee(coffeeData: MyCoffeeData) {
        viewModelScope.launch {
            coffeeRepository.saveCoffee(coffeeData)
        }
    }

    fun deleteCoffeeData(id: Int){
        viewModelScope.launch {
            coffeeRepository.deleteCoffee(id)
        }
    }
}