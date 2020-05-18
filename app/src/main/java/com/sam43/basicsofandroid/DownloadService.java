package com.sam43.basicsofandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloadService extends Service {

    static String pointName = "Point B";

    private static final String TAG = "---DownloadService";

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("Service: ", pointName + " in " + Thread.currentThread().getName());
            Log.d(TAG, "Service: Thread: "+ Thread.currentThread() + " Priority: " + Thread.currentThread().getName() + " and " + Thread.currentThread().getPriority());
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service: ", "Point A in "+Thread.currentThread().getName());
        pointName = "Point C";
        new Thread(runnable).start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* TODO: Solution:
      Service point A to print korbei

      Service point C
      Print korar kotha er pore

      Service to main thread e chole. So. Service point a print hobe main thread e.
      Ar
      Service point c print hobe vitorer runnable thread e

      */

}
