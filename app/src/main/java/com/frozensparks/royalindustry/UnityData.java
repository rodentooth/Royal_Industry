package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;


import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;




/**
 * Created by Emanuel Graf on 18.05.2016.
 */
public class UnityData extends UnityPlayerActivity {
static Context uncon;
    public static void contextMethod(Context c){

        uncon = c;
    }
    public static void testMethod(Context c){
        Intent intent = new Intent(c, MainActivity.class);
        c.startActivity(intent);
    }

    public static void addcoin(Context c){
        SharedPreferences prefs = c.getSharedPreferences("POOL", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = c.getSharedPreferences("POOL", MODE_PRIVATE).edit();
        editor2.putInt("POOL", (prefs.getInt("POOL", 0) +5));
        editor2.apply();


    }
    public static void mpDiascheck(int data, Context c){


        if (data==34){
            SharedPreferences diasckeck  = c.getSharedPreferences("DIAMONDS", Context.MODE_PRIVATE);

           int diascheckgint = diasckeck.getInt("DIAMONDS", 0);
            if (diascheckgint<2){
                UnityPlayer.UnitySendMessage("menuObject", "diacheckok", "0");
            }
            if(diascheckgint>=2){
                UnityPlayer.UnitySendMessage("menuObject", "diacheckok", "1");

            }


        }

        if (data!=34) {
            Intent intent = new Intent(c, AgencyActivity.class);
            c.startActivity(intent);
        }
    }
}
