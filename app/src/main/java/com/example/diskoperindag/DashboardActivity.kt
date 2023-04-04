package com.example.diskoperindag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.diskoperindag.adapter.NewsAdapter
import com.example.diskoperindag.data.Datasource
import com.example.diskoperindag.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Datasource().loadAffirmation()
        binding.newsRecyclerView.adapter = NewsAdapter(this, data)
        binding.newsRecyclerView.setHasFixedSize(true)
    }
}