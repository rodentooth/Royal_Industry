package com.frozensparks.royalindustry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 *Created by Emanuel Graf on 09.06.2016.
 */
public class InstallReferrerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");

        //Use the referrer
        SharedPreferences editorfab3 = new ObscuredSharedPreferences(context, context.getSharedPreferences("referrer", Context.MODE_PRIVATE));
        editorfab3.edit().putString("gid", referrer).apply();
        int done = editorfab3.getInt("done", 0);

        final SharedPreferences google = new ObscuredSharedPreferences(context,context.getSharedPreferences("google", Context.MODE_PRIVATE));
        String gid = google.getString("id", "0");
        if (!gid.equals("0")) {
            if (done == 0) {
                editorfab3.edit().putInt("done", 1).apply();
                bgworkerdias2 lol = new bgworkerdias2(context);
                lol.execute("referrer", gid, referrer, "diacollect");
            }
        }
    }
}