package com.ongghuen.diskoperindag.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ongghuen.diskoperindag.model.Bantuan
import com.ongghuen.diskoperindag.model.BantuanDetail
import com.ongghuen.diskoperindag.model.Sertifikasi
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch

class FasilitasiViewModel(application: Application) : AndroidViewModel(application) {
    val prefs =
        getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)

    private var _bantuan = MutableLiveData<List<Bantuan>>()
    val bantuan: LiveData<List<Bantuan>> get() = _bantuan

    private var _bantuanDetail = MutableLiveData<BantuanDetail>()
    val bantuanDetail: MutableLiveData<BantuanDetail> get() = _bantuanDetail

    private var _sertifikasi = MutableLiveData<List<Sertifikasi>>()
    val sertifikasi: LiveData<List<Sertifikasi>> get() = _sertifikasi

//    private var _pelatihan = MutableLiveData<List<Pelatihan>>()
//    val pelatihan: LiveData<List<Pelatihan>> get() = _pelatihan

    fun getBantuan() {
        viewModelScope.launch {
            try {
                val bantuan = DiskoperindagApiService.UserApi.retrofitService.getBantuan(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${bantuan}")
                _bantuan.value = bantuan
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    fun getBantuanDetail(id: String) {
        viewModelScope.launch {
            try {
                val result = DiskoperindagApiService.UserApi.retrofitService.getBantuanDetail(
                    "Bearer " + prefs.getString("token", "")
                        .toString(), id
                )
                Log.d("CEPTION", "$result")
                _bantuanDetail.value = result
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    fun getSertifikasi() {
        viewModelScope.launch {
            try {
                val sertifikasi = DiskoperindagApiService.UserApi.retrofitService.getSertifikasi(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${sertifikasi}")
                _sertifikasi.value = sertifikasi
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    init {
        getBantuan()
        getSertifikasi()
    }

}