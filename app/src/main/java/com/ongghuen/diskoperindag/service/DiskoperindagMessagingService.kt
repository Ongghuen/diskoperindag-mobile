package com.ongghuen.diskoperindag.service

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.ktx.messaging

class DiskoperindagMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("CEPTION TOKEN", "Refreshed token: $token")
        Firebase.messaging.subscribeToTopic("diskoperindag")
            .addOnCompleteListener { task ->
                var msg = "Subscribe berhasil"
                if (!task.isSuccessful) {
                    msg = "Subscribe gagal"
                }
                Log.d("CEPTION FIREBASE MSG", "$msg")
            }
    }

}