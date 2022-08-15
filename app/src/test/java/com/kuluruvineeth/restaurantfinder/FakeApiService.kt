package com.kuluruvineeth.restaurantfinder

import com.kuluruvineeth.restaurantfinder.restaurants.DummyContent
import com.kuluruvineeth.restaurantfinder.restaurants.data.remote.RemoteRestaurant
import com.kuluruvineeth.restaurantfinder.restaurants.data.remote.RestaurantsApiService
import kotlinx.coroutines.delay

class FakeApiService : RestaurantsApiService {
    val dummyMap: Map<String,RemoteRestaurant> = emptyMap()
    override suspend fun getRestaurants(): List<RemoteRestaurant> {
        delay(1000)
        return DummyContent.getRemoteRestaurants()
    }

    override suspend fun getRestaurant(id: Int): Map<String, RemoteRestaurant> {
        return dummyMap
    }
}