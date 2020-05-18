package com.sam43.basicsofandroid;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SomeIntentService extends IntentService {

    private static final String TAG = "+++SomeIntentService";
    final Handler handle = new Handler();

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(10000);
            //Toast.makeText(getApplicationContext(), "task is running", Toast.LENGTH_LONG).show();
            Log.d(TAG, "run: Runnable Running... thread: "+ Thread.currentThread() + " Priority: " + Thread.currentThread().getName() + " and " + Thread.currentThread().getPriority());
        }
    };

    public SomeIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //handle.postDelayed(runnable, 10000);
        runnable.run();
    }
}
