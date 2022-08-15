package com.kuluruvineeth.restaurantfinder

import com.kuluruvineeth.restaurantfinder.restaurants.DummyContent
import com.kuluruvineeth.restaurantfinder.restaurants.data.RestaurantsRepository
import com.kuluruvineeth.restaurantfinder.restaurants.domain.GetInitialRestaurantsUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.domain.GetSortedRestaurantUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.domain.ToggleRestaurantUseCase
import com.kuluruvineeth.restaurantfinder.restaurants.presentation.list.RestaurantsScreenState
import com.kuluruvineeth.restaurantfinder.restaurants.presentation.list.RestaurantsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantsViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private fun getViewModel() : RestaurantsViewModel{
        val restaurantsRepository = RestaurantsRepository(
            FakeApiService(),
            FakeRoomDao(),
            dispatcher
        )
        val getSortedRestaurantUseCase = GetSortedRestaurantUseCase(restaurantsRepository)
        val getInitialRestaurantsUseCase = GetInitialRestaurantsUseCase(
            restaurantsRepository,
            getSortedRestaurantUseCase
        )
        val toggleRestaurantUseCase = ToggleRestaurantUseCase(
            restaurantsRepository,
            getSortedRestaurantUseCase
        )
        return RestaurantsViewModel(
            getInitialRestaurantsUseCase,
            toggleRestaurantUseCase,
            dispatcher
        )
    }

    @Test
    fun initialState_isProduced() = scope.runTest{
        val viewModel = getViewModel()
        val initialState = viewModel.state.value
        assert(
            initialState == RestaurantsScreenState(
                restaurants = emptyList(),
                isLoading = true,
                error = null
            )
        )
    }

    @Test
    fun stateWithContent_isProduced() = scope.runTest {
        val testVM = getViewModel()
        advanceUntilIdle()
        val currentState = testVM.state.value
        assert(
            currentState == RestaurantsScreenState(
                restaurants = DummyContent.getDomainRestaurants(),
                isLoading = false,
                error = null
            )
        )
    }
}