package com.ongghuen.diskoperindag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ongghuen.diskoperindag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}