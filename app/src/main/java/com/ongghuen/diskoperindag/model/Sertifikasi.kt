package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sertifikasi(
    val id: Int,
    val no_sertifikat: String,
    val nama: String,
    val tanggal_terbit: String,
    val kadaluarsa_penyelenggara: String,
    val keterangan: String,
)