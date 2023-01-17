package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "isComplete") val isComplete: Boolean? = false,
    @ColumnInfo(name = "firstName") val firstName: String? = "",
    @ColumnInfo(name = "emoji") val emoji: String? = "",
    @ColumnInfo(name = "father_id") val father_id: String? = "",
    @ColumnInfo(name = "event") val event: String? = "",
)