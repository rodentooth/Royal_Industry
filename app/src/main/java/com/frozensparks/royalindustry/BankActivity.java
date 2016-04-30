package com.frozensparks.royalindustry;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.INotificationSideChannel;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
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
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.matesnetwork.Cognalys.VerifyMobile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    int delay = 100; //milliseconds

    ProgressBar diaconvertingtimeprogress;


    String str_result;
    int connectcode;

    String DASRESULTAT;

    Dialog veridia;

    TextView diatimer;
    Button confirmcashout;
    public static TextView cashoutonwait;

    int timerfürdias = 123;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        setContentView(R.layout.activity_bank);

        //geld aktualisierung
        final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);
        String type = "convert";
        String gid = google.getString("id", "0");
        bgworkerdias bgworker1 =new bgworkerdias(BankActivity.this);
        bgworker1.execute(type, gid,"" , "convert");

        //notifications

         mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
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
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("16201-16201")
                .build();

        mInterstitialAd.loadAd(adRequest);


        Button closeBank = (Button) findViewById(R.id.closeBank);
        assert closeBank != null;
        closeBank.setOnClickListener(this);

        Button cashout = (Button) findViewById(R.id.cashout);
        assert cashout != null;
        cashout.setOnClickListener(this);


        GoldToDias = (Button) findViewById(R.id.GoldToDias);
        assert GoldToDias != null;
        GoldToDias.setOnClickListener(this);


        currentGoldBalance = (TextView) findViewById(R.id.currentGoldBalance);

        diatimer = (TextView) findViewById(R.id.diatimer);


        diatext = (TextView) findViewById(R.id.diatext);

        cashoutonwait = (TextView) findViewById(R.id.cashoutonwait);

        SharedPreferences cashouttext = getSharedPreferences("cashout", MODE_PRIVATE);
        cashoutonwait.setText("waiting for payout:"+ String.valueOf(cashouttext.getFloat("cashout", 0)+"$"));



        diaconvertingtimeprogress = (ProgressBar) findViewById(R.id.diaconvertingtimeprogress);

        assert diaconvertingtimeprogress != null;
        diaconvertingtimeprogress.setMax(timerfürdias);


        SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);
        String stringGoldmax = String.valueOf(BankMax.getInt("maxGoldStorage", 1000));

        //Aktualisiere den Kontostatus
        SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
        String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
        currentGoldBalance.setText(getString(R.string.Gold) + ": " + Pooltext + "/" + stringGoldmax);

        SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
        //Diapool text aktualisieren
        String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
        diatext.setText((getString(R.string.Diamonds) + ": " + diapooltext));


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


                //startzeit holen
                SharedPreferences DiamantConvertCountdown = getSharedPreferences("DiamantConvertCountdown", MODE_PRIVATE);
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

                SharedPreferences prefs4 = getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE);
                Boolean hatsdiaszumholen = (prefs4.getBoolean("trueorfalse", false));

                if (hatsdiaszumholen) {
                    if (secondsElapsed >= timerfürdias) {

                        //request senden

                        final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);

                        if (inforeground) {

                            String type = "request";
                            String gid = google.getString("id", "0");
                            bgworker bgworker = new bgworker(BankActivity.this);
                            bgworker.execute(type, gid, "diacollect");
                        }
                        if (!inforeground){
                            mNotificationManager.notify(mId, mBuilder.build());
                        }


                        //keine dias mehr zu holen also false
                        SharedPreferences.Editor editor3 = getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE).edit();
                        editor3.putBoolean("trueorfalse", false);
                        editor3.apply();

                        //diaspeicher im seekbar löschen
                        SharedPreferences.Editor editor1 = getSharedPreferences("thatmanydias", MODE_PRIVATE).edit();
                        editor1.putInt("thatmanydias", 0);
                        editor1.apply();

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
        if (id == R.id.GoldToDias) {


            SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);

            final bgworker bgworker = new bgworker(context);

            String type = "verify";
            String gid = google.getString("id", "0");

            bgworker.execute(type, gid, "0", "0", "diascreator");

        }


        if (id == R.id.cashout) {

            SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
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
            diasCost.setText(getString(R.string.Costs) + " " + progressvalue * 10 + getString(R.string.Diamonds));


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
                            diasCost.setText(getString(R.string.Costs) + " " + fück * 10 + getString(R.string.Diamonds));

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue/100 + "$");
                            diasCost.setText(getString(R.string.Costs) + " " + fück * 10 + getString(R.string.Diamonds));

                            //den progressvalue (wieviele dias) speichern
                            SharedPreferences.Editor editor1 = getSharedPreferences("thatmanycash", MODE_PRIVATE).edit();
                            editor1.putFloat("thatmanycash", progressvalue/100);
                            editor1.apply();


                        }
                    }

            );
            dialogconvertdias.show();




        }

            if (id == R.id.verify) {


                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                final String imei = telephonyManager.getDeviceId();
                final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);
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



                                //holen von wieviele diamanten erstellen
                                SharedPreferences prefs1 = getSharedPreferences("thatmanydias", MODE_PRIVATE);

                                //wenn 0 diamanten gewählt, abbrechen
                                if (prefs1.getInt("thatmanydias", 0) == 0) {


                                    //dialog beenden
                                    dialogconvertdias.dismiss();
                                }


                                if (prefs1.getInt("thatmanydias", 0) >= 1) {

                                    //CHECKEN OB ES DEN DIASPEICHER nicht überschreitet
                                    SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);


                                        //Holen von Pool
                                        SharedPreferences prefs2 = getSharedPreferences("POOL", MODE_PRIVATE);

                                        //diamantenanzahl in gold umwandeln
                                        int goldweg = (prefs1.getInt("thatmanydias", 1)) * 200;


                                        //checken obs genug gold für den convert hat
                                        if (goldweg >= prefs2.getInt("POOL", 0)) {

                                            Toast.makeText(BankActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();


                                        }

                                        if (goldweg <= prefs2.getInt("POOL", 0)) {


                                            //Pool aktualisieren, gold abziehen
                                            SharedPreferences.Editor editor1 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                            editor1.putInt("POOL", (prefs2.getInt("POOL", 0)) - goldweg);
                                            editor1.apply();

                                            //Pooltext aktualisieren

                                            String stringGoldmax = String.valueOf(BankMax.getInt("maxGoldStorage", 1000));
                                            String Pooltext = String.valueOf(prefs2.getInt("POOL", 0));
                                            currentGoldBalance.setText(getString(R.string.Gold) + ": " + Pooltext + "/" + stringGoldmax);


                                            //Timer Starten
                                            SharedPreferences.Editor editor3 = getSharedPreferences("DiamantConvertCountdown", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
                                            editor3.apply();


                                            //es hat dias true
                                            SharedPreferences.Editor editor4 = getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE).edit();
                                            editor4.putBoolean("trueorfalse", true);
                                            editor4.apply();

                                            //es hat dias linken

                                            final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);

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
                                        .addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);                            }

                            @Override
                            public void onAdClosed() {
                                //thanks
                                Toast.makeText(BankActivity.this, "thank you", Toast.LENGTH_SHORT).show();
                                dialogconvertdias.dismiss();
                                AdRequest adRequest = new AdRequest.Builder()
                                        .addTestDevice("16201-16201")
                                        .build();

                                mInterstitialAd.loadAd(adRequest);
                            }
                        });

                    }
                    else {
                        Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        AdRequest adRequest = new AdRequest.Builder()
                                .addTestDevice("16201-16201")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }





            }
        if (id == R.id.confirmcashout) {





                                //holen von wieviele diamanten erstellen
                                SharedPreferences prefs1 = getSharedPreferences("thatmanycash", MODE_PRIVATE);

                                float diaweg = (prefs1.getFloat("thatmanycash", 0)*1000);

                                //wenn 0 diamanten gewählt, abbrechen
                                if (diaweg < 10) {

                                    Toast.makeText(BankActivity.this, "minimum 10 diamonds", Toast.LENGTH_SHORT).show();

                                    //dialog beenden
                                    dialogconvertdias.dismiss();
                                }


                                if (diaweg >= 10) {

                                    //CHECKEN OB ES DEN DIASPEICHER nicht überschreitet
                                    SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);


                                    //Holen von Pool
                                    SharedPreferences prefs2 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);



                                    //checken obs genug gold für den convert hat
                                    if (diaweg >= prefs2.getInt("DIAMONDS", 0)) {

                                        Toast.makeText(BankActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();


                                    }

                                    if (diaweg <= prefs2.getInt("DIAMONDS", 0)) {



                                        //es hat dias linken

                                        final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);

                                        int dias_int = prefs1.getInt("thatmanydias", 0);
                                        int loldiasfuck = (int)diaweg;
                                        String dias = Integer.toString(loldiasfuck);

                                        String type = "convert";
                                        String gid = google.getString("id", "0");
                                        bgworker bgworker =new bgworker(BankActivity.this);
                                        bgworker.execute(type, gid,dias , "convert");

                                        Toast.makeText(BankActivity.this, dias, Toast.LENGTH_SHORT).show();

                                        //dialog beenden
                                        dialogconvertdias.dismiss();

                                    }
                                }



        }
    }



        @Override
        protected void onActivityResult ( int arg0, int arg1, Intent arg2){
// TODO Auto-generated method stub
            super.onActivityResult(arg0, arg1, arg2);

            if (arg0 == VerifyMobile.REQUEST_CODE) {
                String message = arg2.getStringExtra("message");
                int result = arg2.getIntExtra("result", 0);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                String restring = Integer.toString(result);

                Toast.makeText(BankActivity.this, restring, Toast.LENGTH_SHORT).show();
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
        howmanygoldtodias.setText(getString(R.string.Create) + " " + progressvalue + getString(R.string.Diamonds));
        diasCost.setText(getString(R.string.Costs) + " " + progressvalue * 200 + getString(R.string.Gold));


        seekbar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {


                    int progressvalue;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        progressvalue = progress;
                        howmanygoldtodias.setText(getString(R.string.Create) + progressvalue + getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs) + progressvalue * 200 + getString(R.string.Gold));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                        howmanygoldtodias.setText(getString(R.string.Create) + progressvalue + getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs) + progressvalue * 200 + getString(R.string.Gold));

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        howmanygoldtodias.setText(getString(R.string.Create) + progressvalue + getString(R.string.Diamonds));
                        diasCost.setText(getString(R.string.Costs) + progressvalue * 200 + getString(R.string.Gold));

                        //den progressvalue (wieviele dias) speichern
                        SharedPreferences.Editor editor1 = getSharedPreferences("thatmanydias", MODE_PRIVATE).edit();
                        editor1.putInt("thatmanydias", progressvalue);
                        editor1.apply();


                    }
                }

        );
        dialogconvertdias.show();
    }

    public void serververification(){


        View verification = View.inflate(this, R.layout.verification, null);
         veridia = new Dialog(BankActivity.this);
        veridia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        veridia.setContentView(verification);

        coucod = (EditText) veridia.findViewById(R.id.coucod);
        phnr = (EditText) veridia.findViewById(R.id.phnr);
        coucod.setText(VerifyMobile
                .getCountryCode(getApplicationContext()));

        verify = (Button) veridia.findViewById(R.id.verify);
        verify.setOnClickListener(this);



        veridia.show();

    }

