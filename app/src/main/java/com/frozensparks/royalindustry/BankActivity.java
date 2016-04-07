package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BankActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

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
    private View mControlsView;
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
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };



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

        //Aktualisiere den Kontostatus
        SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
        String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
        currentGoldBalance.setText("YOUR CURRENT BALANCE IS: " +Pooltext);

        SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
        //Diapool text aktualisieren
        String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
        diatext.setText("Dias: " + diapooltext);


        if ((prefs1.getInt("POOL", 0)) > 20000 ){

            maxdias = 100;
        }
        if ((prefs1.getInt("POOL", 0)) <= 20000 ){

            maxdias = ((prefs1.getInt("POOL", 0)) /200);
        }





        h.postDelayed(new Runnable(){
            public void run(){
                //jede 0.1 sekunde timer aktualisieren



                    //startzeit holen
                    SharedPreferences DiamantConvertContdown = getSharedPreferences("DiamantConvertContdown", MODE_PRIVATE);
                    int startTime = DiamantConvertContdown.getInt("startTime", 0); //0 is the default value.


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

                SharedPreferences prefs4 = getSharedPreferences("DiamantConvertContdownOFF", MODE_PRIVATE);
                Boolean hatsdiaszumholen = (prefs4.getBoolean("trueorfalse", false));

                if (hatsdiaszumholen) {
                    if (secondsElapsed >= 30) {

                        //diamanten dem diamantenpool hinzufügen
                        SharedPreferences thatmanydias = getSharedPreferences("thatmanydias", MODE_PRIVATE);
                        SharedPreferences prefs3 = getSharedPreferences("DIAMONDS", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = getSharedPreferences("DIAMONDS", MODE_PRIVATE).edit();
                        editor2.putInt("DIAMONDS", (prefs3.getInt("DIAMONDS", 1)) + (thatmanydias.getInt("thatmanydias", 1)));
                        editor2.commit();


                        //Diapool text aktualisieren
                        String diapooltext = String.valueOf(prefs3.getInt("DIAMONDS", 0));
                        diatext.setText("Dias: " + diapooltext);


                        //keine dias mehr zu holen also false
                        SharedPreferences.Editor editor3 = getSharedPreferences("DiamantConvertContdownOFF", MODE_PRIVATE).edit();
                        editor3.putBoolean("trueorfalse", false);
                        editor3.commit();

                        diaconvertingtimeprogress.setProgress(0);
                        //converterknopf sichtbar
                        GoldToDias.setVisibility(View.VISIBLE);

                    }
                }

                h.postDelayed(this, delay);
            }
        }, delay);


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


            if (maxdias >=2){
                seekbar.setMax(maxdias);
            }

            if (maxdias <=1){
                seekbar.setMax(1);
            }
            int progressvalue = 1;
            howmanygoldtodias.setText("CREATE" + progressvalue + "DIAS");
            diasCost.setText("COSTS " + progressvalue * 200 + " GOLD");


            seekbar.setOnSeekBarChangeListener(

                    new SeekBar.OnSeekBarChangeListener() {


                        int progressvalue;

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            progressvalue = progress;
                            howmanygoldtodias.setText("CREATE " + progressvalue + " DIAS");
                            diasCost.setText("COSTS " + progressvalue * 200 + " GOLD");
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                            howmanygoldtodias.setText("CREATE " + progressvalue + " DIAS");
                            diasCost.setText("COSTS " + progressvalue * 200 + " GOLD");

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            howmanygoldtodias.setText("CREATE " + progressvalue + " DIAS");
                            diasCost.setText("COSTS " + progressvalue * 200 + " GOLD");

                            //den progressvalue (wieviele dias) speichern
                            SharedPreferences.Editor editor1 = getSharedPreferences("thatmanydias", MODE_PRIVATE).edit();
                            editor1.putInt("thatmanydias", progressvalue);
                            editor1.commit();


                        }
                    }

            );


            dialogconvertdias.show();


        }


        if (id == R.id.confirmconvert) {



            //holen von wieviele diamanten erstellen
            SharedPreferences prefs1 = getSharedPreferences("thatmanydias", MODE_PRIVATE);
            //Holen von Pool
            SharedPreferences prefs2 = getSharedPreferences("POOL", MODE_PRIVATE);

            //diamantenanzahl in gold umwandeln
            int goldweg = (prefs1.getInt("thatmanydias", 1))*200;


            //checken obs genug gold für den convert hat
            if (goldweg >= prefs2.getInt("POOL", 0)){

                Toast.makeText(BankActivity.this, "you can not afford that", Toast.LENGTH_SHORT).show();


            }

            if (goldweg <= prefs2.getInt("POOL", 0)){





            //Pool aktualisieren, gold abziehen
            SharedPreferences.Editor editor1 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
            editor1.putInt("POOL", (prefs2.getInt("POOL", 0)) - goldweg);
            editor1.commit();

            //Pooltext aktualisieren
            String Pooltext = String.valueOf(prefs2.getInt("POOL", 0));
            currentGoldBalance.setText("GOLD: " + Pooltext);


                //Timer Starten
                SharedPreferences.Editor editor3 = getSharedPreferences("DiamantConvertContdown", MODE_PRIVATE).edit();
                editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
                editor3.commit();


                //es hat dias zu holen=true
                SharedPreferences.Editor editor4 = getSharedPreferences("DiamantConvertContdownOFF", MODE_PRIVATE).edit();
                editor4.putBoolean("trueorfalse", true);
                editor4.commit();




            //dialog beenden
            dialogconvertdias.dismiss();
            }

        }
    }
}
