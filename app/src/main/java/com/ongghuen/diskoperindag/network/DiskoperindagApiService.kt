package com.ongghuen.diskoperindag.network

import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.model.User
import com.ongghuen.diskoperindag.model.UserRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val BASE = "http://192.168.100.28:8000"
const val BASE_URL = "${BASE}/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

class DiskoperindagApiService {

    interface UserApiService {
        @POST("login")
        suspend fun login(@Body user: UserRequest): User

        @GET("logout")
        suspend fun logout(@Header("Authorization") token: String)

        @GET("news")
        suspend fun getNews(): List<News>

        @GET("news/saved")
        suspend fun getFavorite(@Header("Authorization") token: String): List<News>

        @POST("news/add")
        suspend fun addFavorite(@Header("Authorization") token: String, id: String)
    }

    object UserApi {
        val retrofitService: UserApiService by lazy {
            retrofit.create(UserApiService::class.java)
        }
    }

}