package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Pelatihan(
    val id: Int,
    val nama: String,
    val penyelenggara: String,
    val tanggal_pelaksanaan: String,
    val tempat: String,
)