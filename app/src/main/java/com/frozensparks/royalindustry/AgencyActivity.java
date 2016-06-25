package com.frozensparks.royalindustry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.billingUtils.util.IabHelper;
import com.billingUtils.util.IabResult;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.Tracker;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AgencyActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: false
    Boolean godmode = false;


    Boolean runner =true;

    Context context = this;
    Button buildboost;
    Dialog gebaeudeboost;
    Button closegebaeudeboost;
    Button freedias;
    Button freegold;
    TextView platzhalterboost;
    RadioGroup radioGroup;
    RadioButton fab1, fab2, fab3, fab4, bank;
    int selectedId = 0;
    int timerad;
    int restzeit;
    boolean ad;
    String dias;
    Button closefreedias;
    TextView link20dias;
    Button sharefreedias;
    Button ratebtn;
    ImageView das;
    Button likeView;


    int boost = 1800;


    Button doublegold;
    Button diastogold;
    Button closeAgency;
    TextView goldagency;
    TextView diapoolagency;

    Dialog dialogconvertdias;
    SeekBar seekbar;
    TextView howmanygoldtodias;
    TextView diasCost;
    Button confirmcashout;
    String derefeerallink;
    Handler h = new Handler();

    InterstitialAd mInterstitialAd;


    static final String TAG = "BillingService";
    // The helper object
    IabHelper mHelper;

