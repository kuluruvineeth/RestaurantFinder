package com.kuluruvineeth.restaurantfinder.restaurants.domain

import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository

class GetInitialRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantUseCase = GetSortedRestaurantUseCase()
    suspend operator fun invoke(): List<Restaurant>{
        repository.loadRestaurants()
        return getSortedRestaurantUseCase()
    }
}