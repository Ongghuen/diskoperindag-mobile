package com.ongghuen.diskoperindag.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ongghuen.diskoperindag.model.User
import com.ongghuen.diskoperindag.model.UserRequest
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch
import java.lang.Exception

enum class UserLoading {
    INIT, LOADING, SUCCESS, FINISH, ERROR
}

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val prefs = getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)

    private var _currentUser = MutableLiveData<User>()
    val currentUser: MutableLiveData<User> get() = _currentUser

    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: MutableLiveData<Boolean> get() = _isLoggedIn

    private var _status = MutableLiveData(UserLoading.INIT)
    val status: MutableLiveData<UserLoading> get() = _status

    fun login(email: String, password: String) {

        _status.value = UserLoading.LOADING
        val userRequest = UserRequest(email, password)

        viewModelScope.launch {
            try {
                val result: User =
                    DiskoperindagApiService.UserApi.retrofitService.login(userRequest)

                _status.value = UserLoading.SUCCESS

                _currentUser.value = result
                _isLoggedIn.value = true

                with(prefs.edit()){
                    putString("email", email)
                    putString("password", password)
                    apply()
                }

                saveSession(isLoggedIn.value!!)

            } catch (e: Exception) {
                _status.value = UserLoading.ERROR
            }
        }

    }

    fun logout() {
        _status.value = UserLoading.FINISH
        viewModelScope.launch {
            try {
                val result =
                    DiskoperindagApiService.UserApi.retrofitService.logout(_currentUser.value!!.token)
                _currentUser.value?.token = ""
                _isLoggedIn.value = false

                prefs.edit().clear().apply()

                Log.d("USERVIEWMODEL OKCEPTION", result.toString())
            } catch (e: Exception) {
                Log.d("USERVIEWMODEL ERROR LOL!", "$e")
            }
        }
    }

    fun saveSession(isLoggedIn: Boolean){
        with(prefs.edit()){
            putBoolean("isLoggedIn", isLoggedIn)
            apply()
        }
    }


    init {
        if (prefs.getBoolean("isLoggedIn", false) == true){
            _isLoggedIn.value = true
            login(prefs.getString("email", "").toString(), prefs.getString("password", "").toString())
        }else{
            _isLoggedIn.value = false
        }
    }
}