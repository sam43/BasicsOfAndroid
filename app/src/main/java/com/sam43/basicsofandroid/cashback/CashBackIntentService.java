package com.sam43.basicsofandroid.cashback;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class CashBackIntentService extends IntentService{
    final static String CASHBACK_INFO = "cashback_info";
    private static final String TAG = "CashBackIntentService";
    private Context cxt;

    public CashBackIntentService(Context context) {
        super("Cashback IntentService");
        cxt = context;
    }

    public CashBackIntentService() {
        super("Cashback IntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String cb_category = intent.getStringExtra("cashback_cat");

        String cbinfo = getCashbackInfo(cb_category);
        sendCashbackInfoToClient(cbinfo);
    }
    private String getCashbackInfo(String cbcat){
        String cashback;
        if("electronics".equals(cbcat)){
            cashback = "Upto 20% cashback on electronics";
        }else if("fashion".equals(cbcat)){
            cashback = "Upto 60% cashbak on all fashion items";
        }else{
            //Toast.makeText(cxt, "response from the background", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "run in background: else case");
                        }
                    }, 10000
            );
            cashback = "All other categories except fashion and electronics, flat 30% cashback";
        }
        return cashback;
    }
    private void sendCashbackInfoToClient(String msg){
        Intent intent = new Intent();
        intent.setAction(CASHBACK_INFO);
        intent.putExtra("cashback",msg);
        sendBroadcast(intent);
    }
}
