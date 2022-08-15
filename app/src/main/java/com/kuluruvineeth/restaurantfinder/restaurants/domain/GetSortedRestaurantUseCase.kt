package com.kuluruvineeth.restaurantfinder.restaurants.domain

import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class GetSortedRestaurantUseCase @Inject constructor(
    private val repository: RestaurantsRepository
){
    suspend operator fun invoke(): List<Restaurant>{
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}