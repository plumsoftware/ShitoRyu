package com.example.shitoryu.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shitoryu.R

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val name = findViewById<EditText>(R.id.name)
        val surname = findViewById<EditText>(R.id.surname)
        val surname2 = findViewById<EditText>(R.id.surname2)
        val birthday = findViewById<EditText>(R.id.birthday)
        val btnsave = findViewById<Button>(R.id.btnsave)

        btnsave.setOnClickListener {
            val nameStr = name.text.toString()
            val surnameStr = surname.text.toString()
            val surname2Str = surname2.text.toString()
            val birthdayStr = birthday.text.toString()

            if (nameStr.isNotEmpty() && surnameStr.isNotEmpty() && surname2Str.isNotEmpty() && birthdayStr.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("name", nameStr)
                editor.putString("surname", surnameStr)
                editor.putString("surname2", surname2Str)
                editor.putString("birthday", birthdayStr)
                editor.apply()

                startActivity(Intent(this, TimetableActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Заполните все данные профиля!", Toast.LENGTH_LONG).show()
            }
        }
    }
}