package com.frozensparks.royalindustry;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

import java.util.concurrent.ExecutionException;

/**
 * Created by Emanuel Graf on 05.07.2016.
 */
public class bg_sound  extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    Handler h = new Handler();
    Context context = this;
    boolean foregroud;
    boolean wasit=false;

    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bg_sound);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

        h.postDelayed(new Runnable() {

            public void run() {

                try {
                    foregroud = new ForegroundCheckTask().execute(bg_sound.this).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (!foregroud){

                    if(!wasit) {
                        wasit=true;
                        player.release();
                    }
                }
                if (foregroud){
                    if(wasit) {
                        wasit=false;
                        player = MediaPlayer.create(bg_sound.this, R.raw.bg_sound);
                        player.setLooping(true); // Set looping
                        player.setVolume(100,100);
                        player.start();

                    }
                }

                h.postDelayed(this, 1000);

            }

        }, 1);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;

    }

    public void onStart(Intent intent, int startId) {
        // TO DO

    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
        player.stop();
        player.release();
    }
    public void onPause() {
        player.pause();

    }

    public void onResume(){
        player.start();

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