private Tracker mTracker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5669148825390630~9003934904");
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();


        mTracker.enableAutoActivityTracking(true);

        setContentView(R.layout.activity_agency);

        if(godmode){
    boost=10000000;
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
            assert actionBar != null;
            actionBar.hide();
        }



        closeAgency = (Button) findViewById(R.id.closeAgency);
        assert closeAgency != null;
        closeAgency.setOnClickListener(this);

        buildboost = (Button) findViewById(R.id.buildboost);
        assert buildboost != null;
        buildboost.setOnClickListener(this);


        diastogold = (Button) findViewById(R.id.diastogold);
        diastogold.setOnClickListener(this);

        freedias = (Button) findViewById(R.id.freedias);
        freedias.setOnClickListener(this);
        freegold = (Button) findViewById(R.id.freegold);
        freegold.setOnClickListener(this);


        doublegold = (Button) findViewById(R.id.doublegold);
        assert doublegold != null;
        doublegold.setOnClickListener(this);

        goldagency = (TextView) findViewById(R.id.goldagency);
        diapoolagency = (TextView) findViewById(R.id.diapoolagency);


        //ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5669148825390630/8864334107");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("E414DE4FE2ADD841904A5328B51C1195")
                .build();

        mInterstitialAd.loadAd(adRequest);


        h.postDelayed(new Runnable() {

            public void run() {
                if(runner) {

                    SharedPreferences prefs1 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
                    String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
                    goldagency.setText(": " + Pooltext);

                    SharedPreferences prefs3 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
                    //Diapool text aktualisieren
                    String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                    diapoolagency.setText(": " + diapooltext);

                    SharedPreferences deziit = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
                    int endtime = (int) (System.currentTimeMillis() / 1000);
                    int startzeit = deziit.getInt("startzeit", 31);
                    int datzeit = endtime - startzeit;
                    restzeit = 30 - datzeit;

                    if (datzeit >= 30) {
                        SharedPreferences deziit1 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
                        deziit1.edit().putInt("startzeit", endtime);
                        deziit1.edit().putBoolean("ad", true).apply();


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

                }
                h.postDelayed(this, 2000);

            }

        }, 1);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.diastogold){

            SharedPreferences prefs3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
            //Diapool text aktualisieren
            int dias = (prefs3.getInt("DIAMONDS", 0));


            View converter = View.inflate(this, R.layout.cashoutconverter, null);
            dialogconvertdias = new Dialog(AgencyActivity.this);
            dialogconvertdias.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogconvertdias.setContentView(converter);


            seekbar = (SeekBar) dialogconvertdias.findViewById(R.id.seekBarcash);
            howmanygoldtodias = (TextView) dialogconvertdias.findViewById(R.id.howmanydiastocash);
            diasCost = (TextView) dialogconvertdias.findViewById(R.id.cashoutcosts);

            confirmcashout = (Button) dialogconvertdias.findViewById(R.id.confirmcashout);
            confirmcashout.setOnClickListener(this);

            if ( dias < 10) {
                seekbar.setMax(dias);
            }
            if (dias >= 10){
                seekbar.setMax(10);

            }

            int progressvalue = 0;
            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue*250 +getString(R.string.Gold) );
            diasCost.setText(getString(R.string.Costs) + " " + progressvalue  + getString(R.string.Diamonds));


            seekbar.setOnSeekBarChangeListener(

                    new SeekBar.OnSeekBarChangeListener() {


                        int progressvalue;

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            progressvalue = progress;
                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue*250 +getString(R.string.Gold) );
                            diasCost.setText(getString(R.string.Costs) + " " + progressvalue  + getString(R.string.Diamonds));
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue*250 +getString(R.string.Gold) );
                            diasCost.setText(getString(R.string.Costs) + " " + progressvalue  + getString(R.string.Diamonds));
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue*250 +getString(R.string.Gold) );
                            diasCost.setText(getString(R.string.Costs) + " " + progressvalue  + getString(R.string.Diamonds));

                            //den progressvalue (wieviele dias) speichern
                            SharedPreferences editor1 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("thatmanydiastogold", MODE_PRIVATE));
                            editor1.edit().putInt("thatmanydiastogold", progressvalue).apply();


                        }
                    }

            );
            dialogconvertdias.show();





        }
        if (id == R.id.confirmcashout) {

            SharedPreferences datad = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
            ad =  datad.getBoolean("ad", true);

            if(!ad) {

                String timeleft = String.format(Locale.US, "%02d:%02d",
                        TimeUnit.SECONDS.toMinutes(restzeit) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restzeit)),
                        TimeUnit.SECONDS.toSeconds(restzeit) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restzeit)));


                Toast.makeText(AgencyActivity.this, "please wait " + timeleft + " before doing this", Toast.LENGTH_SHORT).show();
            }


            if(ad) {
                SharedPreferences deziit1 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
                deziit1.edit().putBoolean("ad", false);



                //holen von wieviele diamanten erstellen
                SharedPreferences prefs1 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("thatmanydiastogold", MODE_PRIVATE));

                int diaweg = (prefs1.getInt("thatmanydiastogold", 0));


                //Holen von Pool
                SharedPreferences prefs2 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));


                //checken obs genug diamanten für den convert hat
                if (diaweg > prefs2.getInt("DIAMONDS", 0)) {

                    Toast.makeText(AgencyActivity.this, R.string.not_enough_diamonds, Toast.LENGTH_SHORT).show();


                }

                if (diaweg <= prefs2.getInt("DIAMONDS", 0)) {


                    //es hat dias linken

                    dias = Integer.toString(diaweg);


                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();


                        mInterstitialAd.setAdListener(new AdListener() {

                            @Override

                            public void onAdLoaded() {

                            }

                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                AdRequest adRequest = new AdRequest.Builder()
                                        //.addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);
                            }

                            @Override
                            public void onAdClosed() {
                                //thanks
                                Toast.makeText(AgencyActivity.this, R.string.thx, Toast.LENGTH_SHORT).show();
                                dialogconvertdias.dismiss();
                                AdRequest adRequest = new AdRequest.Builder()
                                        //.addTestDevice("16201-16201")
                                        .build();

                                //geld aktualisierung
                                final SharedPreferences google = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                                String type = "diasweg";
                                String gid = google.getString("id", "0");
                                bgworkerdias bgworker1 = new bgworkerdias(context);
                                bgworker1.execute(type, gid, dias, "diastogold");


                                mInterstitialAd.loadAd(adRequest);
                            }
                        });

                    } else {
                        Toast.makeText(AgencyActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        AdRequest adRequest = new AdRequest.Builder()
                                //.addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }


                    //dialog beenden
                    dialogconvertdias.dismiss();

                }
            }

        }


        if (id == R.id.doublegold) {
            SharedPreferences doubler = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("doublecoll", MODE_PRIVATE));
            Boolean doblegold = doubler.getBoolean("doublecoll", false);

            if (doblegold) {
                Toast.makeText(AgencyActivity.this, R.string.doublerbought, Toast.LENGTH_SHORT).show();


            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        this);

                // set title
                alertDialogBuilder.setTitle(R.string.doublegold);

                // set dialog message


                alertDialogBuilder.setMessage(R.string.doublegoldtext);


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                //geld aktualisierung
                                final SharedPreferences google = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                                String type = "diasweg";
                                String dias = "2";
                                String gid = google.getString("id", "0");
                                bgworkerdias bgworker1 = new bgworkerdias(context);
                                bgworker1.execute(type, gid, dias, "diasweg");


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
            SharedPreferences datafab1 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("datafab1", MODE_PRIVATE));
            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {
                fab1.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }


            //boostfab2
            SharedPreferences datafab2 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("datafab2", MODE_PRIVATE));
            Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
            if (cdfab2) {
                fab2.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab3
            SharedPreferences datafab3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("datafab3", MODE_PRIVATE));
            Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
            if (cdfab3) {
                fab3.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab4
            SharedPreferences datafab4 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("datafab4", MODE_PRIVATE));
            Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
            if (cdfab4) {
                fab4.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostbank
            SharedPreferences databank = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("dataBank", MODE_PRIVATE));
            Boolean databanklvl = (databank.getBoolean("isLeveling", false));
            if (databanklvl) {
                bank.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            gebaeudeboost.show();


        }


        if (id == R.id.payboostwithad) {

            selectedId = radioGroup.getCheckedRadioButtonId();

            SharedPreferences datad = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
            ad =  datad.getBoolean("ad", true);

            if(!ad) {

                String timeleft = String.format(Locale.US, "%02d:%02d",
                        TimeUnit.SECONDS.toMinutes(restzeit) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restzeit)),
                        TimeUnit.SECONDS.toSeconds(restzeit) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restzeit)));


                Toast.makeText(AgencyActivity.this, "please wait " + timeleft + " before doing this", Toast.LENGTH_SHORT).show();
            }


            if(ad) {
                SharedPreferences deziit1 =new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("blockzeit", MODE_PRIVATE));
                deziit1.edit().putBoolean("ad", false);


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
                                    //.addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }

                        @Override
                        public void onAdClosed() {
                            //thanks
                            Toast.makeText(AgencyActivity.this, R.string.thx, Toast.LENGTH_SHORT).show();
                            timerad = timerad + 1;

                            if (selectedId == fab1.getId()) {
                                SharedPreferences Fab1upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE));
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE));
                                editor3.edit().putInt("startTime", startTimefab1 - boost).apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();
                            }
                            if (selectedId == fab2.getId()) {
                                SharedPreferences Fab2upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE));
                                int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE));
                                editor3.edit().putInt("startTime", startTimefab2 - boost).apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();


                            }

                            if (selectedId == fab3.getId()) {
                                SharedPreferences Fab3upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE));
                                int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE));
                                editor3.edit().putInt("startTime", startTimefab3 - boost).apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == fab4.getId()) {

                                SharedPreferences Fab4upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE));
                                int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE));
                                editor3.edit().putInt("startTime", startTimefab4 - boost).apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == bank.getId()) {

                                SharedPreferences Fab1upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE));
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE));
                                editor3.edit().putInt("startTime", startTimefab1 - boost).apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();


                            }


                            AdRequest adRequest = new AdRequest.Builder()
                                    //.addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }
                    });

                } else {
                    Toast.makeText(AgencyActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                    AdRequest adRequest = new AdRequest.Builder()
                            //.addTestDevice("16201-16201")
                            .build();

                    mInterstitialAd.loadAd(adRequest);

                }
            }


        }

        if (id == R.id.payboostwithgold) {
            selectedId = radioGroup.getCheckedRadioButtonId();

            SharedPreferences prefs = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
            SharedPreferences editor2 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));


            if ((prefs.getInt("POOL", 0)) < 500) {
                Toast.makeText(AgencyActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

            }
            if ((prefs.getInt("POOL", 0)) >= 500) {



                if (selectedId == fab1.getId()) {
                    SharedPreferences Fab1upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE));
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE));
                    editor3.edit().putInt("startTime", startTimefab1 - boost).apply();

                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.edit().putInt("POOL", (prefs.getInt("POOL", 0) - 500)).apply();

                }
                if (selectedId == fab2.getId()) {
                    SharedPreferences Fab2upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE));
                    int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE));
                    editor3.edit().putInt("startTime", startTimefab2 - boost).apply();

                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.edit().putInt("POOL", (prefs.getInt("POOL", 0) - 500)).apply();


                }

                if (selectedId == fab3.getId()) {
                    SharedPreferences Fab3upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE));
                    int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE));
                    editor3.edit().putInt("startTime", startTimefab3 - boost).apply();

                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.edit().putInt("POOL", (prefs.getInt("POOL", 0) - 500)).apply();

                }

                if (selectedId == fab4.getId()) {

                    SharedPreferences Fab4upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE));
                    int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE));
                    editor3.edit().putInt("startTime", startTimefab4 - boost).apply();

                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.edit().putInt("POOL", (prefs.getInt("POOL", 0) - 500)).apply();

                }

                if (selectedId == bank.getId()) {

                    SharedPreferences Fab1upgradeContdown = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE));
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this,AgencyActivity.this.getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE));
                    editor3.edit().putInt("startTime", startTimefab1 - boost).apply();

                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.edit().putInt("POOL", (prefs.getInt("POOL", 0) - 500)).apply();

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
        if (id == R.id.freedias){

            View gebaeudeboostdia = View.inflate(this, R.layout.freedias, null);
            gebaeudeboost = new Dialog(v.getContext());
            gebaeudeboost.requestWindowFeature(Window.FEATURE_NO_TITLE);
            gebaeudeboost.setContentView(gebaeudeboostdia);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(gebaeudeboost.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;

            closefreedias = (Button)gebaeudeboost.findViewById(R.id.closefreedias);
            closefreedias.setOnClickListener(this);

            sharefreedias = (Button)gebaeudeboost.findViewById(R.id.sharefreedias);
            sharefreedias.setOnClickListener(this);


            final SharedPreferences google = new ObscuredSharedPreferences(context,context.getSharedPreferences("google", Context.MODE_PRIVATE));
            String gid = google.getString("id", "0");
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

            derefeerallink ="https://play.google.com/store/apps/details?id="+ appPackageName+"&referrer="+gid;
            link20dias = (TextView)gebaeudeboost.findViewById(R.id.link20dias);
            link20dias.setText(derefeerallink);
            ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setText(link20dias.getText());


            gebaeudeboost.show();

        }
        if (id == R.id.freegold){

            View gebaeudeboostdia = View.inflate(this, R.layout.freegold, null);
            gebaeudeboost = new Dialog(v.getContext());
            gebaeudeboost.requestWindowFeature(Window.FEATURE_NO_TITLE);
            gebaeudeboost.setContentView(gebaeudeboostdia);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(gebaeudeboost.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;

            closefreedias = (Button)gebaeudeboost.findViewById(R.id.closefreedias);
            closefreedias.setOnClickListener(this);
            ratebtn = (Button)gebaeudeboost.findViewById(R.id.ratebtn);
            ratebtn.setOnClickListener(this);

             likeView = (Button) gebaeudeboost.findViewById(R.id.like_view);
            likeView.setOnClickListener(this);


            gebaeudeboost.show();

        }
        if (id == R.id.like_view){
            SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("freegold", MODE_PRIVATE));
            int likefs = editor3.getInt("rate",0);
            if (likefs!=1) {
                try {
                    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/304593896391711")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/FrozenSparks.ch")));
                }
                SharedPreferences editor2 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
                editor2.edit().putInt("POOL", (editor2.getInt("POOL", 0) + 500)).apply();

                editor3.edit().putInt("likefs", 1).apply();

            }
            if (likefs==1) {
                Toast.makeText(AgencyActivity.this, "thanks!", Toast.LENGTH_SHORT).show();
            }

        }

        if (id == R.id.ratebtn){
            SharedPreferences editor3 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("freegold", MODE_PRIVATE));
            int rate = editor3.getInt("rate",0);
            if (rate!=1) {


                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                SharedPreferences editor2 = new ObscuredSharedPreferences(AgencyActivity.this, AgencyActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
                editor2.edit().putInt("POOL", (editor2.getInt("POOL", 0) + 500)).apply();

                editor3.edit().putInt("rate", 1).apply();
            }
            if (rate==1) {
                Toast.makeText(AgencyActivity.this, "thanks!", Toast.LENGTH_SHORT).show();
            }


            }
        if (id == R.id.sharefreedias){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "hey!\nI found a cool game! get 20 free diamonds with my link: \n "+derefeerallink);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, ""));
        }

        if (id == R.id.closefreedias){
            if (gebaeudeboost.isShowing()){
                gebaeudeboost.dismiss();
            }

        }
    }


     // We're being destroyed. It's important to dispose of the helper here!
    @Override
    public void onDestroy() {
        super.onDestroy();

        // very important:
      /*  if (mBroadcastReceiver != null) {
         unregisterReceiver(mBroadcastReceiver);
       }*/
            runner=false;
        // very important:
        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mHelper = null;
        }
    }

}