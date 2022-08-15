package com.kuluruvineeth.restaurantfinder.restaurants

import com.kuluruvineeth.restaurantfinder.restaurants.domain.Restaurant

object DummyContent {
    fun getDomainRestaurants() = arrayListOf(
        Restaurant(0,"title0","description0",false),
        Restaurant(1,"title1","description1",false),
        Restaurant(2,"title2","description2",false),
        Restaurant(3,"title3","description3",false)
    )
}