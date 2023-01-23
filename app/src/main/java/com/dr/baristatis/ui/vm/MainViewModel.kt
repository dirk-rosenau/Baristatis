package com.dr.baristatis.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr.baristatis.model.MyCoffeeData
import com.dr.baristatis.repository.CoffeeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val coffeeRepository: CoffeeRepository) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(Loading)
    val viewState: StateFlow<MainViewState> = _state

    sealed class MainViewState
    object Loading : MainViewState()
    data class Success(val data: List<MyCoffeeData>) : MainViewState()
    data class Error(val throwable: Throwable) : MainViewState()


    private val sortFlow = MutableStateFlow(SortOption.NAME)

    init {
        coffeeRepository.getCoffees().combine(sortFlow) { coffees, sort ->
            coffees.sortedWith { o1, o2 ->
                when (sort) {
                    SortOption.NAME -> o1.name.compareTo(o2.name)
                    SortOption.RATING -> if (o1.rating != null && o2.rating != null) {
                        o1.rating.compareTo(o2.rating)
                    } else 0
                    else -> o1.name.compareTo(o2.name)
                }
            }
        }
            .onStart { _state.emit(Loading) }
            .onEach {
                _state.emit(Success(it))
            }
            .catch { throwable ->
                _state.emit(Error(throwable))
                throwable.message?.let { Log.d("MainViewModel", it) }
            }
            .launchIn(viewModelScope)
    }

    fun setSortOrder(value: SortOption) {
        viewModelScope.launch {
            sortFlow.emit(value)
        }
    }

    enum class SortOption {
        NAME,
        DATE,
        RATING
    }

    //TODO method in dao
    fun getItem(itemId: Int) = (_state.value as? Success)?.let {
        it.data.find { it.id == itemId }
    }

    fun saveCoffee(coffeeData: MyCoffeeData) {
        viewModelScope.launch {
            coffeeRepository.saveCoffee(coffeeData)
        }
    }

    fun deleteCoffeeData(id: Int) {
        viewModelScope.launch {
            coffeeRepository.deleteCoffee(id)
        }
    }
}