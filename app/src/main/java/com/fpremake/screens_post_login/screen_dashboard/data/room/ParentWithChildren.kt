package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class ParentWithChildren(
    @Embedded val parent: Parent,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val child: List<Child>,
)
