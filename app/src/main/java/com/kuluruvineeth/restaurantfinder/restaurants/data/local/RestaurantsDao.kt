package com.kuluruvineeth.restaurantfinder.restaurants.data.local

import androidx.room.*
import com.kuluruvineeth.restaurantfinder.PartialLocalRestaurant

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    suspend fun getAll() : List<LocalRestaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<LocalRestaurant>)

    @Update(entity = LocalRestaurant::class)
    suspend fun update(partialRestaurant: PartialLocalRestaurant)

    @Update(entity = LocalRestaurant::class)
    suspend fun updateAll(partialRestaurant: List<PartialLocalRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite=1")
    suspend fun getAllFavorited(): List<LocalRestaurant>
}