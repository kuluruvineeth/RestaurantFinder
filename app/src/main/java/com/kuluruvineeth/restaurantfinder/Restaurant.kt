package com.kuluruvineeth.restaurantfinder

data class Restaurant(
    val id: Int,
    val title: String,
    val description: String,
    var isFavorite: Boolean = false
)

val dummyRestaurants = listOf(
    Restaurant(0,"Alfredo's foods","At Alfredo's..."),
    Restaurant(1,"Mike and Ben foods","At Alfredo's..."),
    Restaurant(2,"Alfredo's foods","At Alfredo's..."),
    Restaurant(3,"AgriRize foods","At Alfredo's..."),
    Restaurant(4,"agriRize foods","At Alfredo's..."),
    Restaurant(5,"agririze foods","At Alfredo's..."),
    Restaurant(6,"Alfredo's foods","At Alfredo's..."),
    Restaurant(7,"Alfredo's foods","At Alfredo's..."),
    Restaurant(8,"Alfredo's foods","At Alfredo's..."),
    Restaurant(8,"Alfredo's foods","At Alfredo's..."),
    Restaurant(8,"Alfredo's foods","At Alfredo's...")
)
