package com.kuluruvineeth.restaurantfinder.restaurants.presentation.list

import com.kuluruvineeth.restaurantfinder.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)