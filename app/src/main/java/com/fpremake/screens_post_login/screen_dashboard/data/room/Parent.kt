package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parent")
data class Parent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "parentId")
    var parentId:Long = 0,
    @ColumnInfo(name = "name") var name: String? = "",
)
