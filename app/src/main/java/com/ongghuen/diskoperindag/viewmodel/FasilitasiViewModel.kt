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
import com.ongghuen.diskoperindag.model.Pelatihan
import com.ongghuen.diskoperindag.model.Sertifikasi
import com.ongghuen.diskoperindag.network.DiskoperindagApiService
import kotlinx.coroutines.launch

enum class FasilitasiLoading {
    LOADING, SUCCESS, ERROR
}

// viewmodel fasilitasi digunakan untuk memanggil semua logic yang diperlukan untuk
// mengambil data fasilitasi untuk user (pelaku ikm) yang berupa bantuan, sertifikasi, pelatihan
class FasilitasiViewModel(application: Application) : AndroidViewModel(application) {
    val prefs =
        getApplication<Application>().getSharedPreferences("diskoperindag", Context.MODE_PRIVATE)

    private var _bantuan = MutableLiveData<List<Bantuan>>()
    val bantuan: LiveData<List<Bantuan>> get() = _bantuan

    private var _bantuanDetail = MutableLiveData<BantuanDetail>()
    val bantuanDetail: MutableLiveData<BantuanDetail> get() = _bantuanDetail

    private var _sertifikasi = MutableLiveData<List<Sertifikasi>>()
    val sertifikasi: LiveData<List<Sertifikasi>> get() = _sertifikasi

    private var _pelatihan = MutableLiveData<List<Pelatihan>>()
    val pelatihan: LiveData<List<Pelatihan>> get() = _pelatihan

    private var _status = MutableLiveData(FasilitasiLoading.LOADING)
    val status: LiveData<FasilitasiLoading> get() = _status

    // ambil data bantuan
    fun getBantuan() {
        _status.value = FasilitasiLoading.LOADING

        viewModelScope.launch {
            try {
                val bantuan = DiskoperindagApiService.UserApi.retrofitService.getBantuan(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${bantuan}")
                _bantuan.value = bantuan
                _status.value = FasilitasiLoading.SUCCESS
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
                _status.value = FasilitasiLoading.ERROR
            }
        }
    }

    fun getBantuanDetail(id: String) {
        _status.value = FasilitasiLoading.LOADING
        viewModelScope.launch {
            try {
                val result = DiskoperindagApiService.UserApi.retrofitService.getBantuanDetail(
                    "Bearer " + prefs.getString("token", "")
                        .toString(), id
                )
                Log.d("CEPTION", "$result")
                _bantuanDetail.value = result
                _status.value = FasilitasiLoading.SUCCESS
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
                _status.value = FasilitasiLoading.ERROR
            }
        }
    }

    // ambil data sertifikasi
    fun getSertifikasi() {
        _status.value = FasilitasiLoading.LOADING
        viewModelScope.launch {
            try {
                val sertifikasi = DiskoperindagApiService.UserApi.retrofitService.getSertifikasi(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${sertifikasi}")
                _sertifikasi.value = sertifikasi
                _status.value = FasilitasiLoading.SUCCESS
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
                _status.value = FasilitasiLoading.ERROR
            }
        }
    }

    // ambil data pelatihan
    fun getPelatihan() {
        viewModelScope.launch {
            try {
                val pelatihan = DiskoperindagApiService.UserApi.retrofitService.getPelatihan(
                    "Bearer " + prefs.getString(
                        "token", ""
                    )
                )
                Log.d("CEPTION", "${sertifikasi}")
                _pelatihan.value = pelatihan
            } catch (e: Exception) {
                Log.d("ERRORCEPTION", e.toString())
            }
        }
    }

    // ambil semua data, wrap jadi satu
    fun getAll(){
        getBantuan()
        getSertifikasi()
        getPelatihan()
    }

    // saat viewmodel pertama kali dipanggil atau diinstansiasi (activity viewmodel)
    // init ini akan dipanggil
    init {
        getAll()
    }

}