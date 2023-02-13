package com.fpremake.screens_post_login.screen_dashboard.data.realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
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

class Parent() : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.create()
    var pName: String = ""
    var childs: RealmList<Child> = realmListOf()
    constructor(name: String) : this() {
        this.pName = name
    }
}

class Child() : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId.create()
    var name: String = ""
    var parent: Parent? = null
    constructor(name: String) : this() {
        this.name = name
    }
}

