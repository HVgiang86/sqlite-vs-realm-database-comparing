package com.example.databasepractice.database.realm

import android.content.Context
import android.util.Log
import com.example.databasepractice.model.Staff
import com.example.databasepractice.utils.SqliteRealmComparing
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class StaffDataRealmHelper(context: Context) {
    private lateinit var realm: Realm

    fun open() {
        val config = RealmConfiguration.Builder(setOf(Staff::class))
            .build()
        realm = Realm.open(config)
        Log.d("Realm Log","Successfully opened realm: ${realm.configuration.name}")
    }

    suspend fun insert(staffList: MutableList<Staff>) {
        val startActionTimePoint = System.currentTimeMillis()

        for (i in 0..99999) {
            staffList[i].id = i
            realm.write {
                this.copyToRealm(staffList[i % 1000])
            }
            Log.d("Realm Log","Inserted #$i")
        }

        SqliteRealmComparing.realmInsertTime = System.currentTimeMillis() - startActionTimePoint
        Log.d("Realm Log", "insertion time of sqlite: ${SqliteRealmComparing.realmInsertTime}")
    }

    suspend fun update(staffList: MutableList<Staff>) {
        for (i in 0..99999) {
            realm.write {
                val staff: Staff? =
                    this.query<Staff>("id == $0", i).first().find()

                staff?.jobTitle = staffList[i % 999 + 1].jobTitle
                staff?.email = staffList[i % 999 + 1].email
                staff?.name = staffList[i % 999 + 1].name

            }
            Log.d("Realm Log","updated #$i")
        }

    }

    suspend fun delete() {
        for (i in 0..99999) {
            realm.write {
                val staff: Staff =
                    this.query<Staff>("id == $0", i).first().find()!!
                delete(staff)
            }
            Log.d("Realm Log","updated #$i")
        }
    }

    fun read(): RealmResults<Staff> {
        return realm.query<Staff>().find()

    }

    fun close() {
        realm.close()
    }
}