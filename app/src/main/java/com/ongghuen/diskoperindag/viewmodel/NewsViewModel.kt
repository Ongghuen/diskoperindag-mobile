package com.ongghuen.diskoperindag.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val prefs =
        getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)
    private var _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private var _favorites = MutableLiveData<List<News>>()
    val favorites: LiveData<List<News>> get() = _favorites

    fun getNews() {

        viewModelScope.launch {
            try {
                val news = DiskoperindagApiService.UserApi.retrofitService.getNews()
                _news.value = news
                Log.d("OKCEPTION", "${news} really?")
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }

    }

    fun getFavorite() {
        viewModelScope.launch {
            try {
                val favorites = DiskoperindagApiService.UserApi.retrofitService.getNewsFavorite(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${favorites}")
                _favorites.value = favorites
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    fun addFavorite(id: String) {
        viewModelScope.launch {
            try {
                DiskoperindagApiService.UserApi.retrofitService.addNewsFavorite(
                    "Bearer " + prefs.getString(
                        "token", ""
                    ), id
                )
                getFavorite()
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            try {
                DiskoperindagApiService.UserApi.retrofitService.deleteNewsFavorite(
                    "Bearer " + prefs.getString(
                        "token", ""
                    ), id
                )
                getFavorite()
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    init {
        getNews()
        getFavorite()
    }
}
