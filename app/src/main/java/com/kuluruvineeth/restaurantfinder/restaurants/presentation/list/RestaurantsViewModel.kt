package com.kuluruvineeth.restaurantfinder.restaurants.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuluruvineeth.restaurantfinder.restaurants.domain.GetInitialRestaurantsUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.domain.ToggleRestaurantUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.presentation.list.RestaurantsScreenState
import kotlinx.coroutines.*

class RestaurantsViewModel(
    private val _stateHandle: SavedStateHandle
) : ViewModel() {
    private val getRestaurantsUseCase = GetInitialRestaurantsUseCase()
    private val toggleRestaurantUseCase = ToggleRestaurantUseCase()
    private val _state = mutableStateOf(
        RestaurantsScreenState(
            restaurants = listOf(),
            isLoading = true
        )
    )
    val state: State<RestaurantsScreenState>
    get() = _state
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }
    init {

        getRestaurants()
    }

    private fun getRestaurants(){
        viewModelScope.launch(errorHandler) {
            val restaurants = getRestaurantsUseCase()
            _state.value = _state.value.copy(
                restaurants = restaurants,
                isLoading = false
            )
        }
    }

    fun toggleFavorite(id:Int,oldValue: Boolean){
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = toggleRestaurantUseCase(id,oldValue)
            _state.value = _state.value.copy(
                restaurants = updatedRestaurants
            )
        }
    }


}