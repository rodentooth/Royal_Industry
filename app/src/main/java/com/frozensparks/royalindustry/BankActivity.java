package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.InputType;
import android.view.MotionEvent;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.matesnetwork.Cognalys.VerifyMobile;

import org.w3c.dom.Text;

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

    //nrveri
    Button verify;
    EditText phnr;
    EditText coucod;

    Handler h = new Handler();
    int delay = 100; //milliseconds

    ProgressBar diaconvertingtimeprogress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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




        //ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //ad laden
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("16201-16201")
                .build();

        mInterstitialAd.loadAd(adRequest);



        Button closeBank = (Button) findViewById(R.id.closeBank);
        closeBank.setOnClickListener(this);

        GoldToDias = (Button) findViewById(R.id.GoldToDias);
        GoldToDias.setOnClickListener(this);


        currentGoldBalance = (TextView) findViewById(R.id.currentGoldBalance);

        diatext = (TextView) findViewById(R.id.diatext);

        diaconvertingtimeprogress = (ProgressBar) findViewById(R.id.diaconvertingtimeprogress);

        diaconvertingtimeprogress.setMax(30);


        SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);
        String stringGoldmax = String.valueOf(BankMax.getInt("maxGoldStorage", 1000));
        String stringDiamax = String.valueOf(BankMax.getInt("maxDiaStorage", 21));

        //Aktualisiere den Kontostatus
        SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
        String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
        currentGoldBalance.setText(getString(R.string.Gold) + ": " + Pooltext + "/" + stringGoldmax);

        SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
        //Diapool text aktualisieren
        String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
        diatext.setText((getString(R.string.Diamonds) + ": " + diapooltext) + "/" + stringDiamax);


        if ((prefs1.getInt("POOL", 0)) > 20000) {

            maxdias = 100;
        }
        if ((prefs1.getInt("POOL", 0)) <= 20000) {

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
                    //progbar update
                    diaconvertingtimeprogress.setProgress(secondsElapsed);
                }

                if (secondsElapsed >= 1) {
                    if (secondsElapsed < 30) {
                        //converterknopf unsichtbar
                        GoldToDias.setVisibility(View.INVISIBLE);
                        //progbar update
                        diaconvertingtimeprogress.setProgress(secondsElapsed);
                    }
                }

                SharedPreferences prefs4 = getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE);
                Boolean hatsdiaszumholen = (prefs4.getBoolean("trueorfalse", false));

                if (hatsdiaszumholen) {
                    if (secondsElapsed >= 30) {

                        //diamanten dem diamantenpool hinzufügen
                        SharedPreferences thatmanydias = getSharedPreferences("thatmanydias", MODE_PRIVATE);
                        SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = getSharedPreferences("DIAMONDS", MODE_PRIVATE).edit();
                        editor2.putInt("DIAMONDS", (prefs3.getInt("DIAMONDS", 0)) + (thatmanydias.getInt("thatmanydias", 1)));
                        editor2.apply();


                        //Diapool text aktualisieren

                        SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);
                        String stringDiamax = String.valueOf(BankMax.getInt("maxDiaStorage", 1000));

                        String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                        diatext.setText(getString(R.string.Diamonds) + ": " + diapooltext + "/" + stringDiamax);


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


            //if (mobileverification= false){}

            View verification = View.inflate(this, R.layout.verification, null);
            Dialog veridia = new Dialog(v.getContext());
            veridia.requestWindowFeature(Window.FEATURE_NO_TITLE);
            veridia.setContentView(verification);

            coucod = (EditText) veridia.findViewById(R.id.coucod);
            phnr = (EditText) veridia.findViewById(R.id.phnr);
            coucod.setText(VerifyMobile
                    .getCountryCode(getApplicationContext()));

            verify = (Button) veridia.findViewById(R.id.verify);
            verify.setOnClickListener(this);
            String m_Text = coucod.getText().toString()+phnr.getText().toString();

            veridia.show();





            View converter = View.inflate(this, R.layout.converter, null);
            dialogconvertdias = new Dialog(v.getContext());
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


        //    dialogconvertdias.show();


        }
        if (id == R.id.verify) {
          final  String m_Text = coucod.getText().toString()+phnr.getText().toString();

            AlertDialog.Builder nrcheck = new AlertDialog.Builder(context);
            nrcheck.setMessage("is \n"+m_Text+"\n\ncorrect?");


            nrcheck.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent in = new Intent(BankActivity.this, VerifyMobile.class);
                    in.putExtra("app_id", "1648eb2ffc0d4dadbabc4b0");
                    in.putExtra("access_token","a093629e19f74f66936a3b726f5eabb02c3e79ce");
                    in.putExtra("mobile", m_Text);

                    startActivityForResult(in,VerifyMobile.REQUEST_CODE);
                }
            });

            nrcheck.show();
        }

        if (id == R.id.confirmconvert) {





            /*if (mobileverification= true){}




            AlertDialog.Builder werbungcreatedias = new AlertDialog.Builder(
                    context);
            werbungcreatedias.setMessage("a short ad will pop up to confirm the convert");

            werbungcreatedias.setPositiveButton("convert", new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialog, int id) {





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


                                if (prefs1.getInt("thatmanydias", 1) >= 1) {

                                    //CHECKEN OB ES DEN DIASPEICHER nicht überschreitet
                                    SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);
                                    int diastor = BankMax.getInt("maxDiaStorage", 1000);
                                    SharedPreferences DIAMONDS = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
                                    int diainpool = DIAMONDS.getInt("DIAMONDS", 0);
                                    int freespace = diastor - diainpool;

                                    if (prefs1.getInt("thatmanydias", 1) > freespace) {

                                        Toast.makeText(BankActivity.this, "you can only create" + freespace + "diamonds", Toast.LENGTH_SHORT).show();
                                    }

                                    if (prefs1.getInt("thatmanydias", 1) <= freespace) {

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


                                            //es hat dias zu holen=true
                                            SharedPreferences.Editor editor4 = getSharedPreferences("DiamantConvertCountdownOFF", MODE_PRIVATE).edit();
                                            editor4.putBoolean("trueorfalse", true);
                                            editor4.apply();


                                            //dialog beenden
                                            dialogconvertdias.dismiss();

                                        }
                                    }
                                }

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
                                .addTestDevice("04157df49ce5de15")
                                .build();

                        mInterstitialAd.loadAd(adRequest);

                    }

                }
            }
            ).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }

                );

                // create alert dialog
              //  android.app.AlertDialog alertDialog = goldconvertad.create();

                // show it
            werbungcreatedias.show();


           */ }



    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
// TODO Auto-generated method stub
        super.onActivityResult(arg0, arg1, arg2);

        if (arg0 == VerifyMobile.REQUEST_CODE) {
            String message = arg2.getStringExtra("message");
            int result=arg2.getIntExtra("result", 0);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                String restring = Integer.toString(result);

            Toast.makeText(BankActivity.this, restring, Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }
}
