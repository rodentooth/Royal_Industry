package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.*;
//import com.google.example.games.basegameutils.BaseGameUtils;



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity  implements View.OnClickListener {






    //Fabrik1
    Button Fabrik1;
    Button SammelnFabrik1;
    ProgressBar progressBarFabrik1;
    Context context = this;
    SharedPreferences datafab1;
    TextView countfab1;
    TextView leveltextfabr1;
    TextView leveltextdiafab1;

    //Fabrik2
    Button Fabrik2;
    Button SammelnFabrik2;
    ProgressBar progressBarFabrik2;
    SharedPreferences datafab2;
    TextView countfab2;
    TextView leveltextfabr2;
    TextView leveltextdiafab2;

    //Fabrik3
    Button Fabrik3;
    Button SammelnFabrik3;
    ProgressBar progressBarFabrik3;
    SharedPreferences datafab3;
    TextView countfab3;
    TextView leveltextfabr3;
    TextView leveltextdiafab3;

    //Fabrik4
    Button Fabrik4;
    Button SammelnFabrik4;
    ProgressBar progressBarFabrik4;
    SharedPreferences datafab4;
    TextView countfab4;
    TextView leveltextfabr4;
    TextView leveltextdiafab4;

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
    //BauhausFabrik2
    Button upgradefab2;
    ProgressBar  progressBarUpgradefab2;
    TextView upcdfab2;
    //BauhausFabrik3
    Button upgradefab3;
    ProgressBar  progressBarUpgradefab3;
    TextView upcdfab3;
    //BauhausFabrik4
    Button upgradefab4;
    ProgressBar  progressBarUpgradefab4;
    TextView upcdfab4;
    //Bauhaus Bank
    Button upgradeBank;
    TextView leveltextBank;
    TextView upcdBank;

    String uporbld;


    //werkstatt
    Button werkstatt;



    //FULLSCREEN


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //Google sign in




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


        werkstatt = (Button) findViewById(R.id.werkstatt);
        werkstatt.setOnClickListener(this);


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

        //Fabrik2
        Fabrik2 = (Button) findViewById(R.id.Fabrik2);
        Fabrik2.setOnClickListener(this);
        SammelnFabrik2 = (Button) findViewById(R.id.SammelnFabrik2);
        SammelnFabrik2.setOnClickListener(this);
        progressBarUpgradefab2 = (ProgressBar) findViewById(R.id.progressBarUpgradefab2);
        if (progressBarUpgradefab2 != null) {
            progressBarUpgradefab2.setVisibility(View.INVISIBLE);
        }

        //Fabrik3
        Fabrik3 = (Button) findViewById(R.id.Fabrik3);
        Fabrik3.setOnClickListener(this);
        SammelnFabrik3 = (Button) findViewById(R.id.SammelnFabrik3);
        SammelnFabrik3.setOnClickListener(this);
        progressBarUpgradefab3 = (ProgressBar) findViewById(R.id.progressBarUpgradefab3);
        progressBarUpgradefab3.setVisibility(View.INVISIBLE);

        //Fabrik2
        Fabrik4 = (Button) findViewById(R.id.Fabrik4);
        Fabrik4.setOnClickListener(this);
        SammelnFabrik4 = (Button) findViewById(R.id.SammelnFabrik4);
        SammelnFabrik4.setOnClickListener(this);
        progressBarUpgradefab4 = (ProgressBar) findViewById(R.id.progressBarUpgradefab4);
        progressBarUpgradefab4.setVisibility(View.INVISIBLE);



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

                //Fabrik 1 auf lvl 1 setzen
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

                //Fabrik 2 auf lvl 0 setzen
                SharedPreferences.Editor editorfab2 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                editorfab2.putInt("maxfabrik2", 0);
                editorfab2.putInt("minfabrik2", 1);
                editorfab2.putFloat("goldphfab2", (float) 0);
                editorfab2.putString("leveltextfab2", getString(R.string.factory_2)+" " );
                Fabrik2.setVisibility(View.INVISIBLE);
                editorfab2.apply();

                //Fabrik 3 auf lvl 0 setzen
                SharedPreferences.Editor editorfab3 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                editorfab3.putInt("maxfabrik3", 0);
                editorfab3.putInt("minfabrik3", 1);
                editorfab3.putFloat("goldphfab3", (float) 0);
                editorfab3.putString("leveltextfab3", getString(R.string.factory_3)+" " );
                Fabrik3.setVisibility(View.INVISIBLE);
                editorfab3.apply();

                //Fabrik 4 auf lvl 0 setzen
                SharedPreferences.Editor editorfab4 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                editorfab4.putInt("maxfabrik4", 0);
                editorfab4.putInt("minfabrik4", 1);
                editorfab4.putFloat("goldphfab4", (float) 0);
                editorfab4.putString("leveltextfab4", getString(R.string.factory_4)+" " );
                Fabrik4.setVisibility(View.INVISIBLE);
                editorfab4.apply();




                //firstopen auf falsch stellen
                SharedPreferences.Editor editor2 = getSharedPreferences("firstopen", MODE_PRIVATE).edit();
                editor2.putBoolean("firstopen", false);
                editor2.apply();






            }
        }



        //Aktualisator

        h.postDelayed(new Runnable(){
            public void run(){


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


                datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
                datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
                datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
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

                //Gold check Fabrik2
                SharedPreferences prefsfab2 = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE);
                SharedPreferences prefs1fab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
                int startTimefab2 = prefsfab2.getInt("startTime", 0); //0 is the default value.

                int endTimefab2 = ((int) System.currentTimeMillis()/1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab2 = endTimefab2 - startTimefab2;
                //einheiten umwandeln
                double goldtempfab2 = (secondsElapsedfab2 * prefs1fab2.getFloat("goldphfab2",(float) 0.05));
                long goldfab2 = (long) goldtempfab2;
                //checken obs soweit ist
                if (goldfab2<= prefs1fab2.getInt("minfabrik2", 1 )) {
                    SammelnFabrik2.setVisibility(View.GONE);
                }
                if(goldfab2>= prefs1fab2.getInt("minfabrik2", 1 )) {
                    SammelnFabrik2.setVisibility(View.VISIBLE);
                }
                if (datafab2.getInt("Level", 0) == 0) {
                    Fabrik2.setVisibility(View.INVISIBLE);
                }
                if (datafab2.getInt("Level", 0) >= 1) {
                    Fabrik2.setVisibility(View.VISIBLE);
                }

                //Gold check Fabrik3
                SharedPreferences prefsfab3 = getSharedPreferences("speichervonstartzeit3", MODE_PRIVATE);
                SharedPreferences prefs1fab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
                int startTimefab3 = prefsfab3.getInt("startTime", 0); //0 is the default value.

                int endTimefab3 = ((int) System.currentTimeMillis()/1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab3 = endTimefab3 - startTimefab3;
                //einheiten umwandeln
                double goldtempfab3 = (secondsElapsedfab3 * prefs1fab3.getFloat("goldphfab3",(float) 0.05));
                long goldfab3 = (long) goldtempfab3;
                //checken obs soweit ist
                if (goldfab3<= prefs1fab3.getInt("minfabrik3", 1 )) {
                    SammelnFabrik3.setVisibility(View.GONE);
                }
                if(goldfab3>= prefs1fab3.getInt("minfabrik3", 1 )) {
                    SammelnFabrik3.setVisibility(View.VISIBLE);
                }
                if (datafab3.getInt("Level", 0) == 0) {
                    Fabrik3.setVisibility(View.INVISIBLE);
                }

                if (datafab3.getInt("Level", 0) >= 1) {
                    Fabrik3.setVisibility(View.VISIBLE);
                }


                //Gold check Fabrik4
                SharedPreferences prefsfab4 = getSharedPreferences("speichervonstartzeit4", MODE_PRIVATE);
                SharedPreferences prefs1fab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
                int startTimefab4 = prefsfab4.getInt("startTime", 0); //0 is the default value.

                int endTimefab4 = ((int) System.currentTimeMillis()/1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab4 = endTimefab4 - startTimefab4;
                //einheiten umwandeln
                double goldtempfab4 = (secondsElapsedfab4 * prefs1fab4.getFloat("goldphfab4",(float) 0.05));
                long goldfab4 = (long) goldtempfab4;
                //checken obs soweit ist
                if (goldfab4<= prefs1fab4.getInt("minfabrik4", 1 )) {
                    SammelnFabrik4.setVisibility(View.GONE);
                }
                if(goldfab4>= prefs1fab4.getInt("minfabrik4", 1 )) {
                    SammelnFabrik4.setVisibility(View.VISIBLE);
                }
                if (datafab4.getInt("Level", 0) == 0) {
                    Fabrik4.setVisibility(View.INVISIBLE);
                }
                if (datafab4.getInt("Level", 0) >= 1) {
                    Fabrik4.setVisibility(View.VISIBLE);
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


                         dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);

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

                            //gebäude auf Bank level 2 freischalten:

                            //Fabrik 1 level3

                            SharedPreferences.Editor Levelbedingungfab1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                            Levelbedingungfab1.putBoolean("bedingungenerfüllt", true);
                            Levelbedingungfab1.apply();

                            //Fabrik 2

                            SharedPreferences.Editor Levelbedingungfab2 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                            Levelbedingungfab2.putBoolean("bedingungenerfüllt", true);
                            Levelbedingungfab2.apply();

                            //Agentur


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

                                //gebäude auf Bank level 3 freischalten:
                                //fabrik3
                                SharedPreferences.Editor Levelbedingungfab3 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                Levelbedingungfab3.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab3.apply();

                                //Fabriken auf lvl 7
                                SharedPreferences.Editor Levelbedingungfab1 = getSharedPreferences("datafab1", MODE_PRIVATE).edit();
                                Levelbedingungfab1.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab1.apply();


                                SharedPreferences.Editor Levelbedingungfab2 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                Levelbedingungfab2.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab2.apply();

                                //Agentur lvl2
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

                                //gebäude auf Bank level 4 freischalten:
                                //fabrik4
                                SharedPreferences.Editor Levelbedingungfab4 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                Levelbedingungfab4.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab4.apply();

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

                        SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
                        editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                        editor.apply();

                    if (elapsedSecondsfab1 > Fab1upgradeContdown.getInt("countdown", 0)) {



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

                            //gebäude auf lvl 2 freischalten:

                            //bank lvl 2
                            SharedPreferences.Editor LevelbedingungBank = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                            LevelbedingungBank.putBoolean("bedingungenerfüllt", true);
                            LevelbedingungBank.apply();

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


                                    //bedingung 1 für bank lvl 3 ok
                                    SharedPreferences.Editor LevelbedingungBank = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                                    LevelbedingungBank.putBoolean("bedingungenerfüllt", true);
                                    LevelbedingungBank.apply();


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

                //Fabrik2
                datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
                Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
                if (cdfab2) {

                    progressBarUpgradefab2.setVisibility(View.VISIBLE);
                    SammelnFabrik2.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    SharedPreferences Fab2upgradeContdown = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE);
                    int startTimeupgradefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab2 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab2 = endTimeupgradefab2 - startTimeupgradefab2;

                    //progressbar updaten
                    progressBarUpgradefab2.setMax(Fab2upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab2.setProgress(elapsedSecondsfab2);

                    //goldprod.während des lvlns stoppen

                    SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE).edit();
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab2 > Fab2upgradeContdown.getInt("countdown", 0)) {



                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();

                        if (datafab2.getInt("Level", 0) == 0) {

                            editor1.putInt("maxfabrik2", 500);
                            editor1.putInt("minfabrik2", 1);
                            editor1.putFloat("goldphfab2", (float) 0.05);
                            editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level1));
                            editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                            editor1.putBoolean("isLeveling", false);
                            editor1.apply();
                            cdfab2 = false;
                            progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                            SammelnFabrik2.setVisibility(View.VISIBLE);
                            //fabrik 2 sichtbar machen
                            Fabrik2.setVisibility(View.VISIBLE);

                            //Startzeit als jetzt definieren
                            SharedPreferences.Editor editor3 = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE).edit();
                            editor3.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) );
                            editor3.apply();

                        }
                        if (cdfab2) {

                            if (datafab2.getInt("Level", 0) == 1) {


                                //definition level2
                                editor1.putInt("maxfabrik2", 1000);
                                editor1.putInt("minfabrik2", 2);
                                editor1.putFloat("goldphfab2", (float) 0.1);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level2));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();


                            }
                        }

                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 2) {

                                //LEVEL3
                                editor1.putInt("maxfabrik2", 1500);
                                editor1.putInt("minfabrik2", 3);
                                editor1.putFloat("goldphfab2", (float) 0.16667);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level3));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();

                            }
                        }

                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 3) {

                                //LvL4

                                editor1.putInt("maxfabrik2", 2000);
                                editor1.putInt("minfabrik2", 4);
                                editor1.putFloat("goldphfab2", (float) 0.222222222222);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level4));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }

                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 4) {

                                //Lvl5
                                editor1.putInt("maxfabrik2", 5000);
                                editor1.putInt("minfabrik2", 5);
                                editor1.putFloat("goldphfab2", (float) 0.277777777778);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level5));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 5) {

                                //lvl6
                                editor1.putInt("maxfabrik2", 10000);
                                editor1.putInt("minfabrik2", 6);
                                editor1.putFloat("goldphfab2", (float) 0.3333333333);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level6));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();


                                //bedingung 2 für bank lvl 3 ok
                                SharedPreferences.Editor LevelbedingungBank = getSharedPreferences("dataBank", MODE_PRIVATE).edit();
                                LevelbedingungBank.putBoolean("bedingungen2erfüllt", true);
                                LevelbedingungBank.apply();

                            }
                        }
                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 6) {

                                //lvl7
                                editor1.putInt("maxfabrik2", 15000);
                                editor1.putInt("minfabrik2", 7);
                                editor1.putFloat("goldphfab2", (float) 0.388888888);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level7));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 7) {

                                //lvl8
                                editor1.putInt("maxfabrik2", 20000);
                                editor1.putInt("minfabrik2", 8);
                                editor1.putFloat("goldphfab2", (float) 0.44444444444444);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level8));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 8) {

                                //lvl9
                                editor1.putInt("maxfabrik2", 25000);
                                editor1.putInt("minfabrik2", 9);
                                editor1.putFloat("goldphfab2", (float) 0.5);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level9));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab2) {
                            if (datafab2.getInt("Level", 0) == 9) {

                                //lvl10
                                editor1.putInt("maxfabrik2", 30000);
                                editor1.putInt("minfabrik2", 10);
                                editor1.putFloat("goldphfab2", (float) 0.5555555555555556);
                                editor1.putString("leveltextfab2", getString(R.string.factory_2)+" " + getString(R.string.level10));
                                editor1.putBoolean("isLeveling", false);
                                // bei weiteren lvl aktivieren cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }


                    }
                }

                //Fabrik3
                datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
                Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
                if (cdfab3) {

                    progressBarUpgradefab3.setVisibility(View.VISIBLE);
                    SammelnFabrik3.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    SharedPreferences Fab3upgradeContdown = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE);
                    int startTimeupgradefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab3 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab3 = endTimeupgradefab3 - startTimeupgradefab3;

                    //progressbar updaten
                    progressBarUpgradefab3.setMax(Fab3upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab3.setProgress(elapsedSecondsfab3);

                    //goldprod.während des lvlns stoppen

                    SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeitfab3", MODE_PRIVATE).edit();
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab3 > Fab3upgradeContdown.getInt("countdown", 0)) {



                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();

                        if (datafab3.getInt("Level", 0) == 0) {
                            //Level1
                            editor1.putInt("maxfabrik3", 500);
                            editor1.putInt("minfabrik3", 1);
                            editor1.putFloat("goldphfab3", (float) 0.05);
                            editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level1));
                            editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                            editor1.putBoolean("isLeveling", false);
                            editor1.apply();
                            cdfab3 = false;
                            progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                            //fabrik 3 sichtbar machen
                            Fabrik3.setVisibility(View.VISIBLE);
                            //Startzeit als jetzt definieren
                            SharedPreferences.Editor editor3 = getSharedPreferences("speichervonstartzeitfab3", MODE_PRIVATE).edit();
                            editor3.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                            editor3.apply();

                        }
                        if (cdfab3) {

                            if (datafab3.getInt("Level", 0) == 1) {

                                //definition level2
                                editor1.putInt("maxfabrik3", 1000);
                                editor1.putInt("minfabrik3", 2);
                                editor1.putFloat("goldphfab3", (float) 0.1);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level2));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();


                            }
                        }

                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 2) {

                                //LEVEL3
                                editor1.putInt("maxfabrik3", 1500);
                                editor1.putInt("minfabrik3", 3);
                                editor1.putFloat("goldphfab3", (float) 0.16667);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level3));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();

                            }
                        }

                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 3) {

                                //LvL4

                                editor1.putInt("maxfabrik3", 2000);
                                editor1.putInt("minfabrik3", 4);
                                editor1.putFloat("goldphfab3", (float) 0.222222222222);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level4));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }

                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 4) {

                                //Lvl5
                                editor1.putInt("maxfabrik3", 5000);
                                editor1.putInt("minfabrik3", 5);
                                editor1.putFloat("goldphfab3", (float) 0.277777777778);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level5));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 5) {

                                //lvl6
                                editor1.putInt("maxfabrik3", 10000);
                                editor1.putInt("minfabrik3", 6);
                                editor1.putFloat("goldphfab3", (float) 0.3333333333);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level6));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 6) {

                                //lvl7
                                editor1.putInt("maxfabrik3", 15000);
                                editor1.putInt("minfabrik3", 7);
                                editor1.putFloat("goldphfab3", (float) 0.388888888);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level7));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 7) {

                                //lvl8
                                editor1.putInt("maxfabrik3", 20000);
                                editor1.putInt("minfabrik3", 8);
                                editor1.putFloat("goldphfab3", (float) 0.44444444444444);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level8));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 8) {

                                //lvl9
                                editor1.putInt("maxfabrik3", 25000);
                                editor1.putInt("minfabrik3", 9);
                                editor1.putFloat("goldphfab3", (float) 0.5);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level9));
                                editor1.putBoolean("isLeveling", false);
                                cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab3) {
                            if (datafab3.getInt("Level", 0) == 9) {

                                //lvl10
                                editor1.putInt("maxfabrik3", 30000);
                                editor1.putInt("minfabrik3", 10);
                                editor1.putFloat("goldphfab3", (float) 0.5555555555555556);
                                editor1.putString("leveltextfab3", getString(R.string.factory_3)+" " + getString(R.string.level10));
                                editor1.putBoolean("isLeveling", false);
                                // bei weiteren lvl aktivieren cdfab3 = false;
                                progressBarUpgradefab3.setVisibility(View.INVISIBLE);
                                SammelnFabrik3.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }


                    }
                }

                //Fabrik4
                datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
                Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
                if (cdfab4) {

                    progressBarUpgradefab4.setVisibility(View.VISIBLE);
                    SammelnFabrik4.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    SharedPreferences Fab4upgradeContdown = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE);
                    int startTimeupgradefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab4 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab4 = endTimeupgradefab4 - startTimeupgradefab4;

                    //progressbar updaten
                    progressBarUpgradefab4.setMax(Fab4upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab4.setProgress(elapsedSecondsfab4);

                    //goldprod.während des lvlns stoppen

                    SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeitfab4", MODE_PRIVATE).edit();
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab4 > Fab4upgradeContdown.getInt("countdown", 0)) {



                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();

                        if (datafab4.getInt("Level", 0) == 0) {

                            editor1.putInt("maxfabrik4", 500);
                            editor1.putInt("minfabrik4", 1);
                            editor1.putFloat("goldphfab4", (float) 0.05);
                            editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level1));
                            editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                            editor1.putBoolean("isLeveling", false);
                            editor1.apply();
                            cdfab4 = false;
                            progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                            //fabrik 4 sichtbar machen
                            Fabrik4.setVisibility(View.VISIBLE);

                            //Startzeit als jetzt definieren
                            SharedPreferences.Editor editor4 = getSharedPreferences("speichervonstartzeitfab4", MODE_PRIVATE).edit();
                            editor4.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                            editor4.apply();

                        }
                        if (cdfab4) {

                        if (datafab4.getInt("Level", 0) == 1) {


                            //definition level2
                            editor1.putInt("maxfabrik4", 1000);
                            editor1.putInt("minfabrik4", 2);
                            editor1.putFloat("goldphfab4", (float) 0.1);
                            editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level2));
                            editor1.putBoolean("isLeveling", false);
                            cdfab4 = false;
                            progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                            SammelnFabrik4.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                            editor1.apply();

                        }

                        }

                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 2) {

                                //LEVEL3
                                editor1.putInt("maxfabrik4", 1500);
                                editor1.putInt("minfabrik4", 3);
                                editor1.putFloat("goldphfab4", (float) 0.16667);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level3));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();

                            }
                        }

                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 3) {

                                //LvL4

                                editor1.putInt("maxfabrik4", 2000);
                                editor1.putInt("minfabrik4", 4);
                                editor1.putFloat("goldphfab4", (float) 0.222222222222);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level4));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }

                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 4) {

                                //Lvl5
                                editor1.putInt("maxfabrik4", 5000);
                                editor1.putInt("minfabrik4", 5);
                                editor1.putFloat("goldphfab4", (float) 0.277777777778);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level5));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 5) {

                                //lvl6
                                editor1.putInt("maxfabrik4", 10000);
                                editor1.putInt("minfabrik4", 6);
                                editor1.putFloat("goldphfab4", (float) 0.3333333333);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level6));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 6) {

                                //lvl7
                                editor1.putInt("maxfabrik4", 15000);
                                editor1.putInt("minfabrik4", 7);
                                editor1.putFloat("goldphfab4", (float) 0.388888888);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level7));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab3.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 7) {

                                //lvl8
                                editor1.putInt("maxfabrik4", 20000);
                                editor1.putInt("minfabrik4", 8);
                                editor1.putFloat("goldphfab4", (float) 0.44444444444444);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level8));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 8) {

                                //lvl9
                                editor1.putInt("maxfabrik4", 25000);
                                editor1.putInt("minfabrik4", 9);
                                editor1.putFloat("goldphfab4", (float) 0.5);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level9));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 9) {

                                //lvl10
                                editor1.putInt("maxfabrik4", 30000);
                                editor1.putInt("minfabrik4", 10);
                                editor1.putFloat("goldphfab4", (float) 0.5555555555555556);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4)+" " + getString(R.string.level10));
                                editor1.putBoolean("isLeveling", false);
                                // bei weiteren lvl aktivieren cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
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

    }
    @Override
    public void onClick(View v) {



        //FAbrik 1
        datafab1 = getSharedPreferences("datafab1", MODE_PRIVATE);



        int id = v.getId();
        if (id == R.id.Fabrik1) {

            dialogFabr1();
        }

        if (id == R.id.SammelnFabrik1) {

            sammelnfab1();
        }


        //FAbrik 2
        datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);

        if (id == R.id.Fabrik2) {

            dialogFabr2();
        }

        if (id == R.id.SammelnFabrik2) {

            sammelnfab2();
        }

        //FAbrik 3
        datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);

        if (id == R.id.Fabrik3) {

            dialogFabr3();
        }

        if (id == R.id.SammelnFabrik3) {

            sammelnfab3();
        }

        //FAbrik 4
        datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);

        if (id == R.id.Fabrik4) {

            dialogFabr4();
        }

        if (id == R.id.SammelnFabrik4) {

            sammelnfab4();
        }




        //Bank
        SharedPreferences dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);
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


            //Fabrik2
            upgradefab2 = (Button) bauhausdialog.findViewById(R.id.upgradefab2);
            upgradefab2.setOnClickListener(this);
            leveltextfabr2 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr2);
            upcdfab2 = (TextView) bauhausdialog.findViewById(R.id.upcdfab2);
            leveltextfabr2.setText(datafab2.getString("leveltextfab2", "2"));
            datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab2.getInt("Level", 0) >= 1){

                    upgradefab2.setBackgroundResource(R.drawable.levelupbtns);

               }
            if (datafab2.getInt("Level", 0) < 1) {
                if (datafab2.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab2.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab2.getBoolean("bedingungenerfüllt", false)){

                    upgradefab2.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }

            //Fabrik3
            upgradefab3 = (Button) bauhausdialog.findViewById(R.id.upgradefab3);
            upgradefab3.setOnClickListener(this);
            leveltextfabr3 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr3);
            upcdfab3 = (TextView) bauhausdialog.findViewById(R.id.upcdfab3);
            leveltextfabr3.setText(datafab3.getString("leveltextfab3", "3"));
            datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab3.getInt("Level", 0) >= 1){

                upgradefab3.setBackgroundResource(R.drawable.levelupbtns);

            }
            if (datafab3.getInt("Level", 0) < 1) {
                if (datafab3.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab3.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab3.getBoolean("bedingungenerfüllt", false)){

                    upgradefab3.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }

            //Fabrik4
            upgradefab4 = (Button) bauhausdialog.findViewById(R.id.upgradefab4);
            upgradefab4.setOnClickListener(this);
            leveltextfabr4 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr4);
            upcdfab4 = (TextView) bauhausdialog.findViewById(R.id.upcdfab4);
            leveltextfabr4.setText(datafab4.getString("leveltextfab4", "4"));
            datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab4.getInt("Level", 0) >= 1){

                upgradefab4.setBackgroundResource(R.drawable.levelupbtns);

            }
            if (datafab4.getInt("Level", 0) < 1) {
                if (datafab4.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab4.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab4.getBoolean("bedingungenerfüllt", false)){

                    upgradefab4.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }


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


                    //BAnk
                    SharedPreferences dataBank = getSharedPreferences("dataBank", MODE_PRIVATE);
                    Boolean cdBank = (dataBank.getBoolean("isLeveling", false));

                    SharedPreferences BankupgradeContdown = getSharedPreferences("startTimeUpgradeBank", MODE_PRIVATE);
                    int startTimeBank = BankupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownBank = BankupgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeBank = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsBank = endTimeBank - startTimeBank;
                    int restTimeBank = countdownBank - elapsedSecondsBank;
                    String dhmsBank;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdBank){
                        upcdBank.setText("");
                    }
                    if (cdBank) {

                        if (restTimeBank >= 0) {
                            dhmsBank = String.format(Locale.US,"%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimeBank), TimeUnit.SECONDS.toHours(restTimeBank),
                                    TimeUnit.SECONDS.toMinutes(restTimeBank) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimeBank)),
                                    TimeUnit.SECONDS.toSeconds(restTimeBank) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimeBank)));
                            upcdBank.setText(dhmsBank);
                        }
                        if (restTimeBank < 0) {
                            upcdBank.setText("00:00:00:00");
                        }
                    }


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
                            dhmsfab1 = String.format(Locale.US,"%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab1), TimeUnit.SECONDS.toHours(restTimefab1),
                                    TimeUnit.SECONDS.toMinutes(restTimefab1) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimefab1)),
                                    TimeUnit.SECONDS.toSeconds(restTimefab1) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimefab1)));
                            upcdfab1.setText(dhmsfab1);
                        }
                        if (restTimefab1 < 0) {
                            upcdfab1.setText("00:00:00:00");
                        }
                    }
                    //Fabrik2
                    Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));

                    SharedPreferences Fab2upgradeContdown = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE);
                    int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab2 = Fab2upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab2 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab2 = endTimefab2 - startTimefab2;
                    int restTimefab2 = countdownfab2 - elapsedSecondsfab2;
                    String dhmsfab2;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab2){
                        upcdfab2.setText("");
                    }
                    if (cdfab2) {

                        if (restTimefab2 >= 0) {
                            dhmsfab2 = String.format(Locale.US,"%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab2), TimeUnit.SECONDS.toHours(restTimefab2),
                                    TimeUnit.SECONDS.toMinutes(restTimefab2) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimefab2)),
                                    TimeUnit.SECONDS.toSeconds(restTimefab2) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimefab2)));
                            upcdfab2.setText(dhmsfab2);
                        }
                        if (restTimefab2 < 0) {
                            upcdfab2.setText("00:00:00:00");
                        }
                    }
                    //Fabrik3
                    Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));

                    SharedPreferences Fab3upgradeContdown = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE);
                    int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab3 = Fab3upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab3 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab3 = endTimefab3 - startTimefab3;
                    int restTimefab3 = countdownfab3 - elapsedSecondsfab3;
                    String dhmsfab3;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab3){
                        upcdfab3.setText("");
                    }
                    if (cdfab3) {

                        if (restTimefab3 >= 0) {
                            dhmsfab3 = String.format(Locale.US,"%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab3), TimeUnit.SECONDS.toHours(restTimefab3),
                                    TimeUnit.SECONDS.toMinutes(restTimefab3) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimefab3)),
                                    TimeUnit.SECONDS.toSeconds(restTimefab3) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimefab3)));
                            upcdfab3.setText(dhmsfab3);
                        }
                        if (restTimefab3 < 0) {
                            upcdfab3.setText("00:00:00:00");
                        }
                    }

                    //Fabrik4
                    Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));

                    SharedPreferences Fab4upgradeContdown = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE);
                    int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab4 = Fab4upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab4 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab4 = endTimefab4 - startTimefab4;
                    int restTimefab4 = countdownfab4 - elapsedSecondsfab4;
                    String dhmsfab4;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab4){
                        upcdfab4.setText("");
                    }
                    if (cdfab4) {

                        if (restTimefab4 >= 0) {
                            dhmsfab4 = String.format(Locale.US,"%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab4), TimeUnit.SECONDS.toHours(restTimefab4),
                                    TimeUnit.SECONDS.toMinutes(restTimefab4) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimefab4)),
                                    TimeUnit.SECONDS.toSeconds(restTimefab4) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimefab4)));
                            upcdfab4.setText(dhmsfab4);
                        }
                        if (restTimefab4 < 0) {
                            upcdfab4.setText("00:00:00:00");
                        }
                    }


                    h.postDelayed(this, delay);
                }
            }, delay);

        }

        if (id == R.id.closeBauhaus) {

            bauhausdialog.dismiss();
        }

