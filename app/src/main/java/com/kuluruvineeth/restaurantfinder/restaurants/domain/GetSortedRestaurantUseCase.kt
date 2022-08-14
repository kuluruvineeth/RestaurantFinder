package com.kuluruvineeth.restaurantfinder.restaurants.domain

import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository

class GetSortedRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant>{
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}