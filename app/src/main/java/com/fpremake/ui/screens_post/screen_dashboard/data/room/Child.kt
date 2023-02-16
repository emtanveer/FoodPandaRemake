package com.fpremake.ui.screens_post.screen_dashboard.data.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "child",
    foreignKeys = [
        ForeignKey(
            entity = Parent::class,
            parentColumns = ["parentId"],
            childColumns = ["id_fkparent"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
class Child(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "childId")
    var childId: Int = 0,
    @ColumnInfo(name = "firstName") var firstName: String? = "",
    @ColumnInfo(name = "id_fkparent", index = true)
    var id_fkparent: Int? = 0,
)



