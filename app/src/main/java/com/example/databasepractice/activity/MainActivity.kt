package com.example.databasepractice.activity

import android.annotation.TargetApi
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.databasepractice.R
import com.example.databasepractice.adapter.DataAdapter
import com.example.databasepractice.database.dao.SQLiteDAO
import com.example.databasepractice.database.realm.StaffDataRealmHelper
import com.example.databasepractice.json.JsonFileHelper
import io.realm.kotlin.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val SQLITE_MODE = 1
        const val REALM_MODE = 2
    }

    lateinit var sqlCursor: Cursor
    lateinit var adapter: DataAdapter
    var dbType = SQLITE_MODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ask for external storage permission
        if (shouldAskPermissions()) {
            askPermissions()
        }

        JsonFileHelper.initSampleData(this)

        val dao = SQLiteDAO(this)
        dao.open()
        val realmHelper = StaffDataRealmHelper(this)
        realmHelper.open()

        insert_btn.setOnClickListener {
            if (dbType == SQLITE_MODE)
                dao.insert(JsonFileHelper.staffList)
            else
                realmHelper.insert(JsonFileHelper.staffList)
        }

        update_btn.setOnClickListener {
            dao.update(JsonFileHelper.staffList)
        }

        delete_btn.setOnClickListener {
            dao.delete()
        }

        read_btn.setOnClickListener {
            sqlCursor = dao.read()
            initRecyclerView()
        }

        db_type_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dbType = SQLITE_MODE
                db_type_label.text = "SQLite"
            }
            else {
                dbType = REALM_MODE
                db_type_label.text = "Realm"
            }
        }
    }

    fun insert() {

    }

    fun update() {

    }

    fun delete() {

    }

    fun read() {


    }

    private fun initRecyclerView() {
        adapter = DataAdapter(cursor = sqlCursor, context = this)
        adapter.notifyDataSetChanged()
        recycler_view.adapter = adapter
    }

    //request external memory permission
    private fun shouldAskPermissions(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
    }

    /**
     * This function request for important permissions, if user accept, application will work correctly
     */
    @TargetApi(23)
    private fun askPermissions() {
        val permissions = arrayOf(
            "android.permission.MANAGE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
        )
        val requestCode = 200
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}