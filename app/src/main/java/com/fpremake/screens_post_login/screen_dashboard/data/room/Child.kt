package com.fpremake.screens_post_login.screen_dashboard.data.room

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
    var childId: Long = 0,
    @ColumnInfo(name = "firstName") val firstName: String? = "",
//    @ForeignKey(
//        Parent::class.java,
//        parentColumns = "id",
//        childColumns = "parentId",
//        onDelete = CASCADE
//    )
    @ColumnInfo(name = "id_fkparent", index = true)
    var id_fkparent: Long? = 0,
)



