package com.ongghuen

import android.os.Build
import androidx.annotation.RequiresApi
import com.ongghuen.diskoperindag.model.News
import com.ongghuen.diskoperindag.network.BASE
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

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

@RequiresApi(Build.VERSION_CODES.O)
fun convertTimestamp(timestamp: String): String {

    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val dateTime = LocalDateTime.parse(timestamp, inputFormat)
    return dateTime.format(outputFormat).toString()
}