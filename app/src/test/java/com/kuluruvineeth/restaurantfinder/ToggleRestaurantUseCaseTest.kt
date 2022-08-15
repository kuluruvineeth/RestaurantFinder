package com.kuluruvineeth.restaurantfinder

import com.kuluruvineeth.restaurantfinder.restaurants.DummyContent
import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository
import com.kuluruvineeth.restaurantfinder.restaurants.domain.GetSortedRestaurantUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ToggleRestaurantUseCaseTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun toggleRestaurant_IsUpdatingFavoriteField() = scope.runTest {
        // Setup usecase
        val restaurantsRepository = RestaurantsRepository(
            FakeApiService(),
            FakeRoomDao(),
            dispatcher
        )
        val getSortedRestaurantUseCase =
            GetSortedRestaurantUseCase(restaurantsRepository)
        val useCase = ToggleRestaurantUseCase(
            restaurantsRepository,
            getSortedRestaurantUseCase
        )

        //preload data
        restaurantsRepository.loadRestaurants()
        advanceUntilIdle()

        //Execute usecase
        val restaurants = DummyContent.getDomainRestaurants()
        val targetItem = restaurants[0]
        val isFavorite = targetItem.isFavorite
        val updatedRestaurants = useCase(
            targetItem.id,
            isFavorite
        )
        advanceUntilIdle()

        //Assertion
        restaurants[0] = targetItem.copy(isFavorite = !isFavorite)
        assert(updatedRestaurants==restaurants)
    }
}