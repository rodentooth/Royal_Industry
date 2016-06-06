package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;

import android.os.Debug;
import android.util.Log;
import android.widget.Toast;


import com.frozensparks.royalindustry.planecrasher.UnityPlayerNativeActivity;
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

      //  com.frozensparks.royalindustry.planecrasher.UnityPlayerActivity.mUnityPlayer.quit();

    }

    public static void addcoin(Context c){
        MyPreferences prefs = MyPreferences.getInstance(c,"POOL");
        prefs.putInt("POOL", (prefs.getInt("POOL", 0) +5));
        prefs.apply();


    }
    public static void mpDiascheck(int data, Context c){

        String dat = "request";
        final MyPreferences google = MyPreferences.getInstance(c,"google");
        String gid = google.getString("id", "0");
        bgworkerdias lol = new bgworkerdias(c);
        lol.execute(dat, gid, "diacollect");


        if (data==34){
            MyPreferences diasckeck  = MyPreferences.getInstance(c,"DIAMONDS");

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
    public static void oppid(String opponentID, Context c){


        //OpponentID in server eintragen

        final MyPreferences google = MyPreferences.getInstance(c,"google");
        String type = "inputOpponentplayerID";
        String gid = google.getString("id", "0");
        bgworkerdias bgworker1 =new bgworkerdias(c);
        bgworker1.execute(type, gid,opponentID , "inputOpponentplayerID");

        Log.d(opponentID+"      "+gid, "dis is de oppid and gid"
        );

    }
    public static void winorloose(int winloose, String myID,String oppid, Context c){


        final MyPreferences google = MyPreferences.getInstance(c,"google");
    String type = "looserorwinner";
    String gid = google.getString("id", "0");
    bgworkerdias bgworker1 = new bgworkerdias(c);
    bgworker1.execute(type, gid,myID, oppid,Integer.toString(winloose), "afterwinner");



    }
}
