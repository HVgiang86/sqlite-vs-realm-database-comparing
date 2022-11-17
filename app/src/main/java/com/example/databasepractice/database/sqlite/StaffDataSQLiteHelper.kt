package com.example.databasepractice.database.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class StaffDataSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "staff.db"
        const val DATABASE_VERSION = 1
        const val TAG = "SQLite Helper Tag"
    }




    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(StaffDataContract.SQL_CREATE_ENTRIES)
        Log.d(TAG,"DB created!")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(StaffDataContract.SQL_DELETE_ENTRIES)
        onCreate(db)
        Log.d(TAG, "Database upgraded!")
    }


}