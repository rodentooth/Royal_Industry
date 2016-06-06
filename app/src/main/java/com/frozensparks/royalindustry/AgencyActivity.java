package com.frozensparks.royalindustry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.frozensparks.royalindustry.MyPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String dias;



    ImageView das;

    //TODO boost auf 1800 ändern
    int boost = 120000;


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

    Handler h = new Handler();

    InterstitialAd mInterstitialAd;


    static final String TAG = "BillingService";
    // The helper object
    IabHelper mHelper;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);
        String base64EncodedPublicKey = getString(R.string.pubkey);

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

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
            }
        });

        closeAgency = (Button) findViewById(R.id.closeAgency);
        assert closeAgency != null;
        closeAgency.setOnClickListener(this);

        buildboost = (Button) findViewById(R.id.buildboost);
        assert buildboost != null;
        buildboost.setOnClickListener(this);


        diastogold = (Button) findViewById(R.id.diastogold);
        diastogold.setOnClickListener(this);


        doublegold = (Button) findViewById(R.id.doublegold);
        assert doublegold != null;
        doublegold.setOnClickListener(this);

        goldagency = (TextView) findViewById(R.id.goldagency);
        diapoolagency = (TextView) findViewById(R.id.diapoolagency);


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

                MyPreferences prefs1 = MyPreferences.getInstance(AgencyActivity.this,"POOL");
                String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
                goldagency.setText(": " + Pooltext );

                MyPreferences prefs3 = MyPreferences.getInstance(AgencyActivity.this,"DIAMONDS");
                //Diapool text aktualisieren
                String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                diapoolagency.setText(": " + diapooltext );

                MyPreferences deziit =MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
                int endtime = (int) (System.currentTimeMillis()/1000) ;
                int startzeit = deziit.getInt("startzeit", 31);
                int datzeit = endtime - startzeit;
                 restzeit =  30 - datzeit;

                if (datzeit >= 30){
                    MyPreferences deziit1 =MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
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

        if(id == R.id.diastogold){

            MyPreferences prefs3 = MyPreferences.getInstance(AgencyActivity.this,"DIAMONDS");
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
                            MyPreferences editor1 = MyPreferences.getInstance(AgencyActivity.this,"thatmanydiastogold");
                            editor1.putInt("thatmanydiastogold", progressvalue);
                            editor1.apply();


                        }
                    }

            );
            dialogconvertdias.show();





        }
        if (id == R.id.confirmcashout) {

            MyPreferences datad = MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
            ad =  datad.getBoolean("ad", true);

            if(!ad) {

                String timeleft = String.format(Locale.US, "%02d:%02d",
                        TimeUnit.SECONDS.toMinutes(restzeit) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restzeit)),
                        TimeUnit.SECONDS.toSeconds(restzeit) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restzeit)));


                Toast.makeText(AgencyActivity.this, "please wait " + timeleft + " before doing this", Toast.LENGTH_SHORT).show();
            }


            if(ad) {
                MyPreferences deziit1 = MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
                deziit1.putBoolean("ad", false);
                deziit1.apply();


                //holen von wieviele diamanten erstellen
                MyPreferences prefs1 = MyPreferences.getInstance(AgencyActivity.this,"thatmanydiastogold");

                int diaweg = (prefs1.getInt("thatmanydiastogold", 0));


                //Holen von Pool
                MyPreferences prefs2 = MyPreferences.getInstance(AgencyActivity.this,"DIAMONDS");


                //checken obs genug gold für den convert hat
                if (diaweg >= prefs2.getInt("DIAMONDS", 0)) {

                    Toast.makeText(AgencyActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();


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
                                        .addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);
                            }

                            @Override
                            public void onAdClosed() {
                                //thanks
                                Toast.makeText(AgencyActivity.this, "thank you", Toast.LENGTH_SHORT).show();
                                dialogconvertdias.dismiss();
                                AdRequest adRequest = new AdRequest.Builder()
                                        .addTestDevice("16201-16201")
                                        .build();

                                //geld aktualisierung
                                final MyPreferences google = MyPreferences.getInstance(AgencyActivity.this,"google");
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
                                .addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }


                    //dialog beenden
                    dialogconvertdias.dismiss();

                }
            }

        }


            if (id == R.id.doublegold) {
                MyPreferences doubler = MyPreferences.getInstance(AgencyActivity.this,"doublecoll");
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
                alertDialogBuilder.setPositiveButton("buy", new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                //geld aktualisierung
                                final MyPreferences google = MyPreferences.getInstance(AgencyActivity.this,"google");
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
            MyPreferences datafab1 = MyPreferences.getInstance(AgencyActivity.this,"datafab1");
            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {
                fab1.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }


            //boostfab2
            MyPreferences datafab2 = MyPreferences.getInstance(AgencyActivity.this,"datafab2");
            Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
            if (cdfab2) {
                fab2.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab3
            MyPreferences datafab3 = MyPreferences.getInstance(AgencyActivity.this,"datafab3");
            Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
            if (cdfab3) {
                fab3.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostfab4
            MyPreferences datafab4 = MyPreferences.getInstance(AgencyActivity.this,"datafab4");
            Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
            if (cdfab4) {
                fab4.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            //boostbank
            MyPreferences databank = MyPreferences.getInstance(AgencyActivity.this,"dataBank");
            Boolean databanklvl = (databank.getBoolean("isLeveling", false));
            if (databanklvl) {
                bank.setVisibility(View.VISIBLE);
                platzhalterboost.setVisibility(View.GONE);

            }

            gebaeudeboost.show();


        }


        if (id == R.id.payboostwithad) {

            selectedId = radioGroup.getCheckedRadioButtonId();

            MyPreferences datad = MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
            ad =  datad.getBoolean("ad", true);

            if(!ad) {

                String timeleft = String.format(Locale.US, "%02d:%02d",
                        TimeUnit.SECONDS.toMinutes(restzeit) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restzeit)),
                        TimeUnit.SECONDS.toSeconds(restzeit) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restzeit)));


                Toast.makeText(AgencyActivity.this, "please wait " + timeleft + " before doing this", Toast.LENGTH_SHORT).show();
            }


            if(ad) {
                MyPreferences deziit1 =MyPreferences.getInstance(AgencyActivity.this,"blockzeit");
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
                                MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab1");
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab1");
                                editor3.putInt("startTime", startTimefab1 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();
                            }
                            if (selectedId == fab2.getId()) {
                                MyPreferences Fab2upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab2");
                                int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab2");
                                editor3.putInt("startTime", startTimefab2 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();


                            }

                            if (selectedId == fab3.getId()) {
                                MyPreferences Fab3upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab3");
                                int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab3");
                                editor3.putInt("startTime", startTimefab3 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == fab4.getId()) {

                                MyPreferences Fab4upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab4");
                                int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab4");
                                editor3.putInt("startTime", startTimefab4 - boost);
                                editor3.apply();
                                Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                                gebaeudeboost.dismiss();

                            }

                            if (selectedId == bank.getId()) {

                                MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeBank");
                                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                                MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeBank");
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

            MyPreferences prefs = MyPreferences.getInstance(AgencyActivity.this,"POOL");
            MyPreferences editor2 = MyPreferences.getInstance(AgencyActivity.this,"POOL");


            if ((prefs.getInt("POOL", 0)) < 500) {
                Toast.makeText(AgencyActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

            }
            if ((prefs.getInt("POOL", 0)) >= 500) {



                if (selectedId == fab1.getId()) {
                    MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab1");
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab1");
                    editor3.putInt("startTime", startTimefab1 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }
                if (selectedId == fab2.getId()) {
                    MyPreferences Fab2upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab2");
                    int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab2");
                    editor3.putInt("startTime", startTimefab2 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();

                }

                if (selectedId == fab3.getId()) {
                    MyPreferences Fab3upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab3");
                    int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab3");
                    editor3.putInt("startTime", startTimefab3 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }

                if (selectedId == fab4.getId()) {

                    MyPreferences Fab4upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab4");
                    int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeFab4");
                    editor3.putInt("startTime", startTimefab4 - boost);
                    editor3.apply();
                    Toast.makeText(AgencyActivity.this, "boost applied", Toast.LENGTH_SHORT).show();
                    gebaeudeboost.dismiss();
                    //bezahlen
                    editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                    editor2.apply();
                }

                if (selectedId == bank.getId()) {

                    MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeBank");
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    MyPreferences editor3 = MyPreferences.getInstance(AgencyActivity.this,"startTimeUpgradeBank");
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
    // We're being destroyed. It's important to dispose of the helper here!
    @Override
    public void onDestroy() {
        super.onDestroy();

        // very important:
      /*  if (mBroadcastReceiver != null) {
         unregisterReceiver(mBroadcastReceiver);
       }*/

        // very important:
        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mHelper = null;
        }
    }

}
