package com.example.databasepractice.database.sqlite

import android.provider.BaseColumns

object StaffDataContract {
    object StaffData: BaseColumns {
        const val TABLE_NAME = "staffData"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_JOB_TITLE = "jobTitle"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_NAME = "name"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${StaffData.TABLE_NAME} (" +
                "${StaffData.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${StaffData.COLUMN_NAME_JOB_TITLE} TEXT," +
                "${StaffData.COLUMN_NAME_EMAIL} TEXT, " +
                "${StaffData.COLUMN_NAME_NAME} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${StaffData.TABLE_NAME}"


}