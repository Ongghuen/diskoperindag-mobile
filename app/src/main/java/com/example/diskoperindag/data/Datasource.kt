package com.example.diskoperindag.data

import com.example.diskoperindag.R
import com.example.diskoperindag.model.News

class Datasource {
    fun loadAffirmation(): List<News>{
        return listOf(
            News(R.string.news1),
            News(R.string.news2),
            News(R.string.news3),
        )
    }
}