package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val user: UserDetail?, var token: String = "", var msg: String = "") {
    @JsonClass(generateAdapter = true)
    data class UserDetail(
        val id: Int = 0,
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val fcm_token: String?,
        val NIK: String = "",
        val alamat: String = "",
        val phone: String = ""
    )
}