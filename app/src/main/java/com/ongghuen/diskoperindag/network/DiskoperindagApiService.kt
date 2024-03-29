package com.ongghuen.diskoperindag.network

import com.ongghuen.diskoperindag.model.Bantuan
import com.ongghuen.diskoperindag.model.BantuanDetail
import com.ongghuen.diskoperindag.model.ChangePasswordRequest
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.model.Pelatihan
import com.ongghuen.diskoperindag.model.Sertifikasi
import com.ongghuen.diskoperindag.model.User
import com.ongghuen.diskoperindag.model.UserRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// variabel ini digunakan untuk menumpu kepada url api yang dituju
const val BASE = "http://192.168.30.112:8000"
const val BASE_URL = "${BASE}/api/"

// variabel ini digunakan untuk menginstansiasi moshi
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

class DiskoperindagApiService {

    // interface ini digunakan untuk mengakses entry point api
    interface UserApiService {
        @POST("login")
        suspend fun login(@Body user: UserRequest): User

        @GET("logout")
        suspend fun logout(@Header("Authorization") token: String)

        @POST("changePassword")
        suspend fun changePassword(
            @Header("Authorization") token: String,
            @Body request: ChangePasswordRequest
        )

        @POST("assignToken")
        suspend fun assignToken(
            @Header("Authorization") token: String,
            @Query("fcm_token") fcmToken: String
        ): String

        @GET("checkToken")
        suspend fun checkToken(@Header("Authorization") token: String): String

        @GET("news")
        suspend fun getNews(): List<News>

        @GET("news/{id}")
        suspend fun getNews(@Path("id") id: String): News

        @GET("news/saved")
        suspend fun getNewsFavorite(@Header("Authorization") token: String): List<News>

        @POST("news/add")
        suspend fun addNewsFavorite(
            @Header("Authorization") token: String,
            @Query("berita_id") id: String
        )

        @DELETE("news/delete/{id}")
        suspend fun deleteNewsFavorite(
            @Header("Authorization") token: String,
            @Path("id") id: String
        )

        @GET("fasilitasi/bantuan")
        suspend fun getBantuan(@Header("Authorization") token: String): List<Bantuan>

        @GET("fasilitasi/bantuan/{id}")
        suspend fun getBantuanDetail(
            @Header("Authorization") token: String,
            @Path("id") id: String
        ): BantuanDetail

        @GET("fasilitasi/sertifikat")
        suspend fun getSertifikasi(@Header("Authorization") token: String): List<Sertifikasi>

        @GET("fasilitasi/pelatihan")
        suspend fun getPelatihan(@Header("Authorization") token: String): List<Pelatihan>
    }

    // object ini digunakan untuk mengakses atau memanggil fungsi yang berada
    // di interface
    object UserApi {
        val retrofitService: UserApiService by lazy {
            retrofit.create(UserApiService::class.java)
        }
    }

}