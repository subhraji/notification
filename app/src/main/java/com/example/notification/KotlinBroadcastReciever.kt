package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class KotlinBroadcastReciever:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Success...", Toast.LENGTH_LONG).show()
        shownotification("Tobi", "Tobi the best", context)
    }

    fun shownotification(
        title: String?,
        message: String?,
        context: Context?
    ) {
        val builder =
            NotificationCompat.Builder(context!!, "MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setAutoCancel(true)
                .setContentText(message)
        val manager = NotificationManagerCompat.from(context)
        manager.notify(999, builder.build())
    }
}