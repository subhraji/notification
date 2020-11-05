package com.example.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Success...",Toast.LENGTH_LONG).show();

        shownotification("Tobi","Tobi the best",context);
    }

    public void shownotification(String title, String message, Context context)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(999, builder.build());
    }
}
