package com.example.shitoryu.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shitoryu.R
import com.example.shitoryu.adapter.CalendarAdapter
import com.example.shitoryu.model.EventData

class TimetableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val username = findViewById<TextView>(R.id.username)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val recyclerViewCalendar = findViewById<RecyclerView>(R.id.recyclerViewCalendar)
        val recyclerViewTimeTable = findViewById<RecyclerView>(R.id.recyclerViewTimeTable)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)

        val list: List<EventData> = listOf(
            EventData(
                dayOfMonth = 1,
                month = 4,
                year = 2024,
                hour = 11,
                minute = 30,
                isCompetition = 0,
                place = "Омск",
                group = "Шамрай"
            ),
            EventData(
                dayOfMonth = 11,
                month = 4,
                year = 2024,
                hour = 11,
                minute = 30,
                isCompetition = 0,
                place = "Омск",
                group = "Шамрай"
            ),

            EventData(
                dayOfMonth = 16,
                month = 4,
                year = 2024,
                hour = 21,
                minute = 55,
                isCompetition = 1,
                place = "Омск",
                group = "Шамрай Дмитрий"
            )
        )
        val calendarAdapter = CalendarAdapter(list)
        recyclerViewCalendar.adapter = calendarAdapter
        recyclerViewCalendar.layoutManager = GridLayoutManager(this, 7)
        val fullName = sharedPreferences.getString("name", "") + " " + sharedPreferences.getString("surname", "")
        username.text = fullName

        buttonSignUp.setOnClickListener {

        }
    }
}