package com.fpremake.screens_post_login.screen_dashboard.data

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class User(): RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var isComplete: Boolean = false
    var firstName: String = ""
    var emoji: String = ""
    var father_id: String = ""
    var event : String = "default" // partition key

    //For Relation b/w father and son(i.e. User here in our case)
    constructor(fatherId: String = "") : this() {
        father_id = fatherId
    }
}