//SYNC
if (id== R.id.werkstatt){

    Intent intent = new Intent(this, multiplayer.class);
    startActivity(intent);
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
                    //text bank lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.goldstorage)," 1000",Html.fromHtml(" <font color=#00ff00> + 19000</font>"), "\n\n ",getString(R.string.diastorage)," 20 ",Html.fromHtml("<font color=#00ff00> + 80</font>")," \n \n", getString(R.string.unlock)," \n \n",  getString(R.string.factories)," ", getString(R.string.level3),",",getString(R.string.factory_2),", ", getString(R.string.agency)," \n \n",getString(R.string.Costs),"100", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
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

                                Boolean bederfüllt = dataBank.getBoolean("bedingungenerfüllt", false);
                                Boolean bed2erfüllt = dataBank.getBoolean("bedingungen2erfüllt", false);


                                //Level 2
                                if (dataBank.getInt("Level", 1) == 1) {

                                    //bedingung erfüllt?
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.factory_1)+getString(R.string.zu)+getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

                                        if ((prefs.getInt("POOL", 0)) < 100) {
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
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }


                                }


                                //Level 3
                                if (dataBank.getInt("Level", 1) == 2) {

                                    //bedingung erfüllt?
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.factories)+getString(R.string.zu)+getString(R.string.level6), Toast.LENGTH_SHORT).show();
                                    }
                                    if (!bed2erfüllt) {
                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.factories)+getString(R.string.zu)+getString(R.string.level6), Toast.LENGTH_SHORT).show();

                                    }
                                    if (bederfüllt) {

                                        if (bed2erfüllt) {

                                        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                        if ((prefs.getInt("POOL", 0)) < 20000) {
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
                                            editor1.putBoolean("bedingungen2erfüllt", false);
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                        }
                                    }

                                }

                                //Level 4
                                if (dataBank.getInt("Level", 1) == 3) {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 75000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 75000) {
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



                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.gold_per_hour)," 180",Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ",getString(R.string.storage)," 500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n", getString(R.string.unlock)," \n \n",getString(R.string.bank)," ",getString(R.string.level2),"\n\n",getString(R.string.Costs),"50", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));

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
                                Boolean bederfüllt = datafab1.getBoolean("bedingungenerfüllt", false);



                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                if (datafab1.getInt("Level", 1) == 1) {

                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 50) {
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

                                //LEVEL3

                                if (datafab1.getInt("Level", 1) == 2) {
                                    //bedingung erfüllt?
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
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
                                            //bedingung ist nicht mehr erfüllt für die kommenden lvl
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
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

                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

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
        //FAbrik2
        if (id == R.id.upgradefab2) {


            //ist ein upgrade am laufen?


            datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);
            Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
            if (cdfab2) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();



            }

            if (!cdfab2) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.factory_2));

                // set dialog message
                if (datafab2.getInt("Level", 0) == 0) {

                    //Text lvl 1
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1),": \n\n", getString(R.string.gold_per_hour)," 180", "\n\n ",getString(R.string.storage)," 500 "," \n \n",getString(R.string.Costs),"500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab2.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.gold_per_hour)," 180",Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ",getString(R.string.storage)," 500 ",Html.fromHtml("<font color=#00ff00> + 500</font>"),"\n\n",getString(R.string.Costs),"50", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab2.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3),": \n\n", getString(R.string.gold_per_hour),"360",Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ",getString(R.string.storage)," 1000 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:30"));
                }
                if (datafab2.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4),": \n\n", getString(R.string.gold_per_hour),"600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 1500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"1500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:40"));
                }
                if (datafab2.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5),": \n\n", getString(R.string.gold_per_hour),"800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 2000 ",Html.fromHtml("<font color=#00ff00> + 3000</font>")," \n \n",getString(R.string.Costs),"5000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:50"));
                }

                if (datafab2.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6),": \n\n", getString(R.string.gold_per_hour),"1000",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 5000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"20000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:00"));
                }

                if (datafab2.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7),": \n\n", getString(R.string.gold_per_hour),"1200",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 10000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"35000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:10"));
                }

                if (datafab2.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8),": \n\n", getString(R.string.gold_per_hour),"1400",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 15000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"70000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:20"));
                }

                if (datafab2.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9),": \n\n", getString(R.string.gold_per_hour),"1600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 20000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"110000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:30"));
                }

                if (datafab2.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10),": \n\n", getString(R.string.gold_per_hour),"1800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 25000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"150000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:40"));
                }


                alertDialogBuilder.setCancelable(false);


