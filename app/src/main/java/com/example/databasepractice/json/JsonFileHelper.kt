package com.example.databasepractice.json

import android.content.Context
import android.os.Build
import android.util.Log
import com.example.databasepractice.model.Staff
import org.json.JSONArray
import org.json.JSONException
import java.io.*
import java.nio.charset.StandardCharsets


object JsonFileHelper {
    val staffList = mutableListOf<Staff>()
    private const val filename = "data.json"

    private fun readJsonStringFromFile(context: Context): String {
        val jsonFile = File(context.filesDir.path + "/data.json")
        Log.d("File Management Helper", "File Path: " + jsonFile.path)
        var result = ""

        try {
            val fis: FileInputStream = context.openFileInput(filename)
            val charsetName: String = if (Build.VERSION.SDK_INT <= 19) "UTF-8" else StandardCharsets.UTF_8.name()
            val inputStreamReader = InputStreamReader(fis, charsetName)
            val stringBuilder = StringBuilder()
            try {
                BufferedReader(inputStreamReader).use { reader ->
                    var line: String = reader.readLine()
                    while (line.isNotEmpty()) {
                        stringBuilder.append(line).append('\n')
                        line = reader.readLine()
                    }
                }
            } catch (e: IOException) {
                // Error occurred when opening raw file for reading.
                e.printStackTrace()
            } finally {
                result = stringBuilder.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun initSampleData(context: Context) {
        try {
            val array = JSONArray(readJsonStringFromFile(context))
            Log.d("JSON DATA", "array crawled, length: ${array.length()}")
            val range = 0.until(array.length())
            for (i in range) {
                val jsonObject = array.getJSONObject(i)
                val jobTile = jsonObject.getString("jobTitle")
                val email = jsonObject.getString("email")
                val name = jsonObject.getString("name")
                val staff = Staff(jobTile,email,name)
                Log.d("JSON DATA", "staff: job:$jobTile; email:$email; name:$name")
                staffList.add(staff)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}

