package com.ongghuen

import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.network.BASE

fun checkUrlImg(item: News): String {
    if (item.image.isNullOrEmpty()) {
        return ""
    }
    if (item.image!!.contains("http", ignoreCase = true)) {
        return item.image
    } else {
        return "${BASE}/images/berita/" + item.image
    }
}