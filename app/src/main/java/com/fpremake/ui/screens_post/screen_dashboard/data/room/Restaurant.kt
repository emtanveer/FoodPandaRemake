package com.fpremake.ui.screens_post.screen_dashboard.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey(autoGenerate = true)
    val restaurantId: Int = 0,
    var restaurantName: String? = "",
    @Embedded var restaurantMenu: Menu? = null,
)


data class Menu(
    @PrimaryKey(autoGenerate = true)
    var menuId: Int = 0,
    var menuName: String? = ""
)
