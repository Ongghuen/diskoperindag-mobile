package com.ongghuen.diskoperindag.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BantuanDetail(
    val id: Int,
    val nama_bantuan: String,
    val jenis_usaha: String,
    val koordinator: String,
    val sumber_anggaran: String,
    val tahun_pemberian: String,
    val user_id: Int,
    val created_at: String,
    val updated_at: String,
    val item_bantuan: List<ItemBantuan>
) {
    @JsonClass(generateAdapter = true)
    data class ItemBantuan(
        val id: Int,
        val nama_item: String,
        val stok: Int,
        val deskripsi: String,
        val created_at: String,
        val updated_at: String
    )
}

