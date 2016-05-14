package com.frozensparks.royalindustry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AgencyActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    Button buildboost;
    Dialog gebaeudeboost;
    Button closegebaeudeboost;
    TextView platzhalterboost;
    RadioGroup radioGroup;
    RadioButton fab1, fab2, fab3, fab4, bank;
    int selectedId = 0;
    int timerad;
    int restzeit;
    boolean ad;


    //TODO boost auf 1800 ändern
    int boost = 120000;


    Button doublegold;
    Button get5000gold;
    Button closeAgency;
    TextView goldagency;

    Handler h = new Handler();

    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);

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
            actionBar.hide();
        }

        closeAgency = (Button) findViewById(R.id.closeAgency);
        assert closeAgency != null;
        closeAgency.setOnClickListener(this);

        buildboost = (Button) findViewById(R.id.buildboost);
        assert buildboost != null;
        buildboost.setOnClickListener(this);

        doublegold = (Button) findViewById(R.id.doublegold);
        assert doublegold != null;
        doublegold.setOnClickListener(this);

        goldagency = (TextView) findViewById(R.id.goldagency);


        //ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("16201-16201")
                .build();

        mInterstitialAd.loadAd(adRequest);


        h.postDelayed(new Runnable(){
            public void run(){

                SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
                String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
                goldagency.setText(": " + Pooltext );

                SharedPreferences deziit =getSharedPreferences("blockzeit", MODE_PRIVATE);
                int endtime = (int) (System.currentTimeMillis()/1000) ;
                int startzeit = deziit.getInt("startzeit", 31);
                int datzeit = endtime - startzeit;
                 restzeit =  30 - datzeit;

                if (datzeit >= 30){
                    SharedPreferences.Editor deziit1 =getSharedPreferences("blockzeit", MODE_PRIVATE).edit();
                    deziit1.putInt("startzeit", endtime);
                    deziit1.putBoolean("ad", true);
                    deziit1.apply();


                }


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
                    actionBar.hide();
                }


                h.postDelayed(this, 500);
            }
        }, 500);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.doublegold) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle(R.string.doublegold);

            // set dialog message


            alertDialogBuilder.setMessage(R.string.doublegoldtext);


            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("buy", new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialog, int id) {
                    //geld aktualisierung
                    final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);
                    String type = "diasweg";
                    String dias = "2";
                    String gid = google.getString("id", "0");
                    bgworkerdias bgworker1 =new bgworkerdias(context);
                    bgworker1.execute(type, gid,dias , "diasweg");





                }
                    }

            )


                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();
                                }
                            }

                    );

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();



        }






        if (id == R.id.buildboost) {


            View gebaeudeboostdia = View.inflate(this, R.layout.gebaeudeboost, null);
            gebaeudeboost = new Dialog(v.getContext());
            gebaeudeboost.requestWindowFeature(Window.FEATURE_NO_TITLE);
            gebaeudeboost.setContentView(gebaeudeboostdia);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(gebaeudeboost.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;

            closegebaeudeboost = (Button)gebaeudeboost.findViewById(R.id.closegebaeudeboost);
            closegebaeudeboost.setOnClickListener(this);

            platzhalterboost= (TextView) gebaeudeboostdia.findViewById(R.id.platzhalterboost);

             fab1 = (RadioButton) gebaeudeboost.findViewById(R.id.boostfab1);
             fab2 = (RadioButton) gebaeudeboost.findViewById(R.id.boostfab2);
             fab3 = (RadioButton) gebaeudeboost.findViewById(R.id.boostfab3);
             fab4 = (RadioButton) gebaeudeboost.findViewById(R.id.boostfab4);
             bank = (RadioButton) gebaeudeboost.findViewById(R.id.boostbank);

            Button payboostwithgold = (Button) gebaeudeboost.findViewById(R.id.payboostwithgold);
            payboostwithgold.setOnClickListener(this);
            Button payboostwithad = (Button) gebaeudeboost.findViewById(R.id.payboostwithad);
            payboostwithad.setOnClickListener(this);



            radioGroup = (RadioGroup) gebaeudeboost.findViewById(R.id.myradiogroup);
             selectedId = radioGroup.getCheckedRadioButtonId();





            //fab1boost
            SharedPreferences datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);
            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {
                fab1.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }


            //boostfab2
            SharedPreferences datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
            Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
            if (cdfab2) {
                fab2.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab3
            SharedPreferences datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
            Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
            if (cdfab3) {
                fab3.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab4
            SharedPreferences datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
            Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
            if (cdfab4) {
                fab4.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostbank
            SharedPreferences databank = getSharedPreferences("dataBank", MODE_PRIVATE);
            Boolean databanklvl = (databank.getBoolean("isLeveling", false));
            if (databanklvl) {
                bank.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            gebaeudeboost.show();


        }


        if (id == R.id.payboostwithad) {

            selectedId = radioGroup.getCheckedRadioButtonId();

            SharedPreferences datad = getSharedPreferences("blockzeit", MODE_PRIVATE);
            ad =  datad.getBoolean("ad", true);

            if(!ad) {

                String timeleft = String.format(Locale.US, "%02d:%02d",
                        TimeUnit.SECONDS.toMinutes(restzeit) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restzeit)),
                        TimeUnit.SECONDS.toSeconds(restzeit) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restzeit)));


                Toast.makeText(AgencyActivity.this, "please wait " + timeleft + " before doing this", Toast.LENGTH_SHORT).show();
            }


            if(ad) {
                SharedPreferences.Editor deziit1 =getSharedPreferences("blockzeit", MODE_PRIVATE).edit();
                deziit1.putBoolean("ad", false);
                deziit1.apply();

                //werbung starten
                // Nur wenn die ad geladen hat, das converten erlauben
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();


                    mInterstitialAd.setAdListener(new AdListener() {

                        @Override

                        public void onAdLoaded() {

                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            Toast.makeText(AgencyActivity.this, "we're so sorry. please try again", Toast.LENGTH_SHORT).show();
                            gebaeudeboost.dismiss();
                            AdRequest adRequest = new AdRequest.Builder()
                                    .addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }

                        @Override
                        public void onAdClosed() {
                            //thanks
                            Toast.makeText(AgencyActivity.this, "thank you", Toast.LENGTH_SHORT).show();
                            timerad = timerad + 1;

                            if (selectedId == fab1.getId()) {
                                SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE);
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                editor3.putInt("startTime", startTimefab1 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();
                            }
                            if (selectedId == fab2.getId()) {
                                SharedPreferences Fab2upgradeContdown = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE);
                                int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                editor3.putInt("startTime", startTimefab2 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();


                            }

                            if (selectedId == fab3.getId()) {
                                SharedPreferences Fab3upgradeContdown = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE);
                                int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                editor3.putInt("startTime", startTimefab3 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == fab4.getId()) {

                                SharedPreferences Fab4upgradeContdown = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE);
                                int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                editor3.putInt("startTime", startTimefab4 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == bank.getId()) {

                                SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE);
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE).edit();
                                editor3.putInt("startTime", startTimefab1 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();


                            }


                            AdRequest adRequest = new AdRequest.Builder()
                                    .addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }
                    });

                } else {
                    Toast.makeText(AgencyActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice("16201-16201")
                            .build();

                    mInterstitialAd.loadAd(adRequest);

                }
            }


    }

        if (id == R.id.payboostwithgold) {
            selectedId = radioGroup.getCheckedRadioButtonId();

            SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

            if ((prefs.getInt("POOL", 0)) < 500) {
                Toast.makeText(AgencyActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

            }
            if ((prefs.getInt("POOL", 0)) >= 500) {



                if (selectedId == fab1.getId()) {
                    SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE);
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                    editor3.putInt("startTime", startTimefab1 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }
                if (selectedId == fab2.getId()) {
                    SharedPreferences Fab2upgradeContdown = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE);
                    int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                    editor3.putInt("startTime", startTimefab2 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();

                }

                if (selectedId == fab3.getId()) {
                    SharedPreferences Fab3upgradeContdown = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE);
                    int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                    editor3.putInt("startTime", startTimefab3 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }

                if (selectedId == fab4.getId()) {

                    SharedPreferences Fab4upgradeContdown = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE);
                    int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                    editor3.putInt("startTime", startTimefab4 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }

                if (selectedId == bank.getId()) {

                    SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE);
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE).edit();
                    editor3.putInt("startTime", startTimefab1 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }


            }



            }
        if (id == R.id.closegebaeudeboost) {

            gebaeudeboost.dismiss();
        }

        if (id == R.id.closeAgency) {
            Intent start = new Intent(AgencyActivity.this, MainActivity.class);
            AgencyActivity.this.startActivity(start);
            finish();

        }
    }
}
