package com.kuluruvineeth.restaurantfinder.restaurants.domain

import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class GetInitialRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val getSortedRestaurantUseCase: GetSortedRestaurantUseCase
) {

    suspend operator fun invoke(): List<Restaurant>{
        repository.loadRestaurants()
        return getSortedRestaurantUseCase()
    }
}