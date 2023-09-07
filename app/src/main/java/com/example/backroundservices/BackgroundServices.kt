package com.example.backroundservices

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class BackgroundServices: Service() {
    private val Channel: String = "channel"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            //Pengulangan pemangillan notif
            while (true) {
                buatNotif(this,"Pesan Masuk","Pesan Ini masuk")
                try {
                    //Delay 2 detik
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun buatNotif(context: Context, title: String, message: String){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Handling android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Channel,
                "My Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, Channel)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24) // Set the small icon for the notification
            .setContentTitle(title) // Set the title
            .setContentText(message) // Set the message or content text
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set the priority

        // Tampilkan notif
        notificationManager.notify(/* unique notification ID */ 1, builder.build())
    }
}