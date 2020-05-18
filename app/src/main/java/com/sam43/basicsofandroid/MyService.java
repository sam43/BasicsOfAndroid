package com.sam43.basicsofandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private static final String TAG = "+++MyService";

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: Runnable Running... thread: "+ Thread.currentThread() + " Priority: " + Thread.currentThread().getName() + " and " + Thread.currentThread().getPriority());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        runnable.run();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
