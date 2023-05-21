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

enum class NewsLoading {
    LOADING, SUCCESS, ERROR, SAVED_LOADING, SAVED_ERROR
}

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val prefs =
        getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)
    private var _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private var _favorites = MutableLiveData<List<News>>()
    val favorites: LiveData<List<News>> get() = _favorites

    private var _detail = MutableLiveData<News>()
    val detail: LiveData<News> get() = _detail

    private var _status = MutableLiveData(NewsLoading.LOADING)
    val status: LiveData<NewsLoading> get() = _status

    fun getNews() {
        _status.value = NewsLoading.LOADING
        viewModelScope.launch {
            try {
                val news = DiskoperindagApiService.UserApi.retrofitService.getNews()
                _news.value = news
                Log.d("SUCCCEPTION", news.toString())
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
                _status.value = NewsLoading.SAVED_ERROR
                _status.value = NewsLoading.ERROR
            }
        }

    }

    fun getNews(id: String) {

        _status.value = NewsLoading.LOADING
        viewModelScope.launch {
            try {
                Log.d("CEPTIONNNNISTA", id)
                val detail: News = DiskoperindagApiService.UserApi.retrofitService.getNews(id)
                _detail.value = detail
                _status.value = NewsLoading.SUCCESS
                Log.d("OKCEPTION", "${detail} really?")
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
                _status.value = NewsLoading.ERROR
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
                Log.d("CEPTION FAV", favorites.toString())
                _favorites.value = favorites
                _status.value = NewsLoading.SUCCESS
            } catch (e: Exception) {
                _status.value = NewsLoading.ERROR
                Log.d("ERROR FAV", favorites.toString())
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
                _status.value = NewsLoading.ERROR
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
                _status.value = NewsLoading.ERROR
            }
        }
    }

}
