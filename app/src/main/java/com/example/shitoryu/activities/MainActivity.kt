package com.example.shitoryu.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shitoryu.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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