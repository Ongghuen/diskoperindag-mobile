package com.ongghuen.diskoperindag.service

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DiskoperindagMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("CEPTION TOKEN", "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        var builder = NotificationCompat.Builder(this, "Test")
            .setContentTitle(message.notification!!.title.toString())
            .setContentText(message.notification!!.body.toString())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

}