package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

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

    }

    public static void addcoin(Context c){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setAction("com.frozensparks.BroadcastReceiver");
        //intent.putExtra("referrer", "34");
        c.sendBroadcast(intent);



    }
    public static void hatschonmal(int hetter,Context c){
        SharedPreferences prefs = new ObscuredSharedPreferences(c,c.getSharedPreferences("respawnsystem",MODE_PRIVATE));
        prefs.edit().putInt("hatschonmal",hetter).apply();

    }
    public static void anfragehetter(Context c){
        SharedPreferences prefs = new ObscuredSharedPreferences(c,c.getSharedPreferences("respawnsystem",MODE_PRIVATE));
        int anfrage=prefs.getInt("hatschonmal",0);
        String anstring = Integer.toString(anfrage);
        UnityPlayer.UnitySendMessage("PlayerBird", "anfragehatter", anstring);

    }

    public static void adanzeigen(Context c){

        Intent intent = new Intent(c, AdActivity.class);
        c.startActivity(intent);
    }


    public static void scoreanfrage(Context c){
        SharedPreferences prefs = new ObscuredSharedPreferences(c,c.getSharedPreferences("respawnsystem",MODE_PRIVATE));
        int anfrage=prefs.getInt("hatschonmal",0);
        if  (anfrage ==0){

            //score auf 0 setzen
             String scorestring = Integer.toString(0);
            UnityPlayer.UnitySendMessage("GameObject", "scoresetze", scorestring);

        }

        if  (anfrage ==1){
            int score=prefs.getInt("scoreeintrag",0);
            String scorestring = Integer.toString(score);
            //score auf score setzen
            UnityPlayer.UnitySendMessage("GameObject", "scoresetze", scorestring);

        }

    }
    public static void scoreeintrag(int score,Context c){
        SharedPreferences prefs = new ObscuredSharedPreferences(c,c.getSharedPreferences("respawnsystem",MODE_PRIVATE));
        prefs.edit().putInt("scoreeintrag",score).apply();

    }
    public static void mpDiascheck(int data, Context c){

        String dat = "request";
        final SharedPreferences google = new ObscuredSharedPreferences(c,c.getSharedPreferences("google",MODE_PRIVATE));
        String gid = google.getString("id", "0");
        bgworkerdias lol = new bgworkerdias(c);
        lol.execute(dat, gid, "diacollect");


        if (data==34){
            SharedPreferences diasckeck  = new ObscuredSharedPreferences(c,c.getSharedPreferences("DIAMONDS",MODE_PRIVATE));

           int diascheckgint = diasckeck.getInt("DIAMONDS", 0);
            if (diascheckgint<2){
                UnityPlayer.UnitySendMessage("menuObject", "diacheckok", "0");
            }
            if(diascheckgint>=2){
                UnityPlayer.UnitySendMessage("menuObject", "diacheckok", "1");

            }
            Log.d("android","diacheck  is called"+diascheckgint);


        }

        if (data!=34) {
            Intent intent = new Intent(c, AgencyActivity.class);
            c.startActivity(intent);
        }
    }
    public static void oppid(String opponentID, Context c){


        //OpponentID in server eintragen

        final SharedPreferences google = new ObscuredSharedPreferences(c,c.getSharedPreferences("google",MODE_PRIVATE));
        String type = "inputOpponentplayerID";
        String gid = google.getString("id", "0");
        bgworkerdias bgworker1 =new bgworkerdias(c);
        bgworker1.execute(type, gid,opponentID , "inputOpponentplayerID");

        Log.d(opponentID+"      "+gid, "dis is de oppid and gid");

    }
    public static void winorloose(int winloose, String myID,String oppid, Context c){


        final SharedPreferences google = new ObscuredSharedPreferences(c,c.getSharedPreferences("google",MODE_PRIVATE));
    String type = "looserorwinner";
    String gid = google.getString("id", "0");
    bgworkerdias bgworker1 = new bgworkerdias(c);
    bgworker1.execute(type, gid,myID, oppid,Integer.toString(winloose), "afterwinner");



    }
}
