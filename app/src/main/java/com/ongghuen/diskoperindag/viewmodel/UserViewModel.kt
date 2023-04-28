package com.ongghuen.diskoperindag.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ongghuen.diskoperindag.model.User
import com.ongghuen.diskoperindag.model.UserRequest
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel : ViewModel() {
    private var _currentUser = MutableLiveData<User?>()
    val currentUser: MutableLiveData<User?> get() = _currentUser

    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: MutableLiveData<Boolean> get() = _isLoggedIn

    fun login(email: String, password: String) {
        val userRequest = UserRequest(email, password)

        viewModelScope.launch {
            try {
                val result: User =
                    DiskoperindagApiService.UserApi.retrofitService.login(userRequest)
                _currentUser.value = result
                _isLoggedIn.value = true
                Log.d("OKCEPTION!", result.toString())
            } catch (e: Exception) {
                Log.d("ERROR LOL DUMASS", e.message.toString())
                _isLoggedIn.value = false
            }
        }

    }

    fun logout() {
        viewModelScope.launch {
            try {
                val result = DiskoperindagApiService.UserApi.retrofitService.logout(_currentUser.value!!.token)
                _currentUser.value?.token = ""
                _isLoggedIn.value = false
                Log.d("OKCEPTION!", result.toString())
            }catch (e: Exception){
                Log.d("ERROR LOL!", "$e")
            }
        }
    }
}