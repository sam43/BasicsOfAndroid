package com.sam43.basicsofandroid.notificationservices;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.sam43.basicsofandroid.App;
import com.sam43.basicsofandroid.R;

public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input;

        if (intent != null) {
            input = intent.getStringExtra("notify_msg");
        } else {
            input = "Intent is null, so no data found!";
        }

        Intent notificationIntent = new Intent(this, NotificationServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                0
        );

        startForeground(1, getNotification(pendingIntent, input));

        // Do heavy works in background thread
        //stopSelf();

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Notification getNotification(PendingIntent pendingIntent, String input) {
        return new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Sample Notification Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_bicycle)
                .build();
    }
}
