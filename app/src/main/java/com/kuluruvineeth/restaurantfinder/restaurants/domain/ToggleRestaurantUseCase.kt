package com.kuluruvineeth.restaurantfinder.restaurants.domain

import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository

class ToggleRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantUseCase = GetSortedRestaurantUseCase()
    suspend operator fun invoke(
        id: Int,
        oldValue: Boolean
    ) : List<Restaurant>{
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id,newFav)
        return getSortedRestaurantUseCase()
    }
}