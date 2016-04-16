package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BankActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }

        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */



    int maxdias;
    TextView howmanygoldtodias;
    TextView diasCost;
    TextView currentGoldBalance;
    TextView diatext;
    SeekBar seekbar;
    Button confirmconvert;
    Dialog dialogconvertdias;

    Button GoldToDias;

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

        setContentView(R.layout.activity_bank);

        mVisible = true;

        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            hide();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.closeBank) {
            Intent start = new Intent(BankActivity.this, MainActivity.class);
            BankActivity.this.startActivity(start);
            finish();

        }

        if (id == R.id.GoldToDias) {

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


            dialogconvertdias.show();


        }


        if (id == R.id.confirmconvert) {


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
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Bank Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.frozensparks.royalindustry/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Bank Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.frozensparks.royalindustry/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
