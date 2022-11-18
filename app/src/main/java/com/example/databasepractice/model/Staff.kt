package com.example.databasepractice.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Staff(@PrimaryKey var id: Int, var jobTitle: String, var email: String, var name: String): RealmObject
