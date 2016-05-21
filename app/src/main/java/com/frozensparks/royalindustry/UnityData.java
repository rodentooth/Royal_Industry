package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;
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
        //UnityPlayer.currentActivity.finish();
        //Intent intent = c.getPackageManager().getLaunchIntentForPackage("com.frozensparks.MainActivity");
        //c.startActivity(intent);
    }

    public void coins(String data) {

        Log.i("TAG", "The data was "+data);
        assert uncon!=null;
        Toast.makeText(uncon, "lololololololol", Toast.LENGTH_SHORT).show();
    }
}
