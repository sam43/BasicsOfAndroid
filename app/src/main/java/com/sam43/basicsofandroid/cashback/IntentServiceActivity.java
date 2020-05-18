package com.sam43.basicsofandroid.cashback;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sam43.basicsofandroid.R;

public class IntentServiceActivity extends AppCompatActivity {

    private CashBackIntentService cashBackIntentService;
    private CashBackReceiver cashbackReciver;
    private TextView tv;
    private EditText et;
    private static final String TAG = "IntentServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        tv = findViewById(R.id.cb_results);
        et = findViewById(R.id.cashback_cat);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerCashbackReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cashbackReciver);
    }

    public void startCashbackService(View view){
        cashBackIntentService = new CashBackIntentService(IntentServiceActivity.this);
        Intent cbIntent =  new Intent(this, cashBackIntentService.getClass());
       //cbIntent.setClass(this, cashBackIntentService.getClass());
        cbIntent.putExtra("cashback_cat", et.getText().toString());
        if (!isMyServiceRunning(cashBackIntentService.getClass())) {
            startService(cbIntent);
        }
    }
    private void registerCashbackReceiver(){
        cashbackReciver = new CashBackReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CashBackIntentService.CASHBACK_INFO);

        registerReceiver(cashbackReciver, intentFilter);
    }

    private class CashBackReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String cbinfo = intent.getStringExtra("cashback");
            tv.setText(cbinfo);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run in background: " + cbinfo);
                    Toast.makeText(getApplicationContext(), "from background : " + cbinfo, Toast.LENGTH_LONG).show();
                }
            }, 10000);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("isMyServiceRunning?", true+"");
                return true;
            }
        }
        Log.i ("isMyServiceRunning?", false+"");
        return false;
    }

}
