package com.example.databasepractice.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databasepractice.R

class DataAdapter(private var cursor: Cursor, private var context: Context) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    init {
        cursor.moveToFirst()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView = itemView.findViewById<TextView>(R.id.id_tv)
        val jobTitleTextView = itemView.findViewById<TextView>(R.id.job_title_tv)
        val emailTextView = itemView.findViewById<TextView>(R.id.email_tv)
        val nameTextView = itemView.findViewById<TextView>(R.id.name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = cursor.getInt(0)
        val jobTitle = cursor.getString(1)
        val email = cursor.getString(2)
        val name = cursor.getString(3)
        cursor.moveToNext()

        holder.idTextView.text = id.toString()
        holder.jobTitleTextView.text = jobTitle
        holder.emailTextView.text = email
        holder.nameTextView.text = name
    }

    override fun getItemCount(): Int {
        return cursor.count
    }
}