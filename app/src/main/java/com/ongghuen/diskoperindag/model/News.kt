package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val id: Int,
    val image: String? = null,
    val judul: String,
    val subjudul: String,
    val body: String,
)
