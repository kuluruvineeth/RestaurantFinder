package com.kuluruvineeth.restaurantfinder

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.kuluruvineeth.restaurantfinder.restaurants.DummyContent
import com.kuluruvineeth.restaurantfinder.restaurants.presentation.Description
import com.kuluruvineeth.restaurantfinder.restaurants.presentation.list.RestaurantsScreenState
import com.kuluruvineeth.restaurantfinder.ui.theme.RestaurantFinderTheme
import org.junit.Rule
import org.junit.Test

class RestaurantsScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun initialState_isRendered(){
        testRule.setContent {
            RestaurantFinderTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = emptyList(),
                        isLoading = true
                    ),
                    onFavoriteClick = {
                        _: Int,
                        _: Boolean ->
                    },
                    onItemClick = {}
                )
            }
        }
        testRule.onNodeWithContentDescription(
            Description.RESTAURANTS_LOADING
        ).assertIsDisplayed()
        //Thread.sleep(5000)
    }

    @Test
    fun stateWithContent_isRendered(){
        val restaurants = DummyContent.getDomainRestaurants()
        testRule.setContent {
            RestaurantFinderTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = restaurants,
                        isLoading = false
                    ),
                    onItemClick = {},
                    onFavoriteClick = {
                        _: Int,
                        _: Boolean ->
                    }
                )
            }
        }
        testRule.onNodeWithText(restaurants[0].title)
            .assertIsDisplayed()
        testRule.onNodeWithText(restaurants[0].description)
            .assertIsDisplayed()
        testRule.onNodeWithContentDescription(
            Description.RESTAURANTS_LOADING
        ).assertDoesNotExist()
    }

    @Test
    fun stateWithContent_ClickOnItem_isRegistered(){
        val restaurants = DummyContent.getDomainRestaurants()
        val targetRestaurant = restaurants[0]
        testRule.setContent {
            RestaurantFinderTheme {
                RestaurantsScreen(
                    state = RestaurantsScreenState(
                        restaurants = restaurants,
                        isLoading = false
                    ),
                    onItemClick = {id ->
                        assert(id==targetRestaurant.id)
                    },
                    onFavoriteClick = {_,_ ->}
                )
            }
        }
        testRule.onNodeWithText(targetRestaurant.title)
            .performClick()
    }
}