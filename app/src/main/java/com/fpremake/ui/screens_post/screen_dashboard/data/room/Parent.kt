package com.fpremake.ui.screens_post.screen_dashboard.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parent")
data class Parent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "parentId")
    var parentId:Int = 0,
    @ColumnInfo(name = "name") var name: String? = "",
)
