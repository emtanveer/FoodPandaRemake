package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.Entity

/**
 * Create a third class to represent an associative
 * entity (or cross-reference table) between the two entities i.e. parent entity and restaurant entity.
 */
@Entity(primaryKeys = ["restaurantId", "parentId"])
data class ParentRestaurantCrossRef(
    val restaurantId: Int,
    val parentId: Int
)
