package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.TimeUnit;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {




    //Fabrik1
    Button Fabrik1;
    Button SammelnFabrik1;
    ProgressBar progressBarFabrik1;
    Context context = this;
    SharedPreferences datafab1;
    TextView countfab1;
    TextView leveltextfabr1;
    TextView leveltextdiafab1;


    //Bank
    Button Bank;
    ProgressBar  progressBarUpgradeBank;



    //Aktualisator
    Handler h = new Handler();
    int delay = 100; //milliseconds

    //Bauhaus
    Button bauhaus;
    Dialog bauhausdialog;
    Button closeBauhaus;
    //BauhausFabrik1
    Button upgradefab1;
    ProgressBar  progressBarUpgradefab1;
    TextView upcdfab1;
    //Bauhaus Bank
    Button upgradeBank;
    TextView leveltextBank;
    TextView upcdBank;

    //FULLSCREEN

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    //private static final boolean AUTO_HIDE = true;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
   // private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
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
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;




    //Start

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //schrift umstellen
        FontsOverride.setDefaultFont(this, "DEFAULT", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_BOLD", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_BOLD_ITALIC", "OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_ITALIC", "OldGlyphs.ttf");




        setContentView(R.layout.activity_main);

        mVisible = true;
        mContentView = findViewById(R.id.fullscreen_content);
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });







        //Bank
        Bank = (Button) findViewById(R.id.Bank);
        Bank.setOnClickListener(this);
        progressBarUpgradeBank = (ProgressBar) findViewById(R.id.progressBarUpgradeBank);
        progressBarUpgradeBank.setVisibility(View.INVISIBLE);


        //Bauhaus
        bauhaus = (Button) findViewById(R.id.bauhaus);
        bauhaus.setOnClickListener(this);




        //Fabrik1
        Fabrik1 = (Button) findViewById(R.id.Fabrik1);
        Fabrik1.setOnClickListener(this);
        SammelnFabrik1 = (Button) findViewById(R.id.SammelnFabrik1);
        SammelnFabrik1.setOnClickListener(this);
        progressBarUpgradefab1 = (ProgressBar) findViewById(R.id.progressBarUpgradefab1);
        progressBarUpgradefab1.setVisibility(View.INVISIBLE);

        //counter für fabriken. Hat die fabrik schonmal produziert?
         datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);

        SharedPreferences firstopen = getSharedPreferences("firstopen", MODE_PRIVATE);
        Boolean firstopenboo = (firstopen.getBoolean("firstopen", true));

        //Sachen, nur auf das allererste öffnen ausführen
        if (firstopenboo) {

            //zweite versicherung (damit die fabrick nicht versehentlich auf lvl 1 zurückgesetzt wird)
            if ((datafab1.getInt("Level", 1) == 1)) {
                //erste startzeit speichern
                SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
                editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - 979);
                editor.apply();

                //Fabrik auf lvl 1 setzen
                SharedPreferences.Editor editorfab1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                editorfab1.putInt("maxfabrik1", 500);
                editorfab1.putInt("minfabrik1", 1);
                editorfab1.putFloat("goldphfab1", (float) 0.05);
                editorfab1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level1));
                editorfab1.apply();


                //Bank auf lvl 1
                SharedPreferences.Editor editorBank = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                editorBank.putInt("maxGoldStorage", 1000);
                editorBank.putInt("maxDiaStorage", 20);
                editorBank.putString("leveltext", getString(R.string.bank)+" " + getString(R.string.level1));
                editorBank.apply();



                //firstopen auf falsch stellen
                SharedPreferences.Editor editor2 = getSharedPreferences("firstopen", MODE_PRIVATE).edit();
                editor2.putBoolean("firstopen", false);
                editor2.apply();

            }
        }



        //Aktualisator

        h.postDelayed(new Runnable(){
            public void run(){
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.



                //Gold check Fabrik1
                SharedPreferences prefs = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE);
                SharedPreferences prefs1 = getSharedPreferences("datafab1", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis()/1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * prefs1.getFloat("goldphfab1",(float) 0.05));
                long gold = (long) goldtemp;
                //checken obs soweit ist
                if (gold<= prefs1.getInt("minfabrik1", 1 )) {
                    SammelnFabrik1.setVisibility(View.GONE);
                }
                if(gold>= prefs1.getInt("minfabrik1", 1 )) {
                    SammelnFabrik1.setVisibility(View.VISIBLE);
                }







                    //upgrades


                //Bank
                SharedPreferences dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);
                Boolean cdBank = (dataBank.getBoolean("isLeveling", false));
                if (cdBank) {

                    progressBarUpgradeBank.setVisibility(View.VISIBLE);
                    //startzeit holen
                    SharedPreferences BankupgradeContdown = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE);
                    int startTimeBank = BankupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeBank = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsBank = endTimeBank - startTimeBank;

                    //progressbar updaten
                    progressBarUpgradeBank.setMax(BankupgradeContdown.getInt("countdown", 0));
                    progressBarUpgradeBank.setProgress(elapsedSecondsBank);



                    if (elapsedSecondsBank > BankupgradeContdown.getInt("countdown", 0)) {


                        SharedPreferences.Editor editorBank = getSharedPreferences("dataBank", MODE_PRIVATE).edit();

                        if (dataBank.getInt("Level", 1) == 1) {


                            //definition level2
                            editorBank.putInt("maxGoldStorage", 20000);
                            editorBank.putInt("maxDiaStorage", 100);
                            editorBank.putString("leveltext", getString(R.string.bank)+" " + getString(R.string.level2));
                            editorBank.apply();
                            editorBank.putBoolean("isLeveling", false);
                            cdBank = false;
                            progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                            editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                            editorBank.apply();
                        }

                        if (cdBank) {
                            if (dataBank.getInt("Level", 1) == 2) {


                                //definition level3
                                editorBank.putInt("maxGoldStorage", 75000);
                                editorBank.putInt("maxDiaStorage", 1000);
                                editorBank.putString("leveltext", getString(R.string.bank)+" " + getString(R.string.level3));
                                editorBank.apply();
                                editorBank.putBoolean("isLeveling", false);
                                cdBank = false;
                                progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                                editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                                editorBank.apply();
                            }
                        }

                        if (cdBank) {
                            if (dataBank.getInt("Level", 1) == 3) {


                                //definition level4
                                editorBank.putInt("maxGoldStorage", 150000);
                                editorBank.putInt("maxDiaStorage", 2000);
                                editorBank.putString("leveltext", getString(R.string.bank)+" " + getString(R.string.level4));
                                editorBank.apply();
                                editorBank.putBoolean("isLeveling", false);
                                // bei weiteren lvl aktivieren cdBank = false;
                                progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                                editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                                editorBank.apply();
                            }
                        }




                    }
                }


                    //Fabrik1
                datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);
                    Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
                    if (cdfab1) {

                        progressBarUpgradefab1.setVisibility(View.VISIBLE);
                        SammelnFabrik1.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE);
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab1 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab1 = endTimefab1 - startTimefab1;

                        //progressbar updaten
                        progressBarUpgradefab1.setMax(Fab1upgradeContdown.getInt("countdown", 0));
                        progressBarUpgradefab1.setProgress(elapsedSecondsfab1);

                        //goldprod.während des lvlns stoppen
                        SharedPreferences.Editor stopgold = getSharedPreferences("resultatdersession", MODE_PRIVATE).edit();
                        stopgold.putInt("secondsElapsed", 1);
                        stopgold.apply();

                    if (elapsedSecondsfab1 > Fab1upgradeContdown.getInt("countdown", 0)) {

                        stopgold.putInt("secondsElapsed", 1);
                        stopgold.apply();

                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();

                        if (datafab1.getInt("Level", 1) == 1) {


                            //definition level2
                            editor1.putInt("maxfabrik1", 1000);
                            editor1.putInt("minfabrik1", 2);
                            editor1.putFloat("goldphfab1", (float) 0.1);
                            editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level2));
                            editor1.putBoolean("isLeveling", false);
                            cdfab1 = false;
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                            editor1.apply();
                        }

                        if (cdfab1) {
                            if (datafab1.getInt("Level", 1) == 2) {

                                //LEVEL3
                                editor1.putInt("maxfabrik1", 1500);
                                editor1.putInt("minfabrik1", 3);
                                editor1.putFloat("goldphfab1", (float) 0.16667);
                                editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level3));
                                editor1.putBoolean("isLeveling", false);
                                cdfab1 = false;
                                progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                SammelnFabrik1.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                editor1.apply();

                            }
                        }

                        if (cdfab1) {
                            if (datafab1.getInt("Level", 1) == 3) {

                                //LvL4

                                editor1.putInt("maxfabrik1", 2000);
                                editor1.putInt("minfabrik1", 4);
                                editor1.putFloat("goldphfab1", (float) 0.222222222222);
                                editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level4));
                                editor1.putBoolean("isLeveling", false);
                                cdfab1 = false;
                                progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                SammelnFabrik1.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                editor1.apply();
                            }
                        }

                        if (cdfab1) {
                            if (datafab1.getInt("Level", 1) == 4) {

                                //Lvl5
                                editor1.putInt("maxfabrik1", 5000);
                                editor1.putInt("minfabrik1", 5);
                                editor1.putFloat("goldphfab1", (float) 0.277777777778);
                                editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level5));
                                editor1.putBoolean("isLeveling", false);
                                cdfab1 = false;
                                progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                SammelnFabrik1.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                editor1.apply();
                            }
                        }
                            if (cdfab1) {
                                if (datafab1.getInt("Level", 1) == 5) {

                                    //lvl6
                                    editor1.putInt("maxfabrik1", 10000);
                                    editor1.putInt("minfabrik1", 6);
                                    editor1.putFloat("goldphfab1", (float) 0.3333333333);
                                    editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level6));
                                    editor1.putBoolean("isLeveling", false);
                                    cdfab1 = false;
                                    progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                    SammelnFabrik1.setVisibility(View.VISIBLE);
                                    editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                    editor1.apply();
                                }
                            }
                                if (cdfab1) {
                                    if (datafab1.getInt("Level", 1) == 6) {

                                //lvl7
                                editor1.putInt("maxfabrik1", 15000);
                                editor1.putInt("minfabrik1", 7);
                                editor1.putFloat("goldphfab1", (float) 0.388888888);
                                editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level7));
                                editor1.putBoolean("isLeveling", false);
                                cdfab1 = false;
                                progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                SammelnFabrik1.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab1) {
                        if (datafab1.getInt("Level", 1) == 7) {

                            //lvl8
                            editor1.putInt("maxfabrik1", 20000);
                            editor1.putInt("minfabrik1", 8);
                            editor1.putFloat("goldphfab1", (float) 0.44444444444444);
                            editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level8));
                            editor1.putBoolean("isLeveling", false);
                            cdfab1 = false;
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                            editor1.apply();
                        }
                        }
                        if (cdfab1) {
                        if (datafab1.getInt("Level", 1) == 8) {

                            //lvl9
                            editor1.putInt("maxfabrik1", 25000);
                            editor1.putInt("minfabrik1", 9);
                            editor1.putFloat("goldphfab1", (float) 0.5);
                            editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level9));
                            editor1.putBoolean("isLeveling", false);
                            cdfab1 = false;
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                            editor1.apply();
                        }
                        }
                        if (cdfab1) {
                        if (datafab1.getInt("Level", 1) == 9) {

                            //lvl10
                            editor1.putInt("maxfabrik1", 30000);
                            editor1.putInt("minfabrik1", 10);
                            editor1.putFloat("goldphfab1", (float) 0.5555555555555556);
                            editor1.putString("leveltextfab1", getString(R.string.factory_1)+" " + getString(R.string.level10));
                            editor1.putBoolean("isLeveling", false);
                           // bei weiteren lvl aktivieren cdfab1 = false;
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                            editor1.apply();
                        }
                        }


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
        //FAbrik 1
        datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);
        //Bank
        SharedPreferences dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);


        int id = v.getId();
        if (id == R.id.Fabrik1) {

            dialogFabr1();
        }

        if (id == R.id.SammelnFabrik1) {

            sammelnfab1();
        }

        if (id == R.id.Bank) {
          Intent start = new Intent(MainActivity.this, BankActivity.class);
            MainActivity.this.startActivity(start);

            finish();



        }
        //Bauhaus
        if (id == R.id.bauhaus) {


            View bauhausdia = View.inflate(this, R.layout.bauhausdialog, null);
            bauhausdialog = new Dialog(v.getContext());
            bauhausdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bauhausdialog.setContentView(bauhausdia);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(bauhausdialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;

            closeBauhaus = (Button)bauhausdialog.findViewById(R.id.closeBauhaus);
            closeBauhaus.setOnClickListener(this);


            //Fabrik1
            upgradefab1 = (Button) bauhausdialog.findViewById(R.id.upgradefab1);
            upgradefab1.setOnClickListener(this);
            leveltextfabr1 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr1);
            upcdfab1 = (TextView) bauhausdialog.findViewById(R.id.upcdfab1);
            leveltextfabr1.setText(datafab1.getString("leveltextfab1", "1"));



            //Bank
            upgradeBank = (Button) bauhausdialog.findViewById(R.id.upgradeBank);
            upgradeBank.setOnClickListener(this);
            upcdBank = (TextView) bauhausdialog.findViewById(R.id.upcdBank);
            leveltextBank = (TextView) bauhausdialog.findViewById(R.id.leveltextBank);
            leveltextBank.setText(dataBank.getString("leveltext", "1"));




            bauhausdialog.show();
            bauhausdialog.getWindow().setAttributes(lp);


            //Aktualisator bauhaus

            h.postDelayed(new Runnable(){
                public void run(){
                    //jede sec upgradecountdown aktualisieren

                    //Fabrik1
                    Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));

                        SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE);
                        int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                        int countdownfab1 = Fab1upgradeContdown.getInt("countdown", 0); //0 is the default value.
                        //endzeit als jetzt definieren
                        int endTimefab1 = ((int) System.currentTimeMillis() / 1000);
                        int elapsedSecondsfab1 = endTimefab1 - startTimefab1;
                        int restTimefab1 = countdownfab1 - elapsedSecondsfab1;
                        String dhmsfab1;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab1){
                        upcdfab1.setText("");
                    }
                    if (cdfab1) {


                        if (restTimefab1 >= 0) {
                            dhmsfab1 = String.format("%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab1), TimeUnit.SECONDS.toHours(restTimefab1),
                                    TimeUnit.SECONDS.toMinutes(restTimefab1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimefab1)),
                                    TimeUnit.SECONDS.toSeconds(restTimefab1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimefab1)));
                            upcdfab1.setText(dhmsfab1);
                        }
                        if (restTimefab1 < 0) {
                            upcdfab1.setText("00:00:00:00");
                        }
                    }

                    h.postDelayed(this, delay);
                }
            }, delay);

        }

        if (id == R.id.closeBauhaus) {

            bauhausdialog.dismiss();
        }

        //BANK
        if (id == R.id.upgradeBank) {

            dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);
            Boolean cdbank = dataBank.getBoolean("isLeveling", false);

            //wenn ein upgrade am laufen ist, toast machen
            if (cdbank) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();


            }

            //sonst, upgradedialog
            if (!cdbank) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.bank));

                // set dialog message

                if (dataBank.getInt("Level", 1) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.goldstorage)," 1000",Html.fromHtml(" <font color=#00ff00> + 19000</font>"), "\n\n ",getString(R.string.diastorage)," 20 ",Html.fromHtml("<font color=#00ff00> + 80</font>")," \n \n",getString(R.string.Costs),"100", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (dataBank.getInt("Level", 1) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3),": \n\n", getString(R.string.goldstorage),"20000",Html.fromHtml(" <font color=#00ff00> + 55000</font>"), "\n\n ",getString(R.string.diastorage)," 100 ",Html.fromHtml("<font color=#00ff00> + 900</font>")," \n \n",getString(R.string.Costs),"20000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:30"));
                }
                if (dataBank.getInt("Level", 1) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4),": \n\n", getString(R.string.goldstorage),"75000",Html.fromHtml(" <font color=#00ff00> + 75000</font>"), "\n\n ",getString(R.string.diastorage)," 1000 ",Html.fromHtml("<font color=#00ff00> + 1000</font>")," \n \n",getString(R.string.Costs),"75000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:40"));
                }


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(getString(R.string.upgrade), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {


                                //Upgrade
                                SharedPreferences dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);


                                //Level 2
                                if (dataBank.getInt("Level", 1) == 1) {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 100) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 100) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 100));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO bank lvl 1 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                //Level 3
                                if (dataBank.getInt("Level", 1) == 2) {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO bank lvl 2 secs
                                        editor3.putInt("countdown", 30);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //Level 4
                                if (dataBank.getInt("Level", 1) == 1) {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 750000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 750000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 75000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO bank lvl 4 secs
                                        editor3.putInt("countdown", 40);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }





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


        //FAbrik1
        if (id == R.id.upgradefab1) {


            //ist ein upgrade am laufen?


            datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);
            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();


                //TODO zeit um 10 sec verringern
                SharedPreferences Fab1upgradeContdown = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE);
                int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                editor3.putInt("startTime", startTimefab1 - 10);
                editor3.apply();

            }

            if (!cdfab1) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.factory_1));

                // set dialog message

                if (datafab1.getInt("Level", 1) == 1) {
                    //text lvl2



                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.gold_per_hour)," 180",Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ",getString(R.string.storage)," 500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"50", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));

                }
                if (datafab1.getInt("Level", 1) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3),": \n\n", getString(R.string.gold_per_hour),"360",Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ",getString(R.string.storage)," 1000 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:30"));
                }
                if (datafab1.getInt("Level", 1) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4),": \n\n", getString(R.string.gold_per_hour),"600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 1500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"1500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:40"));
                }
                if (datafab1.getInt("Level", 1) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5),": \n\n", getString(R.string.gold_per_hour),"800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 2000 ",Html.fromHtml("<font color=#00ff00> + 3000</font>")," \n \n",getString(R.string.Costs),"5000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:50"));
                }

                if (datafab1.getInt("Level", 1) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6),": \n\n", getString(R.string.gold_per_hour),"1000",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 5000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"20000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:00"));
                }

                if (datafab1.getInt("Level", 1) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7),": \n\n", getString(R.string.gold_per_hour),"1200",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 10000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"35000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:10"));
                }

                if (datafab1.getInt("Level", 1) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8),": \n\n", getString(R.string.gold_per_hour),"1400",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 15000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"70000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:20"));
                }

                if (datafab1.getInt("Level", 1) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9),": \n\n", getString(R.string.gold_per_hour),"1600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 20000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"110000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:30"));
                }

                if (datafab1.getInt("Level", 1) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10),": \n\n", getString(R.string.gold_per_hour),"1800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 25000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"150000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:40"));
                }


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(getString(R.string.upgrade), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {


                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                if (datafab1.getInt("Level", 1) == 1) {
                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }


                                if (datafab1.getInt("Level", 1) == 2) {

                                    //LEVEL3
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 3 secs
                                        editor3.putInt("countdown", 30);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 3) {

                                    //LEVEL4
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

                                    if ((prefs.getInt("POOL", 0)) <= 1500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 1500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1500));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 4 secs
                                        editor3.putInt("countdown", 40);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 4) {

                                    //LEVEL5
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 5000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 5000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 5000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 5 secs
                                        editor3.putInt("countdown", 50);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 5) {

                                    //LEVEL6
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 6 secs
                                        editor3.putInt("countdown", 60);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 6) {

                                    //LEVEL7
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 35000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 35000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 35000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 7 secs
                                        editor3.putInt("countdown", 70);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 7) {

                                    //LEVEL8
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 70000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 70000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 70000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 8 secs
                                        editor3.putInt("countdown", 80);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 8) {

                                    //LEVEL9
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 110000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 110000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 110000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 9 secs
                                        editor3.putInt("countdown", 90);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 9) {

                                    //LEVEL10
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 150000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 150000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 150000));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        //TODO lvl 10 secs
                                        editor3.putInt("countdown", 100);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                    }
                                }


                        )
                        .

                                setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
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
        }









    //Fabrik1
    public void dialogFabr1(){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik1);





        datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);



        countfab1 = (TextView) dialog.findViewById(R.id.countfab1);
        final TextView GperH = (TextView) dialog.findViewById(R.id.GperH);
        progressBarFabrik1 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik1);
        progressBarFabrik1.setMax(datafab1.getInt("maxfabrik1", 500));
        leveltextdiafab1 = (TextView) dialog.findViewById(R.id.leveltextdiafab1);

        String lvltext = (datafab1.getString("leveltextfab1", "level 1"));
        leveltextdiafab1.setText(lvltext);


        h.postDelayed(new Runnable() {
            public void run() {
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * datafab1.getFloat("goldphfab1", (float) 0.05));
                long gold = (long) goldtemp;
                int goldprogress1 = (int) gold;

                if (goldprogress1 >= datafab1.getInt("maxfabrik1", 500)) {
                    goldprogress1 = datafab1.getInt("maxfabrik1", 500);
                }
                progressBarFabrik1.setProgress(goldprogress1);

                //
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(goldprogress1);
                String strI = sb.toString();
                //maximum storage to string
                StringBuilder maxg = new StringBuilder();
                maxg.append("");
                maxg.append(datafab1.getInt("maxfabrik1", 500));
                String maxgo = maxg.toString();

                countfab1.setText(strI + "/" + maxgo);

              double goldphfab160= datafab1.getFloat("goldphfab1", (float) 0.05)*60*60;

                int goldphfab1int = (int)goldphfab160;

                StringBuilder gph = new StringBuilder();
                gph.append("");
                gph.append(goldphfab1int);
                String goph = gph.toString();
                GperH.setText(getString(R.string.gold_per_hour)+ goph);


                h.postDelayed(this, delay);
            }
        }, delay);




        dialog.show();



    }
   public void sammelnfab1(){






        SharedPreferences prefs = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE);

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.



        int endTime = ((int) System.currentTimeMillis()/1000);

        int secondsElapsed = endTime - startTime;








       //Sekunden in gold mit kommas umwandeln
       double goldtemp = (secondsElapsed * datafab1.getFloat("goldphfab1", (float) 0.05));
       long gold = (long) goldtemp;








       //gold von long zu int
      int goldint = (int)(gold);

       //checken ob das max schon erreicht wurde

       if(goldint>= datafab1.getInt("maxfabrik1", 500)) {

           goldint = datafab1.getInt("maxfabrik1", 500);
       }

        //schauen, ist der pool schon auf maxStorage der bank?
       SharedPreferences BankMax = getSharedPreferences("dataBank", MODE_PRIVATE);
       int intBankmax = BankMax.getInt("maxGoldStorage", 1000);
       SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
       if (intBankmax < (prefs1.getInt("POOL", 0))){

           Toast.makeText(MainActivity.this, getString(R.string.Bankisfull), Toast.LENGTH_SHORT).show();
       }


       if (intBankmax >= (prefs1.getInt("POOL", 0))) {
           //Timer Zurücksetzen
           SharedPreferences.Editor editor1 = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
           editor1.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
           editor1.apply();

           //Das Gold zum pool hinzufügen
           SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
           //TODO 10000 löschen
           editor2.putInt("POOL", 10000 + goldint + (prefs1.getInt("POOL", 0)));
           editor2.apply();

           //rücklauf falls das max erfüllt wurde(nicht vorhanden




           Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.frozensparks.royalindustry/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
