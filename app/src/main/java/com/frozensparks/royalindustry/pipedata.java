package com.frozensparks.royalindustry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 *Created by Emanuel Graf on 09.06.2016.
 */
public class pipedata extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int referrer = intent.getIntExtra("referrer",0);
        SharedPreferences prefs = new ObscuredSharedPreferences(context, context.getSharedPreferences("POOL", Context.MODE_PRIVATE));
        prefs.edit().putInt("POOL", (prefs.getInt("POOL", 0) + 5)).apply();
        Log.d("android","brodcast is called");

    }
}