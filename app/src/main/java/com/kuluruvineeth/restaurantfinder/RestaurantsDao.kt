package com.kuluruvineeth.restaurantfinder

import androidx.room.*

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    suspend fun getAll() : List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)

    @Update(entity = Restaurant::class)
    suspend fun update(partialRestaurant: PartialRestaurant)

    @Update(entity = Restaurant::class)
    suspend fun updateAll(partialRestaurant: List<PartialRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite=1")
    suspend fun getAllFavorited(): List<Restaurant>
}