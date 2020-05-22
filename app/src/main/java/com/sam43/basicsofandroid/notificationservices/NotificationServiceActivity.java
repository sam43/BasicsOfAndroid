package com.sam43.basicsofandroid.notificationservices;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.sam43.basicsofandroid.R;

import java.util.Objects;

public class NotificationServiceActivity extends AppCompatActivity {
    AppCompatEditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_service);
        etInput = findViewById(R.id.etInputMsg);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startService(View v) {
        String input = Objects.requireNonNull(etInput.getText()).toString();
        Intent serviceIntent = new Intent(this, NotificationIntentService.class);
        //serviceIntent.putExtra("notify_msg", etInput.getText().toString());
        serviceIntent.putExtra("inputExtra", etInput.getText().toString());
        //startService(serviceIntent);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        stopService(new Intent(this, NotificationIntentService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, NotificationIntentService.class));
    }
}
