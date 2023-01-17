package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child")
class Child(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "firstName") val firstName: String? = "",
    @ColumnInfo(name = "parentId") var parentId: Long? = 0,
)



