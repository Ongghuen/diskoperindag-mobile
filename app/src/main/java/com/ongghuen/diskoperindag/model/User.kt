package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val user: UserDetail?, val token: String){
    @JsonClass(generateAdapter = true)
    data class UserDetail(
        val id: Int,
        val name: String,
        val email: String
    )
}