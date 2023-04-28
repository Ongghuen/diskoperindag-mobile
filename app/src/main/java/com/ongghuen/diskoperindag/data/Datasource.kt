package com.ongghuen.diskoperindag.data

import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.model.News

class Datasource {
    fun loadAffirmation(): List<News>{
        return listOf(
            News(R.string.news1),
            News(R.string.news2),
            News(R.string.news3),
        )
    }
}