//button upgrade oder build
                if (datafab2.getInt("Level", 0) == 0) {
                    uporbld = getString(R.string.build);

                }
                if (datafab2.getInt("Level", 0) >= 1) {
                    uporbld = getString(R.string.upgrade);

                }

                alertDialogBuilder.setPositiveButton((uporbld),
                        new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                Boolean bederfüllt = datafab2.getBoolean("bedingungenerfüllt", false);



                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                //bedingung erfüllt?


                                if (datafab2.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                    //LeveL1

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        //bedingungen nicht mehr erfüllt
                                        editor1.putBoolean("bedingungenerfüllt", false);
                                        editor1.apply();

                                    }
                                }
                                }

                                if (datafab2.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab2.getInt("Level", 0) == 2) {

                                        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                        if ((prefs.getInt("POOL", 0)) <= 500) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 500) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                            editor2.apply();

                                            //Countdown Starten
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO lvl 3 secs
                                            editor3.putInt("countdown", 30);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);

                                            editor1.apply();

                                        }
                                    }


                                if (datafab2.getInt("Level", 0) == 3) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 4 secs
                                        editor3.putInt("countdown", 40);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 4) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 5 secs
                                        editor3.putInt("countdown", 50);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 5) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 6 secs
                                        editor3.putInt("countdown", 60);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 6) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

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
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO lvl 7 secs
                                            editor3.putInt("countdown", 70);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.apply();

                                        }

                                    }
                                }
                                if (datafab2.getInt("Level", 0) == 7) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 8 secs
                                        editor3.putInt("countdown", 80);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab2.getInt("Level", 0) == 8) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 9 secs
                                        editor3.putInt("countdown", 90);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab2.getInt("Level", 0) == 9) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab2", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        //TODO lvl 10 secs
                                        editor3.putInt("countdown", 100);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab2", MODE_PRIVATE).edit();
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

        //FAbrik3
        if (id == R.id.upgradefab3) {


            //ist ein upgrade am laufen?


            datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);
            Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
            if (cdfab3) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();



            }

            if (!cdfab3) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.factory_3));

                // set dialog message
                if (datafab3.getInt("Level", 0) == 0) {

                    //Text lvl 1
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1),": \n\n", getString(R.string.gold_per_hour)," 180", "\n\n ",getString(R.string.storage)," 500 "," \n \n",getString(R.string.Costs),"1000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab3.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.gold_per_hour)," 180",Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ",getString(R.string.storage)," 500 ",Html.fromHtml("<font color=#00ff00> + 500</font>"),"\n\n",getString(R.string.Costs),"50", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab3.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3),": \n\n", getString(R.string.gold_per_hour),"360",Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ",getString(R.string.storage)," 1000 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:30"));
                }
                if (datafab3.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4),": \n\n", getString(R.string.gold_per_hour),"600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 1500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"1500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:40"));
                }
                if (datafab3.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5),": \n\n", getString(R.string.gold_per_hour),"800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 2000 ",Html.fromHtml("<font color=#00ff00> + 3000</font>")," \n \n",getString(R.string.Costs),"5000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:50"));
                }

                if (datafab3.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6),": \n\n", getString(R.string.gold_per_hour),"1000",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 5000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"20000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:00"));
                }

                if (datafab3.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7),": \n\n", getString(R.string.gold_per_hour),"1200",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 10000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"35000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:10"));
                }

                if (datafab3.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8),": \n\n", getString(R.string.gold_per_hour),"1400",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 15000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"70000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:20"));
                }

                if (datafab3.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9),": \n\n", getString(R.string.gold_per_hour),"1600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 20000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"110000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:30"));
                }

                if (datafab3.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10),": \n\n", getString(R.string.gold_per_hour),"1800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 25000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"150000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:40"));
                }


                alertDialogBuilder.setCancelable(false);

                //button upgrade oder build
                if (datafab3.getInt("Level", 0) == 0) {
                    uporbld = getString(R.string.build);

                }
                if (datafab3.getInt("Level", 0) >= 1) {
                    uporbld = getString(R.string.upgrade);

                }

                alertDialogBuilder.setPositiveButton((uporbld), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                Boolean bederfüllt = datafab3.getBoolean("bedingungenerfüllt", false);



                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                //bedingung erfüllt?


                                if (datafab3.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        //LeveL1

                                        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                        if ((prefs.getInt("POOL", 0)) < 1000) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 1000) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1000));
                                            editor2.apply();

                                            //Countdown Starten
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO 3600 secs
                                            editor3.putInt("countdown", 20);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);
                                            //bedingungen nicht mehr erfüllt
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }
                                }

                                if (datafab3.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab3.getInt("Level", 0) == 2) {

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 3 secs
                                        editor3.putInt("countdown", 30);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);

                                        editor1.apply();

                                    }
                                }


                                if (datafab3.getInt("Level", 0) == 3) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 4 secs
                                        editor3.putInt("countdown", 40);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 4) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 5 secs
                                        editor3.putInt("countdown", 50);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 5) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 6 secs
                                        editor3.putInt("countdown", 60);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 6) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

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
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO lvl 7 secs
                                            editor3.putInt("countdown", 70);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.apply();

                                        }

                                    }
                                }
                                if (datafab3.getInt("Level", 0) == 7) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 8 secs
                                        editor3.putInt("countdown", 80);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab3.getInt("Level", 0) == 8) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 9 secs
                                        editor3.putInt("countdown", 90);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab3.getInt("Level", 0) == 9) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab3", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        //TODO lvl 10 secs
                                        editor3.putInt("countdown", 100);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab3", MODE_PRIVATE).edit();
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


        //FAbrik 4
        if (id == R.id.upgradefab4) {


            //ist ein upgrade am laufen?


            datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);
            Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
            if (cdfab4) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();



            }

            if (!cdfab4) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.factory_4));

                // set dialog message
                if (datafab4.getInt("Level", 0) == 0) {

                    //Text lvl 1
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1),": \n\n", getString(R.string.gold_per_hour)," 180", "\n\n ",getString(R.string.storage)," 500 "," \n \n",getString(R.string.Costs),"10000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab4.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2),": \n\n", getString(R.string.gold_per_hour)," 180",Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ",getString(R.string.storage)," 500 ",Html.fromHtml("<font color=#00ff00> + 500</font>"),"\n\n",getString(R.string.Costs),"50", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:20"));
                }
                if (datafab4.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3),": \n\n", getString(R.string.gold_per_hour),"360",Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ",getString(R.string.storage)," 1000 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:30"));
                }
                if (datafab4.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4),": \n\n", getString(R.string.gold_per_hour),"600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 1500 ",Html.fromHtml("<font color=#00ff00> + 500</font>")," \n \n",getString(R.string.Costs),"1500", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:40"));
                }
                if (datafab4.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5),": \n\n", getString(R.string.gold_per_hour),"800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 2000 ",Html.fromHtml("<font color=#00ff00> + 3000</font>")," \n \n",getString(R.string.Costs),"5000", getString(R.string.Gold)," \n \n",getString(R.string.time), "0:50"));
                }

                if (datafab4.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6),": \n\n", getString(R.string.gold_per_hour),"1000",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 5000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"20000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:00"));
                }

                if (datafab4.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7),": \n\n", getString(R.string.gold_per_hour),"1200",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 10000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"35000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:10"));
                }

                if (datafab4.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8),": \n\n", getString(R.string.gold_per_hour),"1400",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 15000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"70000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:20"));
                }

                if (datafab4.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9),": \n\n", getString(R.string.gold_per_hour),"1600",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 20000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"110000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:30"));
                }

                if (datafab4.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10),": \n\n", getString(R.string.gold_per_hour),"1800",Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ",getString(R.string.storage)," 25000 ",Html.fromHtml("<font color=#00ff00> + 5000</font>")," \n \n",getString(R.string.Costs),"150000", getString(R.string.Gold)," \n \n",getString(R.string.time), "1:40"));
                }


                alertDialogBuilder.setCancelable(false);

                //button upgrade oder build
                if (datafab4.getInt("Level", 0) == 0) {
                    uporbld = getString(R.string.build);

                }
                if (datafab4.getInt("Level", 0) >= 1) {
                    uporbld = getString(R.string.upgrade);

                }

                alertDialogBuilder.setPositiveButton((uporbld), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                Boolean bederfüllt = datafab4.getBoolean("bedingungenerfüllt", false);



                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                //bedingung erfüllt?


                                if (datafab4.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level4), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        //LeveL1

                                        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                        if ((prefs.getInt("POOL", 0)) < 10000) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 10000) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 10000));
                                            editor2.apply();

                                            //Countdown Starten
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO 3600 secs
                                            editor3.putInt("countdown", 20);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);
                                            //bedingungen nicht mehr erfüllt
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }
                                }

                                if (datafab4.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 20);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab4.getInt("Level", 0) == 2) {

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 3 secs
                                        editor3.putInt("countdown", 30);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);

                                        editor1.apply();

                                    }
                                }


                                if (datafab4.getInt("Level", 0) == 3) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 4 secs
                                        editor3.putInt("countdown", 40);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 4) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 5 secs
                                        editor3.putInt("countdown", 50);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 5) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 6 secs
                                        editor3.putInt("countdown", 60);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 6) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade)+getString(R.string.bank)+getString(R.string.zu)+getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

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
                                            SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO lvl 7 secs
                                            editor3.putInt("countdown", 70);
                                            editor3.apply();

                                            SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.apply();

                                        }

                                    }
                                }
                                if (datafab4.getInt("Level", 0) == 7) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 8 secs
                                        editor3.putInt("countdown", 80);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab4.getInt("Level", 0) == 8) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 9 secs
                                        editor3.putInt("countdown", 90);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab4.getInt("Level", 0) == 9) {

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
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab4", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        //TODO lvl 10 secs
                                        editor3.putInt("countdown", 100);
                                        editor3.apply();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("datafab4", MODE_PRIVATE).edit();
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
                String strI = "" +
                        goldprogress1;
                //maximum storage to string

                String maxgo = "" +
                        datafab1.getInt("maxfabrik1", 500);

                countfab1.setText(strI + "/" + maxgo);

                double goldphfab160= datafab1.getFloat("goldphfab1", (float) 0.05)*60*60;

                int goldphfab1int = (int)goldphfab160;

                String goph = "" +
                        goldphfab1int;
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


    //Fabrik2
    public void dialogFabr2(){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik2);





        datafab2 = getSharedPreferences("datafab2", MODE_PRIVATE);



        countfab2 = (TextView) dialog.findViewById(R.id.countfab2);
        final TextView GperH2 = (TextView) dialog.findViewById(R.id.GperH2);
        progressBarFabrik2 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik2);
        progressBarFabrik2.setMax(datafab2.getInt("maxfabrik2", 500));
        leveltextdiafab2 = (TextView) dialog.findViewById(R.id.leveltextdiafab2);

        String lvltext = (datafab2.getString("leveltextfab2", "level 1"));
        leveltextdiafab2.setText(lvltext);


        h.postDelayed(new Runnable() {
            public void run() {
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * datafab2.getFloat("goldphfab2", (float) 0.05));
                long gold = (long) goldtemp;
                int goldprogress1 = (int) gold;

                if (goldprogress1 >= datafab2.getInt("maxfabrik2", 500)) {
                    goldprogress1 = datafab2.getInt("maxfabrik2", 500);
                }
                progressBarFabrik2.setProgress(goldprogress1);

                //
                String strI = "" +
                        goldprogress1;
                //maximum storage to string
                String maxgo = "" +
                        datafab2.getInt("maxfabrik2", 500);

                countfab2.setText(strI + "/" + maxgo);

                double goldphfab160= datafab2.getFloat("goldphfab2", (float) 0.05)*60*60;

                int goldphfab1int = (int)goldphfab160;

                String goph = "" +
                        goldphfab1int;
                GperH2.setText(getString(R.string.gold_per_hour)+ goph);


                h.postDelayed(this, delay);
            }
        }, delay);




        dialog.show();



    }
    public void sammelnfab2(){


        SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE);

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.



        int endTime = ((int) System.currentTimeMillis()/1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab2.getFloat("goldphfab2", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int)(gold);

        //checken ob das max schon erreicht wurde

        if(goldint>= datafab2.getInt("maxfabrik2", 500)) {

            goldint = datafab2.getInt("maxfabrik2", 500);
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
            SharedPreferences.Editor editor1 = getSharedPreferences("speichervonstartzeitfab2", MODE_PRIVATE).edit();
            editor1.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
            editor1.apply();

            //Das Gold zum pool hinzufügen
            SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

            editor2.putInt("POOL", + goldint + (prefs1.getInt("POOL", 0)));
            editor2.apply();

            //rücklauf falls das max erfüllt wurde(nicht vorhanden

            Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

        }

    }


    //Fabrik3
    public void dialogFabr3(){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik3);





        datafab3 = getSharedPreferences("datafab3", MODE_PRIVATE);



        countfab3 = (TextView) dialog.findViewById(R.id.countfab3);
        final TextView GperH3 = (TextView) dialog.findViewById(R.id.GperH3);
        progressBarFabrik3 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik3);
        progressBarFabrik3.setMax(datafab3.getInt("maxfabrik3", 500));
        leveltextdiafab3 = (TextView) dialog.findViewById(R.id.leveltextdiafab3);

        String lvltext = (datafab3.getString("leveltextfab3", "level 1"));
        leveltextdiafab3.setText(lvltext);


        h.postDelayed(new Runnable() {
            public void run() {
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab3", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * datafab3.getFloat("goldphfab3", (float) 0.05));
                long gold = (long) goldtemp;
                int goldprogress1 = (int) gold;

                if (goldprogress1 >= datafab3.getInt("maxfabrik3", 500)) {
                    goldprogress1 = datafab3.getInt("maxfabrik3", 500);
                }
                progressBarFabrik3.setProgress(goldprogress1);

                //
                String strI = "" +
                        goldprogress1;
                //maximum storage to string
                String maxgo = "" +
                        datafab3.getInt("maxfabrik3", 500);

                countfab3.setText(strI + "/" + maxgo);

                double goldphfab160= datafab3.getFloat("goldphfab3", (float) 0.05)*60*60;

                int goldphfab1int = (int)goldphfab160;

                String goph = "" +
                        goldphfab1int;
                GperH3.setText(getString(R.string.gold_per_hour)+ goph);


                h.postDelayed(this, delay);
            }
        }, delay);




        dialog.show();



    }
    public void sammelnfab3(){


        SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab3", MODE_PRIVATE);

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.



        int endTime = ((int) System.currentTimeMillis()/1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab3.getFloat("goldphfab3", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int)(gold);

        //checken ob das max schon erreicht wurde

        if(goldint>= datafab3.getInt("maxfabrik3", 500)) {

            goldint = datafab3.getInt("maxfabrik3", 500);
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
            SharedPreferences.Editor editor1 = getSharedPreferences("speichervonstartzeitfab3", MODE_PRIVATE).edit();
            editor1.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
            editor1.apply();

            //Das Gold zum pool hinzufügen
            SharedPreferences.Editor editor3 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

            editor3.putInt("POOL", + goldint + (prefs1.getInt("POOL", 0)));
            editor3.apply();

            //rücklauf falls das max erfüllt wurde(nicht vorhanden

            Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

        }

    }

    //Fabrik4
    public void dialogFabr4(){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik4);





        datafab4 = getSharedPreferences("datafab4", MODE_PRIVATE);



        countfab4 = (TextView) dialog.findViewById(R.id.countfab4);
        final TextView GperH4 = (TextView) dialog.findViewById(R.id.GperH4);
        progressBarFabrik4 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik4);
        progressBarFabrik4.setMax(datafab4.getInt("maxfabrik4", 500));
        leveltextdiafab4 = (TextView) dialog.findViewById(R.id.leveltextdiafab4);

        String lvltext = (datafab4.getString("leveltextfab4", "level 1"));
        leveltextdiafab4.setText(lvltext);


        h.postDelayed(new Runnable() {
            public void run() {
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab4", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * datafab4.getFloat("goldphfab4", (float) 0.05));
                long gold = (long) goldtemp;
                int goldprogress1 = (int) gold;

                if (goldprogress1 >= datafab4.getInt("maxfabrik4", 500)) {
                    goldprogress1 = datafab4.getInt("maxfabrik4", 500);
                }
                progressBarFabrik4.setProgress(goldprogress1);

                //
                String strI = "" +
                        goldprogress1;
                //maximum storage to string
                String maxgo = "" +
                        datafab4.getInt("maxfabrik4", 500);

                countfab4.setText(strI + "/" + maxgo);

                double goldphfab160= datafab4.getFloat("goldphfab4", (float) 0.05)*60*60;

                int goldphfab1int = (int)goldphfab160;

                String goph = "" +
                        goldphfab1int;
                GperH4.setText(getString(R.string.gold_per_hour)+ goph);


                h.postDelayed(this, delay);
            }
        }, delay);




        dialog.show();



    }
    public void sammelnfab4(){


        SharedPreferences prefs = getSharedPreferences("speichervonstartzeitfab4", MODE_PRIVATE);

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.



        int endTime = ((int) System.currentTimeMillis()/1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab4.getFloat("goldphfab4", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int)(gold);

        //checken ob das max schon erreicht wurde

        if(goldint>= datafab4.getInt("maxfabrik4", 500)) {

            goldint = datafab4.getInt("maxfabrik4", 500);
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
            SharedPreferences.Editor editor1 = getSharedPreferences("speichervonstartzeitfab4", MODE_PRIVATE).edit();
            editor1.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
            editor1.apply();

            //Das Gold zum pool hinzufügen
            SharedPreferences.Editor editor4 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

            editor4.putInt("POOL", + goldint + (prefs1.getInt("POOL", 0)));
            editor4.apply();

            //rücklauf falls das max erfüllt wurde(nicht vorhanden

            Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

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
