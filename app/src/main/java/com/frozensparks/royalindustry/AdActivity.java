package com.frozensparks.royalindustry;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        //Fullscreen
        if (Build.VERSION.SDK_INT < 16) { //ye olde method
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else { // Jellybean and up, new hotness
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.hide();
        }
        //ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5669148825390630/9176901702");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("E414DE4FE2ADD841904A5328B51C1195")
                .build();

        mInterstitialAd.loadAd(adRequest);


            mInterstitialAd.setAdListener(new AdListener() {

                @Override

                public void onAdLoaded() {
                    mInterstitialAd.show();

                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    AdRequest adRequest = new AdRequest.Builder()
                            //.addTestDevice("16201-16201")
                            .build();

                    mInterstitialAd.loadAd(adRequest);
                    Toast.makeText(AdActivity.this, "are you connected to the internet?", Toast.LENGTH_SHORT).show();
                    i++;
                    if (i==2){
                        finish();
                    }
                }

                @Override
                public void onAdClosed() {
                    //thanks


                    finish();
                }
            });


    }
}