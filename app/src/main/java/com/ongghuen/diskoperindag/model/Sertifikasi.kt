package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sertifikasi(
    val id: Int,
    val nama: String,
    val tanggal_terbit: String,
    val kadaluarsa_penyelenggara: String,
    val keterangan: String,
    val pivot: NoSertifikat
) {
    @JsonClass(generateAdapter = true)
    data class NoSertifikat(val no_sertifikat: String)
}
