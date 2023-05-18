package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bantuan(
    val id: Int,
    val nama_bantuan: String,
    val jenis_usaha: String,
    val koordinator: String,
    val sumber_anggaran: String,
    val tahun_pemberian: String,
)