package com.fpremake.ui.screens_post.screen_dashboard.data.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ParentWithRestaurants(
    @Embedded val parent: Parent,

    @Relation(
        parentColumn = "parentId",
        entityColumn = "restaurantId",
        associateBy = Junction(ParentRestaurantCrossRef::class)
    )
    val restaurant: List<Restaurant>
)


data class RestaurantWithParents(
    @Embedded val restaurant: Restaurant,

    @Relation(
        parentColumn = "restaurantId",
        entityColumn = "parentId",
        associateBy = Junction(ParentRestaurantCrossRef::class)
    )
    val parent: List<Parent>
)
