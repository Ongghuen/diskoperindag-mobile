package com.ongghuen.diskoperindag.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.ongghuen.diskoperindag.model.ChangePasswordRequest
import com.ongghuen.diskoperindag.model.User
import com.ongghuen.diskoperindag.model.UserRequest
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent

enum class UserLoading {
    INIT, LOADING, SUCCESS, ERROR, FINISH, WRONG_PASSWORD, LOGOUT_ERROR
}

enum class ChangePassLoading {
    INIT, LOADING, SUCCESS, ERROR, FINISH
}

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val prefs =
        getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)

    private var _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser

    private var _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    private var _status = MutableLiveData(UserLoading.INIT)
    val status: LiveData<UserLoading> get() = _status
    private var _statusPass = MutableLiveData(ChangePassLoading.LOADING)
    val statusPass: LiveData<ChangePassLoading> get() = _statusPass

    // fungsi login untuk masuk ke dalam aplikasi
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

                with(prefs.edit()) {
                    putString("email", email)
                    putString("password", password)
                    apply()
                }

                saveSession(isLoggedIn.value!!)
                assignFCMToken()
                _status.value = UserLoading.FINISH

            } catch (e: Exception) {
                _status.value = UserLoading.WRONG_PASSWORD
                _status.value = UserLoading.ERROR
            }
        }

    }

    // fungsi ini berguna untuk menambahkan fcm token saat ini ke user
    private fun assignFCMToken() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("ERRORCEPTION FIREBASEUSERVIEWMODEL", "Fetching gagal", task.exception)
                return@OnCompleteListener
            }

            val fcm = task.result
            if (fcm == currentUser.value!!.user!!.fcm_token) return@OnCompleteListener
            viewModelScope.launch {
                try {
                    val result = DiskoperindagApiService.UserApi.retrofitService.assignToken(
                        "Bearer " + prefs.getString(
                            "token", ""
                        ), fcmToken = fcm
                    )
                    with(prefs.edit()) {
                        putString("fcmToken", fcm)
                        apply()
                    }
                    Log.d("SUCCESSCEPTION FIREBASEUSERVIEWMODEL", "$result")
                    Log.d("SUCCESSCEPTION FIREBASEUSERVIEWMODEL", "Token: ${fcm}")
                } catch (e: Exception) {
                    Log.d(
                        "ERRORCEPTION FIREBASEUSERVIEWMODEL",
                        "Token gagal ditambahkan",
                        task.exception
                    )
                }
            }
        })

    }

    // fungsi ini berguna untuk mencoba kembali sesi yang telah disave
    fun retryCachedLogin() {
        login(
            prefs.getString("email", "").toString(),
            prefs.getString("password", "").toString()
        )
    }

    // fungsi ini berguna untuk logout
    fun logout() {
        _status.value = UserLoading.LOADING

        _isLoggedIn.value = false
        prefs.edit().clear().apply()

        viewModelScope.launch {
            try {
                val result =
                    DiskoperindagApiService.UserApi.retrofitService.logout(_currentUser.value!!.token)
                _currentUser.value?.token = ""

                Log.d("USERVIEWMODEL OKCEPTION", result.toString())
                _status.value = UserLoading.FINISH
            } catch (e: Exception) {
                Log.d("USERVIEWMODEL ERROR LOL!", "$e")
                _status.value = UserLoading.LOGOUT_ERROR
            }
        }
    }

    // fungsi ini berguna untuk menyimpan sesi agar user tidak usah repot repot kembali keluar ke login screen dan login kembali
    fun saveSession(isLoggedIn: Boolean) {
        with(prefs.edit()) {
            putBoolean("isLoggedIn", isLoggedIn)
            putString("name", currentUser.value!!.user!!.name)
            putString("token", currentUser.value!!.token)
            apply()
        }
    }

    // fungsi ini berguna untuk mengganti password user
    fun changePassword(request: ChangePasswordRequest) {
        _statusPass.value = ChangePassLoading.LOADING
        viewModelScope.launch {
            try {
                val result =
                    DiskoperindagApiService.UserApi.retrofitService.changePassword(
                        "Bearer " + prefs.getString(
                            "token", ""
                        ),
                        request
                    )
                _currentUser.value?.token = ""
                _statusPass.value = ChangePassLoading.SUCCESS
                _statusPass.value = ChangePassLoading.FINISH
                Log.d("USERVIEWMODEL OKCEPTION", result.toString())
            } catch (e: Exception) {
                _statusPass.value = ChangePassLoading.ERROR
                _statusPass.value = ChangePassLoading.FINISH
                Log.d("USERVIEWMODEL ERROR LOL!", "$e")
            }
        }
    }

    // fungsi ini berguna untuk mengecek token yang disimpan di session lalu jika token sudah expired
    // maka user dikeluarkan
    private fun checkToken() {
        viewModelScope.launch {
            try {
                val result = DiskoperindagApiService.UserApi.retrofitService.checkToken(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("USERVIEWMODEL OKCEPTION", result.toString())
            } catch (e: Exception) {
                logout()
                Log.d("USERVIEWMODEL ERRORCEPTION", e.toString())
            }
        }
    }

    // digunakan saat pertamakali viewmodel dipanggil
    init {
        if (prefs.getBoolean("isLoggedIn", false)) {
            _isLoggedIn.value = true
            login(
                prefs.getString("email", "").toString(),
                prefs.getString("password", "").toString()
            )
            checkToken()
        } else {
            _isLoggedIn.value = false
        }
    }
}