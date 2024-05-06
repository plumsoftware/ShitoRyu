package com.example.shitoryu.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shitoryu.R
import com.example.shitoryu.database.DatabaseHelper
import com.example.shitoryu.model.EventData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Write to database
        val list: List<EventData> = listOf(
            EventData(),
            EventData(
                dayOfMonth = 2,
                month = 4,
                year = 2024,
                hour = 16,
                minute = 0,
                isCompetition = 0,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(
                dayOfMonth = 4,
                month = 4,
                year = 2024,
                hour = 17,
                minute = 30,
                isCompetition = 0,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(),
            EventData(),
            EventData(
                dayOfMonth = 8,
                month = 4,
                year = 2024,
                hour = 17,
                minute = 30,
                isCompetition = 0,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(
                dayOfMonth = 10,
                month = 4,
                year = 2024,
                hour = 17,
                minute = 30,
                isCompetition = 0,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(),
            EventData(
                dayOfMonth = 13,
                month = 4,
                year = 2024,
                hour = 17,
                minute = 30,
                isCompetition = 0,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(
                dayOfMonth = 20,
                month = 4,
                year = 2024,
                hour = 9,
                minute = 30,
                isCompetition = 1,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(
                dayOfMonth = 21,
                month = 4,
                year = 2024,
                hour = 10,
                minute = 0,
                isCompetition = 1,
                place = "Молодова 20/1",
                group = "Занятие по карате"
            ),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData(),
            EventData()
        )

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isFirst", true)) {
            val dbHelper = DatabaseHelper(this)
            list.forEach {
                dbHelper.addEvent(it)
            }
            sharedPreferences.edit().putBoolean("isFirst", false).apply()
        }


        val editTextTextLogin = findViewById<EditText>(R.id.editTextTextLogin)
        val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        buttonLogin.setOnClickListener {
            val login = editTextTextLogin.text.toString()
            val password = editTextTextPassword.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                val sp = getSharedPreferences("user", Context.MODE_PRIVATE)
                val l = sp.getString("login", "")
                val p = sp.getString("password", "")

                if (p.equals(password) && l.equals(login)) {
                    startActivity(Intent(this, TimetableActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_LONG).show()
                }
            }
        }
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }
}