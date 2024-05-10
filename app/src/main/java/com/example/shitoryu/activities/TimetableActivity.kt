package com.example.shitoryu.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shitoryu.R
import com.example.shitoryu.adapter.CalendarAdapter
import com.example.shitoryu.adapter.TimetableAdapter
import com.example.shitoryu.database.DatabaseHelper
import com.example.shitoryu.model.EventData
import com.example.shitoryu.model.OnItemClickedListener

class TimetableActivity : AppCompatActivity() {

    companion object {
        var selectedEvent: EventData = EventData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val username = findViewById<TextView>(R.id.username)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val recyclerViewCalendar = findViewById<RecyclerView>(R.id.recyclerViewCalendar)
        val recyclerViewTimeTable = findViewById<RecyclerView>(R.id.recyclerViewTimeTable)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)

        val dbHelper = DatabaseHelper(this)
        val list = dbHelper.getEvents()

        val calendarAdapter = CalendarAdapter(list)
        recyclerViewCalendar.adapter = calendarAdapter
        recyclerViewCalendar.layoutManager = GridLayoutManager(this, 7)
        val fullName = sharedPreferences.getString("name", "") + " " + sharedPreferences.getString(
            "surname",
            ""
        )
        username.text = fullName

        val sortedList: MutableList<EventData> = mutableListOf()
        list.filter { it.isCompetition != -1 && it.isCompetition == 0 }.forEach {
            sortedList.add(it)
        }
        list.filter { it.isCompetition != -1 && it.isCompetition == 1}.forEach {
            sortedList.add(it)
        }
//        val listener = object : OnItemClickedListener {
//            override fun onItemClicked(position: Int) {
//                selectedEvent = sortedList[position]
//            }
//        }

        val timetableAdapter = TimetableAdapter(sortedList.toList())
//        timetableAdapter.setOnItemClickedListener(listener)
        recyclerViewTimeTable.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewTimeTable.adapter = timetableAdapter

        buttonSignUp.setOnClickListener {
            if (selectedEvent == EventData()) {
                Toast.makeText(this, "Выберите хотя бы одно занятие", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(
                    this,
                    EventActivity::class.java
                ).apply {
                    putExtra("event", selectedEvent)
                }
                startActivity(intent)
            }
        }
    }
}