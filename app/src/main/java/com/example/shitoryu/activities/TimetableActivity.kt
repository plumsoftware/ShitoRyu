package com.example.shitoryu.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shitoryu.R

class TimetableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val username = findViewById<TextView>(R.id.username)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)

        val fullName = sharedPreferences.getString("name", "") + " " + sharedPreferences.getString("surname", "")
        username.text = fullName

        buttonSignUp.setOnClickListener {

        }
    }
}