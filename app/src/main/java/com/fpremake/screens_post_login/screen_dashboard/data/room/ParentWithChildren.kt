package com.fpremake.screens_post_login.screen_dashboard.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class ParentWithChildren(
    @Embedded var parent: Parent,
   // @Relation(  parentColumn = "parentId", entityColumn = "id_fkparent")
    val children: List<Child>,
)
