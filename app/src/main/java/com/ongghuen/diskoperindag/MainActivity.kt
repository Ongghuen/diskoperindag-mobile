package com.ongghuen.diskoperindag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ongghuen.diskoperindag.databinding.ActivityMainBinding

// ini adalah activity utama dari project kami
// kami menggunakan fragment untuk seluruh screen
// di xml mainactivity kami menggunakan navhost yang nantinya masuk ke dalam dashboard
// dan di dalam dashboard tersebut akan ada navhost baru untuk navigasinya
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}