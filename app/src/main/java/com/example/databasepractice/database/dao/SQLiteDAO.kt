package com.example.databasepractice.database.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.databasepractice.database.sqlite.StaffDataContract
import com.example.databasepractice.database.sqlite.StaffDataSQLiteHelper
import com.example.databasepractice.model.Staff
import com.example.databasepractice.utils.SqliteRealmComparing

class SQLiteDAO(context: Context) {
    private val staffDataSQLiteHelper = StaffDataSQLiteHelper(context)
    private lateinit var db: SQLiteDatabase
    fun open() {
        db = staffDataSQLiteHelper.writableDatabase
    }

    fun insert(staffList: MutableList<Staff>) {
        val startActionTimePoint = System.currentTimeMillis()

        for (i in 0..99999) {
            val values = ContentValues()
            values.put(StaffDataContract.StaffData.COLUMN_NAME_ID, i + 1)
            values.put(
                StaffDataContract.StaffData.COLUMN_NAME_JOB_TITLE, staffList[i % 1000].jobTitle
            )
            values.put(StaffDataContract.StaffData.COLUMN_NAME_EMAIL, staffList[i % 1000].email)
            values.put(StaffDataContract.StaffData.COLUMN_NAME_NAME, staffList[i % 1000].name)
            val rowId = db.insert(StaffDataContract.StaffData.TABLE_NAME, null, values)
            Log.d("SQLite Tag", "inserted row id $rowId")
        }

        SqliteRealmComparing.sqliteInsertTime = System.currentTimeMillis() - startActionTimePoint
        Log.d("SQLite Tag", "insertion time of sqlite: ${SqliteRealmComparing.sqliteInsertTime}")

    }

    fun delete() {
        val startActionTimePoint = System.currentTimeMillis()
        db.delete(StaffDataContract.StaffData.TABLE_NAME, null, null)
        SqliteRealmComparing.sqLiteDeleteTime = System.currentTimeMillis() - startActionTimePoint
        Log.d("SQLite Tag", "deletion time of sqlite: ${SqliteRealmComparing.sqLiteDeleteTime}")
        Log.d("SQLite Tag", "File page: ${db.pageSize}")
    }

    fun update(staffList: MutableList<Staff>) {
        val startActionTimePoint = System.currentTimeMillis()

        for (i in 0..99999) {
            val values = ContentValues()
            values.put(
                StaffDataContract.StaffData.COLUMN_NAME_JOB_TITLE, staffList[i % 999 + 1].jobTitle
            )
            values.put(StaffDataContract.StaffData.COLUMN_NAME_EMAIL, staffList[i % 999 + 1].email)
            values.put(StaffDataContract.StaffData.COLUMN_NAME_NAME, staffList[i % 999 + 1].name)
            val selection = "${StaffDataContract.StaffData.COLUMN_NAME_ID} = ?"
            val selectionArgs = arrayOf("${i+1}")
            db.update(StaffDataContract.StaffData.TABLE_NAME, values, selection, selectionArgs)
        }

        SqliteRealmComparing.sqliteUpdateTime = System.currentTimeMillis() - startActionTimePoint
        Log.d("SQLite Tag", "update time of sqlite: ${SqliteRealmComparing.sqliteUpdateTime}")
    }

    @SuppressLint("Recycle")
    fun read(): Cursor{
        val startActionTimePoint = System.currentTimeMillis()
        val projection = arrayOf(StaffDataContract.StaffData.COLUMN_NAME_ID,
            StaffDataContract.StaffData.COLUMN_NAME_JOB_TITLE,
            StaffDataContract.StaffData.COLUMN_NAME_EMAIL,
            StaffDataContract.StaffData.COLUMN_NAME_NAME)

        val cursor = db.query(StaffDataContract.StaffData.TABLE_NAME,projection,null,null,null,null,null)
        SqliteRealmComparing.sqliteReadTime = System.currentTimeMillis() - startActionTimePoint
        Log.d("SQLite Tag", "reading time of sqlite: ${SqliteRealmComparing.sqliteReadTime}")
        Log.d("SQLite Tag","records count: ${cursor.count}")
        return cursor
    }
}