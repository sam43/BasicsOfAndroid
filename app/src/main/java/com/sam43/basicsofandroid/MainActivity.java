package com.sam43.basicsofandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private static final String TAG = "+++MainActivity";

    private SomeBroadcastReceiver someBroadcastReceiver;
    private BroadcastReceiver broadcastReceiver;

    static WeakReference<Activity> weakReference;
    static SoftReference<Activity> softReference;
    static Activity strongReference;

    static Callable callable = new Callable() {
        @Override
        public Object call() throws Exception {
            return this;
        }
    };


    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: Runnable Running... thread: "+ Thread.currentThread() + " Priority: " + Thread.currentThread().getName() + " and " + Thread.currentThread().getPriority());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //broadcastReceiver = new SomeBroadcastReceiver();
        //initBroadCastReceiver();
        referenceChecking();
        mTextView = findViewById(R.id.mTextView);
        //runnable.run();
        //new LongRunningTask(this).execute();
        ContextCompat.startForegroundService(this, new Intent(this, MyService.class));
        //startService(new Intent(this, DownloadService.class));
    }

    private void referenceChecking() {
        weakReference = new WeakReference<Activity>(this);
        softReference = new SoftReference<Activity>(this);
        strongReference = this;
    }

    private void initBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Download Complete!");
                builder.show();
                Toast.makeText(MainActivity.this, "No problem with activity context", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*IntentFilter filter = new IntentFilter("com.sam43.basicsofandroid");
        this.registerReceiver(broadcastReceiver, filter);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(broadcastReceiver, filter);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //this.unregisterReceiver(broadcastReceiver);
    }

    class LongRunningTask extends AsyncTask<Void, Void, String> {

        //WeakReference<MainActivity> activity;
        SoftReference<MainActivity> activity;
        //MainActivity activity;

        LongRunningTask(MainActivity mainActivity) {
            //this.activity = new WeakReference<>(mainActivity);
            this.activity = new SoftReference<>(mainActivity);
            //this.activity = mainActivity;
        }

        @Override
        protected String doInBackground(Void... voids) {
            return someRemoteApiCallToFetchString();
        }

        @Override
        protected void onPostExecute(String s) {
            //mTextView.setText(s);
            if (this.activity.get() != null) { // Using weak reference
                mTextView.setText(s);
            }
        }
    }

    private String someRemoteApiCallToFetchString() {
        SystemClock.sleep(6000);
        return "fetched string from server";
    }
}