public void numberverification(){
    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    final String imei = telephonyManager.getDeviceId();


    final SharedPreferences google = getSharedPreferences("google", MODE_PRIVATE);

    final String notcleared = coucod.getText().toString() + phnr.getText().toString();




    final String m_Text = "+"+ notcleared.replaceAll("[-+.^:,;#/()'*N!¨_ ]","");
    AlertDialog.Builder nrcheck = new AlertDialog.Builder(context);
    nrcheck.setMessage("is \n" + m_Text + "\n\ncorrect?");


    nrcheck.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent in = new Intent(BankActivity.this, VerifyMobile.class);
                    in.putExtra("app_id", "759bda3b29d44fada046640");
                    in.putExtra("access_token", "4358c6d8507f116bfa46c3a25b83bea5763254e2");
                    in.putExtra("mobile", m_Text);

                    startActivityForResult(in, VerifyMobile.REQUEST_CODE);

                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                            String type = "verify";
                            String gid = google.getString("id", "0");
                            bgworker bgworker =new bgworker(BankActivity.this);
                            bgworker.execute(type, gid, imei, m_Text, "numberverify");
                        }
                    }, 10000);


                }
            });

    nrcheck.show();








}



        @Override
        public void onStart () {
            super.onStart();
            inforeground = true;


        }
        @Override
        public void onStop () {
            super.onStop();
            inforeground = false;


        }
    @Override
    protected void onResume() {
        super.onResume();
        inforeground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
       inforeground = false;
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
        String veri_url =  "http://frozensparks.com/verify.php";
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

            } catch (MalformedURLException e) {
                e.printStackTrace();
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
                String line="";

                while((line=bufferedReader.readLine())!=null){
                    result +=line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
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
                String line="";

                while((line=bufferedReader.readLine())!=null){
                    result +=line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if(type.equals("verify")){
            try {
                doafter = params[4];
                String googleID = params[1];
                String deviceID = params[2];
                String number = params[3];


                URL url = new URL(veri_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8")+"="+URLEncoder.encode(googleID, "UTF-8")+"&"
                        +URLEncoder.encode("deviceID", "UTF-8")+"="+URLEncoder.encode(deviceID, "UTF-8")+"&"
                        +URLEncoder.encode("number", "UTF-8")+"="+URLEncoder.encode(number, "UTF-8");
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





            } catch (MalformedURLException e) {
                e.printStackTrace();
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


            if (connectcode >= 1) {

                SharedPreferences prefs1 = getSharedPreferences("thatmanycash", MODE_PRIVATE);
                SharedPreferences prefs2 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);

                float diaweg = prefs1.getFloat("thatmanycash", 0)*1000;

                //Pool aktualisieren, gold abziehen
                SharedPreferences.Editor editor1 = getSharedPreferences("DIAMONDS", MODE_PRIVATE).edit();
                editor1.putInt("DIAMONDS", (prefs2.getInt("DIAMONDS", 0)) - (int)diaweg);
                editor1.apply();

                //Pooltext aktualisieren
                SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
                String Pooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                diatext.setText(getString(R.string.Diamonds) + ": " + Pooltext);



                //Pool aktualisieren, dias abziehen


                // cashout aktualisieren
                float rappen = (float)(connectcode);
                float rtrdrp = rappen/100;
                String rpstr = Float.toString(rtrdrp);

                Toast.makeText(BankActivity.this, rpstr, Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor2 = getSharedPreferences("cashout", MODE_PRIVATE).edit();
                editor2.putFloat("cashout", rtrdrp);
                editor2.commit();
                SharedPreferences cashouttext = getSharedPreferences("cashout", MODE_PRIVATE);

                cashoutonwait.setText("waiting for payout:" + String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();


                    mInterstitialAd.setAdListener(new AdListener() {

                        @Override

                        public void onAdLoaded() {
                            Toast.makeText(BankActivity.this, "loaded", Toast.LENGTH_SHORT).show();

                        }
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            Toast.makeText(BankActivity.this, "you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onAdClosed() {
                            //thanks
                            Toast.makeText(BankActivity.this, "thank you", Toast.LENGTH_SHORT).show();
                            dialogconvertdias.dismiss();
                            AdRequest adRequest = new AdRequest.Builder()
                                    .addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }
                    });

                }

                else {
                    Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice("16201-16201")
                            .build();

                    mInterstitialAd.loadAd(adRequest);

                }



            }
            if (connectcode == -1) {
                Toast.makeText(BankActivity.this, "too much diamonds fordered", Toast.LENGTH_SHORT).show();
            }

            if (connectcode == -2) {
                Toast.makeText(BankActivity.this, "no verify", Toast.LENGTH_SHORT).show();
            }


        }


        if(doafter =="diacollect") {
            if (connectcode >= 1) {
                //
                Toast.makeText(BankActivity.this, "diamonds collected", Toast.LENGTH_SHORT).show();


                //Diapool text aktualisieren
                diatext.setText((getString(R.string.Diamonds) + ": " + connectcode));

                //databank aktualisieren
                SharedPreferences.Editor editor1 = getSharedPreferences("DIAMONDS", MODE_PRIVATE).edit();
                editor1.putInt("DIAMONDS", connectcode);
                editor1.commit();
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();


                    mInterstitialAd.setAdListener(new AdListener() {

                        @Override

                        public void onAdLoaded() {
                            Toast.makeText(BankActivity.this, "loaded", Toast.LENGTH_SHORT).show();

                        }
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            Toast.makeText(BankActivity.this, "you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onAdClosed() {
                            //thanks
                            Toast.makeText(BankActivity.this, "thank you", Toast.LENGTH_SHORT).show();
                            dialogconvertdias.dismiss();
                            AdRequest adRequest = new AdRequest.Builder()
                                    .addTestDevice("16201-16201")
                                    .build();

                            mInterstitialAd.loadAd(adRequest);
                        }
                    });

                }

                else {
                    Toast.makeText(BankActivity.this, "Ad did not load. you have to be connected to the internet", Toast.LENGTH_SHORT).show();
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice("16201-16201")
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
            if (connectcode == 110) {
                //
                Toast.makeText(BankActivity.this, "not verified. but why?", Toast.LENGTH_SHORT).show();
            }
            if (connectcode == 11101) {
                //hacker ban
                Toast.makeText(BankActivity.this, "you are banned", Toast.LENGTH_SHORT).show();
            }
            if (connectcode == 11111) {
                //übertrag ok
                Toast.makeText(BankActivity.this, "Timer started", Toast.LENGTH_SHORT).show();
            }

        }

if(doafter =="diascreator") {
    if (connectcode == 11) {
        //show 'createdias'
        diaconverter();
    }


    if (connectcode == 1010) {
        //check number on verification server

        serververification();

    }
    if (connectcode == 100) {

        //create new verification
        serververification();
    }
}
        if(doafter =="verifyonserver") {
            if (connectcode == 11) {
                diaconverter();

            }
            if (connectcode == 100) {
                Toast.makeText(BankActivity.this, "cant  verify number twice", Toast.LENGTH_SHORT).show();

                //create new verification
                serververification();
            }
            if (connectcode == 1010) {
                Toast.makeText(BankActivity.this, "Verify your number", Toast.LENGTH_SHORT).show();

                //create new verification
                numberverification();
            }
            if (connectcode == 10111) {
                Toast.makeText(BankActivity.this, "verification sucess", Toast.LENGTH_SHORT).show();

                diaconverter();

            }
            if (connectcode == 10110) {
                Toast.makeText(BankActivity.this, "verification no sucess. server error", Toast.LENGTH_SHORT).show();
            }
        }



        if(doafter =="numberverify") {
            if (connectcode == 11) {
                diaconverter();

            }
            if (connectcode == 100) {
                Toast.makeText(BankActivity.this, "cant  verify number twice", Toast.LENGTH_SHORT).show();

                //create new verification
                serververification();
            }
            if (connectcode == 1010) {


            }
            if (connectcode == 10111) {
                Toast.makeText(BankActivity.this, "verification sucess", Toast.LENGTH_SHORT).show();

                diaconverter();

            }
            if (connectcode == 10110) {
                Toast.makeText(BankActivity.this, "verification no sucess. server error", Toast.LENGTH_SHORT).show();
            }
        }


    }


    @Override
    protected void onProgressUpdate(Void ... values) {
        super.onProgressUpdate(values);

    }


}

}
