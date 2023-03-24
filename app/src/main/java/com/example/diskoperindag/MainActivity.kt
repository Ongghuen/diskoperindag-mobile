package com.example.diskoperindag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

}