package com.ongghuen.diskoperindag.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private var _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news


    fun getNews() {

        viewModelScope.launch {
            try {
                val lol = DiskoperindagApiService.UserApi.retrofitService.getNews()
                _news.value = lol
                Log.d("OKCEPTION", "${lol} really?")
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }

    }

    init {
        getNews()
    }
}
