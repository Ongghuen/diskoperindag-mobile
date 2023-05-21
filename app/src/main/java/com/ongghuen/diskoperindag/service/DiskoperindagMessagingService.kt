package com.ongghuen.diskoperindag.service

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ongghuen.diskoperindag.R

class DiskoperindagMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("CEPTION TOKEN", "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(
            "CEPTION FIREBASE",
            "Message: ${message.notification!!.title}: ${message.notification!!.body}"
        )

        val notificationBuilder = NotificationCompat.Builder(this, "diskoperindag")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("KONTOL")
            .setContentText("This is a sample notification.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(
                    "CEPTION FIREBASE",
                    "Message: ${message.notification!!.title}: ${message.notification!!.body}"
                )
                return
            }
            notify(1, notificationBuilder.build())
        }
    }

}