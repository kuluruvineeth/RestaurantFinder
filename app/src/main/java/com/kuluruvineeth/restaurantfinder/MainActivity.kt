package com.kuluruvineeth.restaurantfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.kuluruvineeth.restaurantfinder.ui.theme.RestaurantFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantFinderTheme {
                //RestaurantsScreen()
                //RestaurantDetailsScreen()
                RestaurantApp()
            }
        }
    }
}

@Composable
private fun RestaurantApp(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "restaurants"){
        composable(route="restaurants"){
            RestaurantsScreen{
                id->
                navController.navigate("restaurants/$id")
            }
        }
        composable(
            route="restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id"){
                type = NavType.IntType
            }),
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "www.restaurantsapp.details.com/{restaurant_id}"
            })
        ){
            RestaurantDetailsScreen()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}