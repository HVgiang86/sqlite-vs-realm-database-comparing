package com.example.databasepractice.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Staff(): RealmObject {
    constructor(id: Int, jobTitle: String, email: String, name: String) : this() {
        this.id = id
        this.jobTitle = jobTitle
        this.email = email
        this.name = name
    }

    @PrimaryKey var id: Int = 0
    var jobTitle: String = ""
    var email: String = ""
    var name: String = ""

}
