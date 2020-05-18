package com.sam43.basicsofandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SomeBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "+++SomeBroadcastReceiver";

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: Runnable Running... thread: "+ Thread.currentThread() + " Priority: " + Thread.currentThread().getName() + " and " + Thread.currentThread().getPriority());
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        runnable.run();
    }
}
