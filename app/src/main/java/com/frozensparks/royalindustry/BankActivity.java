package com.frozensparks.royalindustry;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BankActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    int maxdias;
    TextView howmanygoldtodias;
    TextView diasCost;
    TextView currentGoldBalance;
    TextView diatext;
    SeekBar seekbar;
    Button confirmconvert;
    Dialog dialogconvertdias;
    float thatmanycashoutinfloat;
    InterstitialAd mInterstitialAd;

    Button GoldToDias;

    //notification
    Boolean inforeground;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;



    //nrveri
    Button verify;
    EditText phnr;
    EditText coucod;

    Handler h = new Handler();
    int delay = 1000; //milliseconds

    ProgressBar diaconvertingtimeprogress;


    String str_result;
    int connectcode;

    String DASRESULTAT;

    Dialog veridia;
    Button faqButton;

    TextView diatimer;
    Button confirmcashout;
    public static TextView cashoutonwait;

    int timerfürdias = 1800;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int mId;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5669148825390630~9003934904");

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.enableAutoActivityTracking(true);

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


        setContentView(R.layout.activity_bank);



        //geld aktualisierung
        final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));
        String type = "convert";
        String dat = "request";
        String gid = google.getString("id", "0");
        bgworkerdias bgworker1 =new bgworkerdias(context);
        bgworker1.execute(type, gid,"" , "convert");

        bgworkerdias2 lol = new bgworkerdias2(context);
        lol.execute(dat, gid, "diacollect");

        //notifications

        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setPriority(2)
                        .setContentTitle("ROYAL INDUSTRY")
                        .setContentText("Your diamonds are ready!")
                        .setAutoCancel(true);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, IntroActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(BankActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.


        //ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5669148825390630/4434134509");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("E414DE4FE2ADD841904A5328B51C1195")
                .build();

        mInterstitialAd.loadAd(adRequest);


        Button closeBank = (Button) findViewById(R.id.closeBank);
        assert closeBank != null;
        closeBank.setOnClickListener(this);


        Button cashout = (Button) findViewById(R.id.cashout);
        assert cashout != null;
        cashout.setOnClickListener(this);

        Button faqButton = (Button) findViewById(R.id.faqButton);
        assert faqButton != null;
        faqButton.setOnClickListener(this);


        GoldToDias = (Button) findViewById(R.id.GoldToDias);
        assert GoldToDias != null;
        GoldToDias.setOnClickListener(this);


        currentGoldBalance = (TextView) findViewById(R.id.currentGoldBalance);

        diatimer = (TextView) findViewById(R.id.diatimer);


        diatext = (TextView) findViewById(R.id.diatext);

        cashoutonwait = (TextView) findViewById(R.id.cashoutonwait);

        SharedPreferences cashouttext = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("cashout", MODE_PRIVATE));

      //  cashoutonwait.setText( String.valueOf(cashouttext.getFloat("cashout", 0)+"$"));



        diaconvertingtimeprogress = (ProgressBar) findViewById(R.id.diaconvertingtimeprogress);

        assert diaconvertingtimeprogress != null;
        diaconvertingtimeprogress.setMax(timerfürdias);


        SharedPreferences BankMax = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("dataBank", MODE_PRIVATE));
        String stringGoldmax = String.valueOf(BankMax.getInt("maxGoldStorage", 1000));

        //Aktualisiere den Kontostatus
        SharedPreferences prefs1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
        String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
        currentGoldBalance.setText(": " + Pooltext + "/" + stringGoldmax);




        if ((prefs1.getInt("POOL", 0)) > 4000) {

            maxdias = 20;
        }
        if ((prefs1.getInt("POOL", 0)) <= 4000) {

            maxdias = ((prefs1.getInt("POOL", 0)) / 200);
        }


        h.postDelayed(new Runnable() {
            public void run() {
                //jede 0.1 sekunde folgendes aktualisieren

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
                SharedPreferences prefs3 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
                //Diapool text aktualisieren
                String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                diatext.setText(": " + diapooltext);


                //startzeit holen
                SharedPreferences DiamantConvertCountdown = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DiamantConvertCountdown", MODE_PRIVATE));
                int startTime = DiamantConvertCountdown.getInt("startTime", 0); //0 is the default value.


                //endzeit als jetzt definieren
                int endTime = ((int) System.currentTimeMillis() / 1000);

                int secondsElapsed = endTime - startTime;


                if (secondsElapsed == 0) {
                    //converterknopf auf 0 sec sichtbar
                    GoldToDias.setVisibility(View.VISIBLE);
                    diatimer.setVisibility(View.INVISIBLE);

                    //progbar update
                    diaconvertingtimeprogress.setProgress(secondsElapsed);
                }

                if (secondsElapsed >= 1) {
                    if (secondsElapsed < timerfürdias) {
                        //converterknopf unsichtbar
                        GoldToDias.setVisibility(View.INVISIBLE);
                        //progbar update
                        diaconvertingtimeprogress.setProgress(secondsElapsed);

                        int time =timerfürdias - secondsElapsed;

                        String diatimeleft = String.format(Locale.US,"%02d:%02d",
                                TimeUnit.SECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(time)),
                                TimeUnit.SECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(time)));

                        diatimer.setVisibility(View.VISIBLE);
                        diatimer.setText(diatimeleft);

                    }
                }

                SharedPreferences prefs4 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE));
                Boolean hatsdiaszumholen = (prefs4.getBoolean("trueorfalse", false));

                if (hatsdiaszumholen) {
                    if (secondsElapsed >= timerfürdias) {

                        //request senden

                        final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                        if (inforeground != null) {
                            if (inforeground) {

                                String type = "request";
                                String gid = google.getString("id", "0");
                                bgworker bgworker = new bgworker(BankActivity.this);
                                bgworker.execute(type, gid, "diacollect");
                            }
                            if (!inforeground) {
                                mNotificationManager.notify(mId, mBuilder.build());
                            }
                        }
                        if (inforeground == null) {
                            mNotificationManager.notify(mId, mBuilder.build());

                        }


                        //keine dias mehr zu holen also false
                        SharedPreferences editor3 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE));
                        editor3.edit().putBoolean("trueorfalse", false).apply();

                        //diaspeicher im seekbar löschen
                        SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanydias", MODE_PRIVATE));
                        editor1.edit().putInt("thatmanydias", 0).apply();

                        diaconvertingtimeprogress.setProgress(0);
                        //converterknopf sichtbar
                        GoldToDias.setVisibility(View.VISIBLE);
                        diatimer.setVisibility(View.INVISIBLE);

                    }

                }

                h.postDelayed(this, delay);
            }
        }, delay);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.closeBank) {
            Intent start = new Intent(BankActivity.this, MainActivity.class);
            BankActivity.this.startActivity(start);
            finish();

        }
        final EditText input;
        if (id == R.id.faqButton) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.frozensparks.com/royalindustryfaq.html"));
            startActivity(browserIntent);

        }
        if (id == R.id.GoldToDias) {


            diaconverter();

        }


        if (id == R.id.cashout) {

            SharedPreferences prefs3 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
            //Diapool text aktualisieren
            int dias = (prefs3.getInt("DIAMONDS", 0));


            View converter = View.inflate(this, R.layout.cashoutconverter, null);
            dialogconvertdias = new Dialog(BankActivity.this);
            dialogconvertdias.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogconvertdias.setContentView(converter);


            seekbar = (SeekBar) dialogconvertdias.findViewById(R.id.seekBarcash);
            howmanygoldtodias = (TextView) dialogconvertdias.findViewById(R.id.howmanydiastocash);
            diasCost = (TextView) dialogconvertdias.findViewById(R.id.cashoutcosts);

            confirmcashout = (Button) dialogconvertdias.findViewById(R.id.confirmcashout);
            confirmcashout.setOnClickListener(this);


            seekbar.setMax(dias/10);

            float progressvalue = 0;
            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue/100 + "$");
            diasCost.setText(getString(R.string.Costs) + " " + progressvalue * 10+ " " + getString(R.string.Diamonds));


            seekbar.setOnSeekBarChangeListener(

                    new SeekBar.OnSeekBarChangeListener() {


                        float progressvalue;
                        int fück;

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            progressvalue = progress;
                            fück = progress;
                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue/100 + "$");
                            diasCost.setText(getString(R.string.Costs) + " " + fück * 10 + getString(R.string.Diamonds));
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue/100 + "$");
                            diasCost.setText(getString(R.string.Costs) + " " + fück * 10 + " "+ getString(R.string.Diamonds));

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue/100 + "$");
                            diasCost.setText(getString(R.string.Costs) + " " + fück * 10 + " "+ getString(R.string.Diamonds));

                            //den progressvalue (wieviele dias) speichern
                            //SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanycash", MODE_PRIVATE));
                            //editor1.edit().putFloat("thatmanycash", progressvalue/100).apply();
                             thatmanycashoutinfloat = progressvalue / 100;

                        }
                    }

            );
            dialogconvertdias.show();




        }

        if (id == R.id.verify) {


            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String imei = telephonyManager.getDeviceId();
            final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));
            String m_Text = coucod.getText().toString() + phnr.getText().toString();
            String type = "verify";
            String gid = google.getString("id", "0");
            bgworker bgworker =new bgworker(BankActivity.this);

            bgworker.execute(type, gid, imei, m_Text, "verifyonserver");
            veridia.dismiss();
        }


        if (id == R.id.confirmconvert) {







            //werbung starten
            // Nur wenn die ad geladen hat, das converten erlauben
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
              //  Toast.makeText(BankActivity.this, R.string.click_on_ad, Toast.LENGTH_LONG).show();



                //holen von wieviele diamanten erstellen
                SharedPreferences prefs1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanydias", MODE_PRIVATE));

                //wenn 0 diamanten gewählt, abbrechen
                if (prefs1.getInt("thatmanydias", 0) == 0) {


                    //dialog beenden
                    dialogconvertdias.dismiss();
                }


                if (prefs1.getInt("thatmanydias", 0) >= 1) {

                    //CHECKEN OB ES DEN DIASPEICHER nicht überschreitet
                    SharedPreferences BankMax = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("dataBank", MODE_PRIVATE));


                    //Holen von Pool
                    SharedPreferences prefs2 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));

                    //diamantenanzahl in gold umwandeln
                    int goldweg = (prefs1.getInt("thatmanydias", 1)) * 200;


                    //checken obs genug gold für den convert hat
                    if (goldweg > prefs2.getInt("POOL", 0)) {

                        Toast.makeText(BankActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();


                    }

                    if (goldweg <= prefs2.getInt("POOL", 0)) {


                        //Pool aktualisieren, gold abziehen
                        SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("POOL", MODE_PRIVATE));
                        editor1.edit().putInt("POOL", (prefs2.getInt("POOL", 0)) - goldweg).apply();

                        //Pooltext aktualisieren

                        String stringGoldmax = String.valueOf(BankMax.getInt("maxGoldStorage", 1000));
                        String Pooltext = String.valueOf(prefs2.getInt("POOL", 0));
                        currentGoldBalance.setText(": " + Pooltext + "/" + stringGoldmax);


                        //Timer Starten
                        SharedPreferences editor3 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DiamantConvertCountdown", MODE_PRIVATE));
                        editor3.edit().putInt("startTime", ((int) System.currentTimeMillis()) / 1000).apply();


                        //es hat dias true
                        SharedPreferences editor4 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE));
                        editor4.edit().putBoolean("trueorfalse", true).apply();

                        //es hat dias linken

                        final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));

                        int dias_int = prefs1.getInt("thatmanydias", 0);
                        String dias = Integer.toString(dias_int);

                        String type = "diamonds";
                        String gid = google.getString("id", "0");
                        bgworker bgworker =new bgworker(BankActivity.this);
                        bgworker.execute(type, gid,dias , "übertrag");


                        //dialog beenden
                        dialogconvertdias.dismiss();

                    }
                }


                mInterstitialAd.setAdListener(new AdListener() {

                    @Override

                    public void onAdLoaded() {

                    }
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        Toast.makeText(BankActivity.this, "we're so sorry. please try again", Toast.LENGTH_SHORT).show();
                        dialogconvertdias.dismiss();
                        AdRequest adRequest = new AdRequest.Builder()
                                //.addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);                            }

                    @Override
                    public void onAdClosed() {
                        //thanks
                        Toast.makeText(BankActivity.this, R.string.thx, Toast.LENGTH_SHORT).show();
                        dialogconvertdias.dismiss();
                        AdRequest adRequest = new AdRequest.Builder()
                                //.addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);
                    }
                });

            }
            else {
                Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                AdRequest adRequest = new AdRequest.Builder()
                        //.addTestDevice("16201-16201")
                        .build();

                mInterstitialAd.loadAd(adRequest);

            }





        }
        if (id == R.id.confirmcashout) {





            //holen von wieviele diamanten erstellen
            //SharedPreferences prefs1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanycash", MODE_PRIVATE));

            int diaweg=0;

             diaweg = (int)(thatmanycashoutinfloat * 1000);

                    //wenn 0 diamanten gewählt, abbrechen
            if (diaweg < 10) {

                Toast.makeText(BankActivity.this, "minimum 10 diamonds", Toast.LENGTH_SHORT).show();

                //dialog beenden
                dialogconvertdias.dismiss();
            }


            if (diaweg >= 10) {



                //Holen von Pool
                SharedPreferences prefs2 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));



                //checken obs genug gold für den convert hat
                if (diaweg > prefs2.getInt("DIAMONDS", 0)) {

                    Toast.makeText(BankActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();


                }

                if (diaweg <= prefs2.getInt("DIAMONDS", 0)) {



                    //es hat dias linken

                    final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));

                    int loldiasfuck = diaweg;
                    String dias = Integer.toString(loldiasfuck);

                    String type = "convert";
                    String gid = google.getString("id", "0");
                    bgworker bgworker =new bgworker(BankActivity.this);
                    bgworker.execute(type, gid,dias , "convert");


                    //dialog beenden
                    dialogconvertdias.dismiss();

                }
            }



        }
    }



    public void diaconverter(){
        View converter = View.inflate(this, R.layout.converter, null);
        dialogconvertdias = new Dialog(BankActivity.this);
        dialogconvertdias.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogconvertdias.setContentView(converter);


        seekbar = (SeekBar) dialogconvertdias.findViewById(R.id.seekBar);
        howmanygoldtodias = (TextView) dialogconvertdias.findViewById(R.id.howmanygoldtodias);
        diasCost = (TextView) dialogconvertdias.findViewById(R.id.diasCost);

        confirmconvert = (Button) dialogconvertdias.findViewById(R.id.confirmconvert);
        confirmconvert.setOnClickListener(this);


        if (maxdias >= 2) {
            seekbar.setMax(maxdias);
        }

        if (maxdias <= 1) {
            seekbar.setMax(1);
        }
        int progressvalue = 0;
        howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue + " "+ getString(R.string.Diamonds));
        diasCost.setText(getString(R.string.Costs) + " " + progressvalue * 200 + " "+ getString(R.string.Gold));


        seekbar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {


                    int progressvalue;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        progressvalue = progress;
                        howmanygoldtodias.setText(getString(R.string.Create) + " "+ progressvalue + " "+ getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs) + " "+ progressvalue * 200 + " "+ getString(R.string.Gold));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                        howmanygoldtodias.setText(getString(R.string.Create)+ " " + progressvalue + " "+ getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs) + " "+ progressvalue * 200 + " "+ getString(R.string.Gold));

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        howmanygoldtodias.setText(getString(R.string.Create)+ " " + progressvalue + " "+ getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs)+ " " + progressvalue * 200 + " "+ getString(R.string.Gold));

                        //den progressvalue (wieviele dias) speichern
                        SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanydias", MODE_PRIVATE));
                        editor1.edit().putInt("thatmanydias", progressvalue).apply();


                    }
                }

        );
        dialogconvertdias.show();
    }
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        inforeground=hasFocus;
    }
    @Override
    public void onStart () {
        super.onStart();


    }
    @Override
    public void onStop () {
        super.onStop();


    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }







    class bgworker extends AsyncTask<String, Void, String> {

        ProgressDialog waite;

        String doafter;

        public bgworker(Context context) {
            waite = new ProgressDialog(context);
            waite.requestWindowFeature(Window.FEATURE_NO_TITLE);
            waite.setMessage("checking user...");



        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (! ((Activity) context).isFinishing()) {
                waite.show();
            }
            // waite.show();
        }


        @Override
        protected String doInBackground(String... params) {


            String type = params[0];
            String dia_url = "http://frozensparks.com/diamonds.php";
            String collect_url =  "http://frozensparks.com/collect.php";
            String convert_url =  "http://frozensparks.com/cashout.php";


            if(type.equals("diamonds")){
                try {
                    doafter = params[3];
                    String googleID = params[1];
                    String diamonds = params[2];


                    URL url = new URL(dia_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8")+"="+URLEncoder.encode(googleID, "UTF-8")+"&"
                            +URLEncoder.encode("diamonds", "UTF-8")+"="+URLEncoder.encode(diamonds, "UTF-8");

                    Log.d("diamantanzahl ist " ,diamonds);


                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream =httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";

                    while((line=bufferedReader.readLine())!=null){
                        result +=line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if(type.equals("convert")){
                try {
                    doafter = params[3];
                    String googleID = params[1];
                    String diamonds = params[2];


                    URL url = new URL(convert_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8")+"="+URLEncoder.encode(googleID, "UTF-8")+"&"
                            +URLEncoder.encode("diamonds", "UTF-8")+"="+URLEncoder.encode(diamonds, "UTF-8");




                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream =httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;

                    while((line=bufferedReader.readLine())!=null){
                        result +=line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            if(type.equals("request")){
                try {
                    doafter = params[2];
                    String googleID = params[1];



                    URL url = new URL(collect_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8")+"="+URLEncoder.encode(googleID, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream =httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;

                    while((line=bufferedReader.readLine())!=null){
                        result +=line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;





                } catch (IOException e) {
                    e.printStackTrace();
                }


            }







            return null;


        }



        @Override
        protected void onPostExecute(String result) {



            DASRESULTAT = result;
            waite.dismiss();


            str_result = DASRESULTAT;

            if (str_result != null) {

                str_result = str_result.replace(" ", "");
                connectcode = Integer.parseInt(str_result);
            }

            if(doafter =="convert") {


                if (connectcode >= 0) {

                    SharedPreferences prefs1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("thatmanycash", MODE_PRIVATE));
                    SharedPreferences prefs2 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));

                    //float diaweg = prefs1.getFloat("thatmanycash", 0)*1000;

                    //Pool aktualisieren, gold abziehen
                    SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
                    editor1.edit().putInt("DIAMONDS", (prefs2.getInt("DIAMONDS", 0)) - (int)thatmanycashoutinfloat).apply();


                    //Pooltext aktualisieren
                    String Pooltext = String.valueOf((prefs2.getInt("DIAMONDS", 0)) - (int)thatmanycashoutinfloat);
                    diatext.setText(": " + Pooltext);



                    //Pool aktualisieren, dias abziehen


                    // cashout aktualisieren
                    float rappen = (float)(connectcode);
                    float rtrdrp = rappen/100;
                    String rpstr = Float.toString(rtrdrp);

                   // Toast.makeText(BankActivity.this, rpstr, Toast.LENGTH_SHORT).show();

                    SharedPreferences editor2 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("cashout", MODE_PRIVATE));
                    editor2.edit().putFloat("cashout", rtrdrp).apply();

                    SharedPreferences cashouttext = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("cashout", MODE_PRIVATE));

                    cashoutonwait.setText( String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

                    if(mInterstitialAd.isLoaded()){
                        mInterstitialAd.show();

                  //      Toast.makeText(BankActivity.this, R.string.click_on_ad, Toast.LENGTH_LONG).show();

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
                                Toast.makeText(BankActivity.this, R.string.thx, Toast.LENGTH_SHORT).show();
                                dialogconvertdias.dismiss();
                                AdRequest adRequest = new AdRequest.Builder()
                                        //.addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);
                                final SharedPreferences google = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                                String dat = "request";
                                String gid = google.getString("id", "0");
                                bgworkerdias2 lol = new bgworkerdias2(context);
                                lol.execute(dat, gid, "diacollect");
                            }
                        });

                    }

                    else {
                        Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        AdRequest adRequest = new AdRequest.Builder()
                                //.addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }



                }
                if (connectcode == -1) {
                    Toast.makeText(BankActivity.this, "too much diamonds fordered", Toast.LENGTH_SHORT).show();
                }

                if (connectcode == -2) {
                    Toast.makeText(BankActivity.this, "damn son y u play dis?", Toast.LENGTH_SHORT).show();
                }


            }


            if(doafter =="diacollect") {
                if (connectcode >= 1) {
                    //
                    Toast.makeText(BankActivity.this, "diamonds collected", Toast.LENGTH_SHORT).show();


                    //Diapool text aktualisieren
                    diatext.setText((": " + connectcode));

                    //databank aktualisieren
                    SharedPreferences editor1 = new ObscuredSharedPreferences(BankActivity.this,BankActivity.this.getSharedPreferences("DIAMONDS", MODE_PRIVATE));
                    editor1.edit().putInt("DIAMONDS", connectcode).apply();

                    if(mInterstitialAd.isLoaded()){
                        mInterstitialAd.show();
                      //  Toast.makeText(BankActivity.this, R.string.click_on_ad, Toast.LENGTH_LONG).show();


                        mInterstitialAd.setAdListener(new AdListener() {

                            @Override

                            public void onAdLoaded() {

                            }
                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                AdRequest adRequest = new AdRequest.Builder()
                                        //.addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);                        }
                            @Override
                            public void onAdClosed() {
                                //thanks
                                Toast.makeText(BankActivity.this, R.string.thx, Toast.LENGTH_SHORT).show();
                                if (dialogconvertdias!= null) {
                                    if (dialogconvertdias.isShowing()) {
                                        dialogconvertdias.dismiss();
                                    }
                                }
                                AdRequest adRequest = new AdRequest.Builder()
                                        //.addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);
                            }
                        });

                    }

                    //fabriklevelstunde 2.7
                    //fabriklevelstunde 2.7
                    //fabriklevelstunde 2.5
                    //fabriklevelstunde 2.5
                    //fabriklevelstunde 2.5
                    //fabriklevelstunde 8.3
                    //fabriklevelstunde 10.7
                    //fabriklevelstunde 12.5
                    //fabriklevelstunde 13.8
                    //fabriklevelstunde 15


                    else {
                        Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        AdRequest adRequest = new AdRequest.Builder()
                                //.addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }






                }



            }
            if(doafter =="übertrag") {
                if (connectcode == 10) {
                    //
                    Toast.makeText(BankActivity.this, "no user found. restart the app", Toast.LENGTH_SHORT).show();
                }

                if (connectcode == 101) {
                    //hacker ban
                    Toast.makeText(BankActivity.this, "you are banned", Toast.LENGTH_SHORT).show();
                }
                if (connectcode == 1111) {
                    //übertrag ok
                    Toast.makeText(BankActivity.this, R.string.click_on_ad, Toast.LENGTH_LONG).show();
                }
                Log.d("serverantwort ist: " ,result);

            }

            if(doafter =="diascreator") {
                if (connectcode == 11) {
                    //show 'createdias'
                    diaconverter();
                }


                if(connectcode == 1010) {
                    //check number on verification server



                }
                if(connectcode == 100) {

                    //create new verification

                }
            }


        }


        @Override
        protected void onProgressUpdate(Void ... values) {
            super.onProgressUpdate(values);

        }


    }

}