package com.example.shitoryu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.shitoryu.R

class SpinnerAdapter(private val list: List<String>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)

        view.findViewById<TextView>(R.id.place).text = list[p0]

        return view
    }
}