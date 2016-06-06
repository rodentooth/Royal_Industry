package com.frozensparks.royalindustry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.unity3d.player.UnityPlayer;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //fabrikdialoge
    Dialog dialog;

    //Fabrik1
    Button Fabrik1;
    Button SammelnFabrik1;
    ProgressBar progressBarFabrik1;
    Context context = this;
    MyPreferences datafab1;
    TextView countfab1;
    TextView leveltextfabr1;
    TextView leveltextdiafab1;

    //Fabrik2
    Button Fabrik2;
    Button SammelnFabrik2;
    ProgressBar progressBarFabrik2;
    MyPreferences datafab2;
    TextView countfab2;
    TextView leveltextfabr2;
    TextView leveltextdiafab2;

    //Fabrik3
    Button Fabrik3;
    Button SammelnFabrik3;
    ProgressBar progressBarFabrik3;
    MyPreferences datafab3;
    TextView countfab3;
    TextView leveltextfabr3;
    TextView leveltextdiafab3;

    //Fabrik4
    Button Fabrik4;
    Button SammelnFabrik4;
    ProgressBar progressBarFabrik4;
    MyPreferences datafab4;
    TextView countfab4;
    TextView leveltextfabr4;
    TextView leveltextdiafab4;

    //Bank
    Button Bank;
    ProgressBar progressBarUpgradeBank;


    //Aktualisator
    Handler h = new Handler();
    int delay = 100; //milliseconds

    //Bauhaus
    Button bauhaus;
    Dialog bauhausdialog;
    Button closeBauhaus;
    TextView goldtextarch;
    //BauhausFabrik1
    Button upgradefab1;
    ProgressBar progressBarUpgradefab1;
    TextView upcdfab1;
    //BauhausFabrik2
    Button upgradefab2;
    ProgressBar progressBarUpgradefab2;
    TextView upcdfab2;
    //BauhausFabrik3
    Button upgradefab3;
    ProgressBar progressBarUpgradefab3;
    TextView upcdfab3;
    //BauhausFabrik4
    Button upgradefab4;
    ProgressBar progressBarUpgradefab4;
    TextView upcdfab4;
    //Bauhaus Bank
    Button upgradeBank;
    TextView leveltextBank;
    TextView upcdBank;
    //Bauhaus Agentur
    Button upgradeAgency;
    TextView leveltextAgency;
    TextView upcdAgency;

    String uporbld;


    //werkstatt
    Button werkstatt;

    //agency
    Button Agency;
    MyPreferences dataAgency;
    ProgressBar progressBarUpgradeAgency;


    Typeface typeface;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //FULLSCREEN


    //Google sign in


    //Start

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //schrift umstellen
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_BOLD", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_BOLD_ITALIC", "fonts/OldGlyphs.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT_ITALIC", "fonts/OldGlyphs.ttf");
        AssetManager am = context.getApplicationContext().getAssets();

        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%2s", "OldGlyphs.ttf"));


        setContentView(R.layout.activity_main);



/*
        SharedPreferences coins = getSharedPreferences("coins", MODE_PRIVATE);
        int i = coins.getInt("coins", 0);

        Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();

*/

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

        //agency
        Agency = (Button) findViewById(R.id.openagency);
        assert Agency != null;
        Agency.setOnClickListener(this);
        progressBarUpgradeAgency = (ProgressBar) findViewById(R.id.progressBarUpgradeAgency);
        assert progressBarUpgradeAgency != null;
        progressBarUpgradeAgency.setVisibility(View.INVISIBLE);
        final MyPreferences dataAgency = MyPreferences.getInstance(this, "dataAgency");
        if (dataAgency.getInt("Level", 0) == 0) {

            Agency.setVisibility(View.INVISIBLE);
        }
        if (dataAgency.getInt("Level", 0) == 1) {

            Agency.setVisibility(View.VISIBLE);
        }


        //werkstatt
        werkstatt = (Button) findViewById(R.id.werkstatt);
        assert werkstatt != null;
        werkstatt.setOnClickListener(this);


        //Bank
        Bank = (Button) findViewById(R.id.Bank);
        assert Bank != null;
        Bank.setOnClickListener(this);
        progressBarUpgradeBank = (ProgressBar) findViewById(R.id.progressBarUpgradeBank);
        assert progressBarUpgradeBank != null;
        progressBarUpgradeBank.setVisibility(View.INVISIBLE);
        MyPreferences dataBank = MyPreferences.getInstance(this,"dataBank");
        //bilder
        if (dataBank.getInt("Level", 1) == 2) {
            Bank.setBackgroundResource(R.drawable.banklvl2);
        }
        if (dataBank.getInt("Level", 1) == 3) {
            Bank.setBackgroundResource(R.drawable.banklvl3);
        }
        if (dataBank.getInt("Level", 1) == 4) {
            Bank.setBackgroundResource(R.drawable.banklvl4);
        }


        //Bauhaus
        bauhaus = (Button) findViewById(R.id.bauhaus);
        assert bauhaus != null;
        bauhaus.setOnClickListener(this);


        //Fabrik1

        Fabrik1 = (Button) findViewById(R.id.Fabrik1);
        assert Fabrik1 != null;
        Fabrik1.setOnClickListener(this);
        SammelnFabrik1 = (Button) findViewById(R.id.SammelnFabrik1);
        assert SammelnFabrik1 != null;
        SammelnFabrik1.setOnClickListener(this);
        progressBarUpgradefab1 = (ProgressBar) findViewById(R.id.progressBarUpgradefab1);
        assert progressBarUpgradefab1 != null;
        progressBarUpgradefab1.setVisibility(View.INVISIBLE);


        //Fabrik2
        Fabrik2 = (Button) findViewById(R.id.Fabrik2);
        assert Fabrik2 != null;
        Fabrik2.setOnClickListener(this);
        SammelnFabrik2 = (Button) findViewById(R.id.SammelnFabrik2);
        assert SammelnFabrik2 != null;
        SammelnFabrik2.setOnClickListener(this);
        progressBarUpgradefab2 = (ProgressBar) findViewById(R.id.progressBarUpgradefab2);
        if (progressBarUpgradefab2 != null){
            progressBarUpgradefab2.setVisibility(View.INVISIBLE);
    }

        //Fabrik3
        Fabrik3 = (Button) findViewById(R.id.Fabrik3);
        assert Fabrik3 != null;
        Fabrik3.setOnClickListener(this);
        SammelnFabrik3 = (Button) findViewById(R.id.SammelnFabrik3);
        assert SammelnFabrik3 != null;
        SammelnFabrik3.setOnClickListener(this);
        progressBarUpgradefab3 = (ProgressBar) findViewById(R.id.progressBarUpgradefab3);
        assert progressBarUpgradefab3 != null;
        progressBarUpgradefab3.setVisibility(View.INVISIBLE);


        //Fabrik4
        Fabrik4 = (Button) findViewById(R.id.Fabrik4);
        assert Fabrik4 != null;
        Fabrik4.setOnClickListener(this);
        SammelnFabrik4 = (Button) findViewById(R.id.SammelnFabrik4);
        assert SammelnFabrik4 != null;
        SammelnFabrik4.setOnClickListener(this);
        progressBarUpgradefab4 = (ProgressBar) findViewById(R.id.progressBarUpgradefab4);
        assert progressBarUpgradefab4 != null;
        progressBarUpgradefab4.setVisibility(View.INVISIBLE);



        //counter für fabriken. Hat die fabrik schonmal produziert?
        datafab1 = MyPreferences.getInstance(this,"datafab1");


        MyPreferences firstopen = MyPreferences.getInstance(this,"firstopen");
        Boolean firstopenboo = (firstopen.getBoolean("firstopen", true));

        //Sachen, nur auf das allererste öffnen ausführen
        if (firstopenboo) {

            //zweite versicherung (damit die fabrick nicht versehentlich auf lvl 1 zurückgesetzt wird)
            if ((datafab1.getInt("Level", 1) == 1)) {
                //erste startzeit speichern
                MyPreferences editor = MyPreferences.getInstance(this, "speichervonstartzeit1");
                editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - 1500);
                editor.apply();

                //Fabrik 1 auf lvl 1 setzen
                MyPreferences editorfab1 = MyPreferences.getInstance(this,"datafab1");
                editorfab1.putInt("maxfabrik1", 500);
                editorfab1.putInt("minfabrik1", 1);
                editorfab1.putFloat("goldphfab1", (float) 0.05);
                editorfab1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level1));
                editorfab1.apply();


                //Agency auf lvl 0
                MyPreferences editorAgency = MyPreferences.getInstance(this,"dataAgency");
                editorAgency.putString("leveltext", getString(R.string.agency) + " ");
                editorAgency.apply();


                //Bank auf lvl 1
                MyPreferences editorBank = MyPreferences.getInstance(this,"dataBank");
                editorBank.putInt("maxGoldStorage", 1000);
                editorBank.putString("leveltext", getString(R.string.bank) + " " + getString(R.string.level1));
                editorBank.apply();

                //Fabrik 2 auf lvl 0 setzen
                MyPreferences editorfab2 = MyPreferences.getInstance(this,"datafab2");
                editorfab2.putInt("maxfabrik2", 0);
                editorfab2.putInt("minfabrik2", 1);
                editorfab2.putFloat("goldphfab2", (float) 0);
                editorfab2.putString("leveltextfab2", getString(R.string.factory_2) + " ");
                Fabrik2.setVisibility(View.INVISIBLE);
                editorfab2.apply();

                //Fabrik 3 auf lvl 0 setzen
                MyPreferences editorfab3 = MyPreferences.getInstance(this,"datafab3");
                editorfab3.putInt("maxfabrik3", 0);
                editorfab3.putInt("minfabrik3", 1);
                editorfab3.putFloat("goldphfab3", (float) 0);
                editorfab3.putString("leveltextfab3", getString(R.string.factory_3) + " ");
                Fabrik3.setVisibility(View.INVISIBLE);
                editorfab3.apply();

                //Fabrik 4 auf lvl 0 setzen
                MyPreferences editorfab4 = MyPreferences.getInstance(this,"datafab4");
                editorfab4.putInt("maxfabrik4", 0);
                editorfab4.putInt("minfabrik4", 1);
                editorfab4.putFloat("goldphfab4", (float) 0);
                editorfab4.putString("leveltextfab4", getString(R.string.factory_4) + " ");
                Fabrik4.setVisibility(View.INVISIBLE);
                editorfab4.apply();


                //firstopen auf falsch stellen
                MyPreferences editor2 = MyPreferences.getInstance(this,"firstopen");
                editor2.putBoolean("firstopen", false);
                editor2.apply();

            }
        }


        //Aktualisator

        datafab2 = MyPreferences.getInstance(this,"datafab2");
        datafab3 = MyPreferences.getInstance(this,"datafab3");
        datafab4 = MyPreferences.getInstance(this,"datafab4");

        int fabbild1 = datafab1.getInt("Level", 1);
        int fabbild1id= getResources().getIdentifier("fablvl"+fabbild1, "drawable", getPackageName());
        Fabrik1.setBackgroundResource(fabbild1id);

        if (datafab2.getInt("Level", 0) == 0) {
            Fabrik2.setVisibility(View.INVISIBLE);
            SammelnFabrik2.setVisibility(View.INVISIBLE);

        }
            if (datafab2.getInt("Level", 0) >= 1) {
                int fabbild2 = datafab2.getInt("Level", 1);
                int fabbild2id= getResources().getIdentifier("fablvl"+fabbild2, "drawable", getPackageName());
                Fabrik2.setBackgroundResource(fabbild2id);
            }
        if (datafab3.getInt("Level", 0) == 0) {
            Fabrik3.setVisibility(View.INVISIBLE);
            SammelnFabrik3.setVisibility(View.INVISIBLE);

        }
        if (datafab3.getInt("Level", 0) >= 1) {
            int fabbild3 = datafab3.getInt("Level", 1);
            int fabbild3id= getResources().getIdentifier("fablvl"+fabbild3, "drawable", getPackageName());
            Fabrik3.setBackgroundResource(fabbild3id);
        }
        if (datafab4.getInt("Level", 0) == 0) {
            Fabrik4.setVisibility(View.INVISIBLE);
            SammelnFabrik4.setVisibility(View.INVISIBLE);

        }
        if (datafab4.getInt("Level", 0) >= 1) {
            int fabbild4 = datafab4.getInt("Level", 1);
            int fabbild4id= getResources().getIdentifier("fablvl"+fabbild4, "drawable", getPackageName());
            Fabrik4.setBackgroundResource(fabbild4id);
        }
        h.postDelayed(new Runnable() {
            public void run() {


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



//jede sekunde fabrikbilder checken

                int fabbild1 = datafab1.getInt("Level", 1);
                int fabbild1id= getResources().getIdentifier("fablvl"+fabbild1, "drawable", getPackageName());
                Fabrik1.setBackgroundResource(fabbild1id);

                if (datafab2.getInt("Level", 0) == 0) {
                    Fabrik2.setVisibility(View.INVISIBLE);
                    SammelnFabrik2.setVisibility(View.INVISIBLE);

                }
                if (datafab2.getInt("Level", 0) >= 1) {
                    int fabbild2 = datafab2.getInt("Level", 1);
                    int fabbild2id= getResources().getIdentifier("fablvl"+fabbild2, "drawable", getPackageName());
                    Fabrik2.setBackgroundResource(fabbild2id);
                }
                if (datafab3.getInt("Level", 0) == 0) {
                    Fabrik3.setVisibility(View.INVISIBLE);
                    SammelnFabrik3.setVisibility(View.INVISIBLE);

                }
                if (datafab3.getInt("Level", 0) >= 1) {
                    int fabbild3 = datafab3.getInt("Level", 1);
                    int fabbild3id= getResources().getIdentifier("fablvl"+fabbild3, "drawable", getPackageName());
                    Fabrik3.setBackgroundResource(fabbild3id);
                }
                if (datafab4.getInt("Level", 0) == 0) {
                    Fabrik4.setVisibility(View.INVISIBLE);
                    SammelnFabrik4.setVisibility(View.INVISIBLE);

                }
                if (datafab4.getInt("Level", 0) >= 1) {
                    int fabbild4 = datafab4.getInt("Level", 1);
                    int fabbild4id= getResources().getIdentifier("fablvl"+fabbild4, "drawable", getPackageName());
                    Fabrik4.setBackgroundResource(fabbild4id);
                }



                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.


                //Gold check Fabrik1
                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * prefs1.getFloat("goldphfab1", (float) 0.05));
                long gold = (long) goldtemp;
                //checken obs soweit ist
                if (gold <= prefs1.getInt("minfabrik1", 1)) {
                    SammelnFabrik1.setVisibility(View.GONE);
                }
                if (gold >= prefs1.getInt("minfabrik1", 1)) {
                    SammelnFabrik1.setVisibility(View.VISIBLE);
                }


                //Gold check Fabrik2
                MyPreferences prefsfab2 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");
                MyPreferences prefs1fab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                int startTimefab2 = prefsfab2.getInt("startTime", 0); //0 is the default value.

                int endTimefab2 = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab2 = endTimefab2 - startTimefab2;
                //einheiten umwandeln
                double goldtempfab2 = (secondsElapsedfab2 * prefs1fab2.getFloat("goldphfab2", (float) 0.05));
                long goldfab2 = (long) goldtempfab2;
                //checken obs soweit ist
                if (goldfab2 <= prefs1fab2.getInt("minfabrik2", 1)) {
                    SammelnFabrik2.setVisibility(View.GONE);
                }
                if (goldfab2 >= prefs1fab2.getInt("minfabrik2", 1)) {
                    SammelnFabrik2.setVisibility(View.VISIBLE);
                }
                if (datafab2.getInt("Level", 0) == 0) {
                    Fabrik2.setVisibility(View.INVISIBLE);
                }
                if (datafab2.getInt("Level", 0) >= 1) {
                    Fabrik2.setVisibility(View.VISIBLE);
                }


                //Gold check Fabrik3
                MyPreferences prefsfab3 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");
                MyPreferences prefs1fab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                int startTimefab3 = prefsfab3.getInt("startTime", ((int) System.currentTimeMillis() / 1000)); //0 is the default value.

                int endTimefab3 = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab3 = endTimefab3 - startTimefab3;
                //einheiten umwandeln
                double goldtempfab3 = (secondsElapsedfab3 * prefs1fab3.getFloat("goldphfab3", (float) 0.05));
                long goldfab3 = (long) goldtempfab3;
                //checken obs soweit ist

                if (goldfab3 <= prefs1fab3.getInt("minfabrik3", 1)) {
                    SammelnFabrik3.setVisibility(View.GONE);
                }
                if (goldfab3 >= prefs1fab3.getInt("minfabrik3", 1)) {
                    SammelnFabrik3.setVisibility(View.VISIBLE);
                }
                if (datafab3.getInt("Level", 0) == 0) {
                    Fabrik3.setVisibility(View.INVISIBLE);
                }

                if (datafab3.getInt("Level", 0) >= 1) {
                    Fabrik3.setVisibility(View.VISIBLE);
                }


                //Gold check Fabrik4
                MyPreferences prefsfab4 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");
                MyPreferences prefs1fab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                int startTimefab4 = prefsfab4.getInt("startTime", ((int) System.currentTimeMillis() / 1000)); //0 is the default value.

                int endTimefab4 = ((int) System.currentTimeMillis() / 1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsedfab4 = endTimefab4 - startTimefab4;
                //einheiten umwandeln
                double goldtempfab4 = (secondsElapsedfab4 * prefs1fab4.getFloat("goldphfab4", (float) 0.05));
                long goldfab4 = (long) goldtempfab4;
                //checken obs soweit ist
                if (goldfab4 <= prefs1fab4.getInt("minfabrik4", 1)) {
                    SammelnFabrik4.setVisibility(View.GONE);
                }
                if (goldfab4 >= prefs1fab4.getInt("minfabrik4", 1)) {
                    SammelnFabrik4.setVisibility(View.VISIBLE);
                }
                if (datafab4.getInt("Level", 0) == 0) {
                    Fabrik4.setVisibility(View.INVISIBLE);
                }
                if (datafab4.getInt("Level", 0) >= 1) {
                    Fabrik4.setVisibility(View.VISIBLE);
                }


                if (dataAgency.getInt("Level", 0) == 0) {

                    Agency.setVisibility(View.INVISIBLE);
                }


                //upgrades


                //Agency
                MyPreferences dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                Boolean cdAgency = (dataAgency.getBoolean("isLeveling", false));
                if (cdAgency) {

                    progressBarUpgradeAgency.setVisibility(View.VISIBLE);
                    //startzeit holen
                    MyPreferences AgencyupgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeAgency");
                    int startTimeAgency = AgencyupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeAgency = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsAgency = endTimeAgency - startTimeAgency;

                    //progressbar updaten
                    progressBarUpgradeAgency.setMax(AgencyupgradeContdown.getInt("countdown", 0));
                    progressBarUpgradeAgency.setProgress(elapsedSecondsAgency);


                    if (elapsedSecondsAgency > AgencyupgradeContdown.getInt("countdown", 0)) {


                        MyPreferences editorAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                        dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");

                        if (dataAgency.getInt("Level", 0) == 0) {
                            Agency.setVisibility(View.VISIBLE);

                            //definition level1
                            editorAgency.putString("leveltext", getString(R.string.agency) + " " + getString(R.string.level1));
                            editorAgency.putBoolean("isLeveling", false);
                            //cdAgency = false;
                            progressBarUpgradeAgency.setVisibility(View.INVISIBLE);
                            Agency.setBackgroundResource(R.drawable.agencylvl1);
                            editorAgency.putInt("Level", dataAgency.getInt("Level", 0) + 1);
                            editorAgency.apply();


                        }



                    }

                }

                //Bank
                MyPreferences dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
                Boolean cdBank = (dataBank.getBoolean("isLeveling", false));
                if (cdBank) {

                    progressBarUpgradeBank.setVisibility(View.VISIBLE);
                    //startzeit holen
                    MyPreferences BankupgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeBank");
                    int startTimeBank = BankupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeBank = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsBank = endTimeBank - startTimeBank;

                    //progressbar updaten
                    progressBarUpgradeBank.setMax(BankupgradeContdown.getInt("countdown", 0));
                    progressBarUpgradeBank.setProgress(elapsedSecondsBank);


                    if (elapsedSecondsBank > BankupgradeContdown.getInt("countdown", 0)) {


                        dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");


                        MyPreferences editorBank = MyPreferences.getInstance(MainActivity.this,"dataBank");

                        if (dataBank.getInt("Level", 1) == 1) {
                            Bank.setBackgroundResource(R.drawable.banklvl2);

                            //definition level2
                            editorBank.putInt("maxGoldStorage", 20000);
                            editorBank.putString("leveltext", getString(R.string.bank) + " " + getString(R.string.level2));
                            editorBank.apply();
                            editorBank.putBoolean("isLeveling", false);
                            cdBank = false;
                            progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                            Bank.setBackgroundResource(R.drawable.banklvl2);
                            editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                            editorBank.apply();

                            //gebäude auf Bank level 2 freischalten:

                            //Fabrik 1 level4

                            MyPreferences Levelbedingungfab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                            Levelbedingungfab1.putBoolean("bedingungenerfüllt", true);
                            Levelbedingungfab1.apply();

                            //Fabrik 2

                            MyPreferences Levelbedingungfab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                            Levelbedingungfab2.putBoolean("bedingungenerfüllt", true);
                            Levelbedingungfab2.apply();

                            //Agentur

                            MyPreferences lvlbedagency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                            lvlbedagency.putBoolean("bedingungenerfüllt", true);
                            lvlbedagency.apply();

                        }

                        if (cdBank) {
                            if (dataBank.getInt("Level", 1) == 2) {


                                //definition level3
                                editorBank.putInt("maxGoldStorage", 75000);
                                editorBank.putString("leveltext", getString(R.string.bank) + " " + getString(R.string.level3));
                                editorBank.apply();
                                editorBank.putBoolean("isLeveling", false);
                                cdBank = false;
                                Bank.setBackgroundResource(R.drawable.banklvl3);

                                progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                                editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                                editorBank.apply();

                                //gebäude auf Bank level 3 freischalten:
                                //fabrik3
                                MyPreferences Levelbedingungfab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                Levelbedingungfab3.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab3.apply();

                                //Fabriken auf lvl 7
                                MyPreferences Levelbedingungfab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                Levelbedingungfab1.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab1.apply();


                                MyPreferences Levelbedingungfab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                Levelbedingungfab2.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab2.apply();

                                //Agentur lvl2

                            }
                        }

                        if (cdBank) {
                            if (dataBank.getInt("Level", 1) == 3) {


                                //definition level4
                                editorBank.putInt("maxGoldStorage", 150000);
                                editorBank.putString("leveltext", getString(R.string.bank) + " " + getString(R.string.level4));
                                editorBank.apply();
                                editorBank.putBoolean("isLeveling", false);
                                // bei weiteren lvl aktivieren cdBank = false;
                                progressBarUpgradeBank.setVisibility(View.INVISIBLE);
                                editorBank.putInt("Level", dataBank.getInt("Level", 1) + 1);
                                editorBank.apply();
                                Bank.setBackgroundResource(R.drawable.banklvl4);

                                //gebäude auf Bank level 4 freischalten:
                                //fabrik4
                                MyPreferences Levelbedingungfab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                Levelbedingungfab4.putBoolean("bedingungenerfüllt", true);
                                Levelbedingungfab4.apply();

                            }

                        }


                    }
                }


                //Fabrik1
                datafab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
                if (cdfab1) {

                    progressBarUpgradefab1.setVisibility(View.VISIBLE);
                    SammelnFabrik1.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab1 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab1 = endTimefab1 - startTimefab1;

                    //progressbar updaten
                    progressBarUpgradefab1.setMax(Fab1upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab1.setProgress(elapsedSecondsfab1);

                    //goldprod.während des lvlns stoppen

                    MyPreferences editor = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab1 > Fab1upgradeContdown.getInt("countdown", 0)) {


                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");

                        if (datafab1.getInt("Level", 1) == 1) {


                            //definition level2
                            editor1.putInt("maxfabrik1", 1000);
                            editor1.putInt("minfabrik1", 2);
                            editor1.putFloat("goldphfab1", (float) 0.1);
                            editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level2));
                            editor1.putBoolean("isLeveling", false);
                            cdfab1 = false;
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                            editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                            editor1.apply();

                            //gebäude auf lvl 2 freischalten:

                            //bank lvl 2
                            MyPreferences LevelbedingungBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
                            LevelbedingungBank.putBoolean("bedingungenerfüllt", true);
                            LevelbedingungBank.apply();

                        }

                        if (cdfab1) {
                            if (datafab1.getInt("Level", 1) == 2) {

                                //LEVEL3
                                editor1.putInt("maxfabrik1", 1500);
                                editor1.putInt("minfabrik1", 3);
                                editor1.putFloat("goldphfab1", (float) 0.16667);
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level3));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level4));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level5));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level6));
                                editor1.putBoolean("isLeveling", false);
                                cdfab1 = false;
                                progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                                SammelnFabrik1.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                                editor1.apply();


                                //bedingung 1 für bank lvl 3 ok
                                MyPreferences LevelbedingungBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level7));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level8));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level9));
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
                                editor1.putString("leveltextfab1", getString(R.string.factory_1) + " " + getString(R.string.level10));
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
                datafab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                Boolean cdfab2 = (datafab2.getBoolean("isLeveling", false));
                if (cdfab2) {

                    progressBarUpgradefab2.setVisibility(View.VISIBLE);
                    SammelnFabrik2.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    MyPreferences Fab2upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                    int startTimeupgradefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab2 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab2 = endTimeupgradefab2 - startTimeupgradefab2;

                    //progressbar updaten
                    progressBarUpgradefab2.setMax(Fab2upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab2.setProgress(elapsedSecondsfab2);

                    //goldprod.während des lvlns stoppen

                    MyPreferences editor = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab2 > Fab2upgradeContdown.getInt("countdown", 0)) {


                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");

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
                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");
                            editor3.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level3));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level4));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level5));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level6));
                                editor1.putBoolean("isLeveling", false);
                                cdfab2 = false;
                                progressBarUpgradefab2.setVisibility(View.INVISIBLE);
                                SammelnFabrik2.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab2.getInt("Level", 0) + 1);
                                editor1.apply();


                                //bedingung 2 für bank lvl 3 ok
                                MyPreferences LevelbedingungBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level7));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level8));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level9));
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
                                editor1.putString("leveltextfab2", getString(R.string.factory_2) + " " + getString(R.string.level10));
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
                datafab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                Boolean cdfab3 = (datafab3.getBoolean("isLeveling", false));
                if (cdfab3) {

                    progressBarUpgradefab3.setVisibility(View.VISIBLE);
                    SammelnFabrik3.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    MyPreferences Fab3upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                    int startTimeupgradefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab3 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab3 = endTimeupgradefab3 - startTimeupgradefab3;

                    //progressbar updaten
                    progressBarUpgradefab3.setMax(Fab3upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab3.setProgress(elapsedSecondsfab3);

                    //goldprod.während des lvlns stoppen

                    MyPreferences editor = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab3 > Fab3upgradeContdown.getInt("countdown", 0)) {


                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");

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
                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");
                            editor3.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - 20);
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level3));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level4));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level5));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level6));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level7));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level8));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level9));
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
                                editor1.putString("leveltextfab3", getString(R.string.factory_3) + " " + getString(R.string.level10));
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
                datafab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                Boolean cdfab4 = (datafab4.getBoolean("isLeveling", false));
                if (cdfab4) {

                    progressBarUpgradefab4.setVisibility(View.VISIBLE);
                    SammelnFabrik4.setVisibility(View.INVISIBLE);
                    //startzeit holen
                    MyPreferences Fab4upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                    int startTimeupgradefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeupgradefab4 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab4 = endTimeupgradefab4 - startTimeupgradefab4;

                    //progressbar updaten
                    progressBarUpgradefab4.setMax(Fab4upgradeContdown.getInt("countdown", 0));
                    progressBarUpgradefab4.setProgress(elapsedSecondsfab4);

                    //goldprod.während des lvlns stoppen

                    MyPreferences editor = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");
                    editor.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor.apply();

                    if (elapsedSecondsfab4 > Fab4upgradeContdown.getInt("countdown", 0)) {


                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");

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
                            MyPreferences editor4 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");
                            editor4.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - 20);
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level3));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level4));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level5));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level6));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level7));
                                editor1.putBoolean("isLeveling", false);
                                cdfab4 = false;
                                progressBarUpgradefab4.setVisibility(View.INVISIBLE);
                                SammelnFabrik4.setVisibility(View.VISIBLE);
                                editor1.putInt("Level", datafab4.getInt("Level", 0) + 1);
                                editor1.apply();
                            }
                        }
                        if (cdfab4) {
                            if (datafab4.getInt("Level", 0) == 7) {

                                //lvl8
                                editor1.putInt("maxfabrik4", 20000);
                                editor1.putInt("minfabrik4", 8);
                                editor1.putFloat("goldphfab4", (float) 0.44444444444444);
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level8));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level9));
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
                                editor1.putString("leveltextfab4", getString(R.string.factory_4) + " " + getString(R.string.level10));
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
        /*
      ATTENTION: This was auto-generated to implement the App Indexing API.
      See https://g.co/AppIndexing/AndroidStudio for more information.
     */
        client= new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    @Override
    public void onClick(View v) {


        //FAbrik 1
        datafab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");


        int id = v.getId();
        if (id == R.id.Fabrik1) {

            dialogFabr1();
        }

        if (id == R.id.SammelnFabrik1) {

            sammelnfab1();
        }


        //FAbrik 2
        datafab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");

        if (id == R.id.Fabrik2) {

            dialogFabr2();
        }

        if (id == R.id.SammelnFabrik2) {

            sammelnfab2();
        }

        //FAbrik 3
        datafab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");

        if (id == R.id.Fabrik3) {

            dialogFabr3();
        }

        if (id == R.id.SammelnFabrik3) {

            sammelnfab3();
        }

        //FAbrik 4
        datafab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");

        if (id == R.id.Fabrik4) {

            dialogFabr4();
        }

        if (id == R.id.SammelnFabrik4) {

            sammelnfab4();
        }


        //Bank
        MyPreferences dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
        if (id == R.id.Bank) {
            Intent start = new Intent(MainActivity.this, BankActivity.class);
            MainActivity.this.startActivity(start);

            finish();
        }
        //Bauhaus
        if (id == R.id.bauhaus) {
            MyPreferences dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");


            View bauhausdia = View.inflate(this, R.layout.bauhausdialog, null);
            bauhausdialog = new Dialog(v.getContext());
            bauhausdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bauhausdialog.setContentView(bauhausdia);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(bauhausdialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;

            closeBauhaus = (Button) bauhausdialog.findViewById(R.id.closeBauhaus);
            closeBauhaus.setOnClickListener(this);

            goldtextarch = (TextView) bauhausdialog.findViewById(R.id.goldpoolarch);


            //Fabrik1
            upgradefab1 = (Button) bauhausdialog.findViewById(R.id.upgradefab1);
            upgradefab1.setOnClickListener(this);
            leveltextfabr1 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr1);
            leveltextfabr1.setOnClickListener(this);
            leveltextfabr1.setTypeface(typeface);
            upcdfab1 = (TextView) bauhausdialog.findViewById(R.id.upcdfab1);
            upcdfab1.setTypeface(typeface);
            leveltextfabr1.setText(datafab1.getString("leveltextfab1", "1"));
            if (datafab1.getInt("Level", 1) == 10) {
                upgradefab1.setBackgroundResource(R.mipmap.nolevelupbtn);
            }


            //Fabrik2
            upgradefab2 = (Button) bauhausdialog.findViewById(R.id.upgradefab2);
            upgradefab2.setOnClickListener(this);
            leveltextfabr2 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr2);
            leveltextfabr2.setTypeface(typeface);
            leveltextfabr2.setOnClickListener(this);
            upcdfab2 = (TextView) bauhausdialog.findViewById(R.id.upcdfab2);
            upcdfab2.setTypeface(typeface);
            leveltextfabr2.setText(datafab2.getString("leveltextfab2", "2"));
            datafab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab2.getInt("Level", 0) >= 1) {

                upgradefab2.setBackgroundResource(R.drawable.levelupbtns);
                if (datafab2.getInt("Level", 0) == 10) {
                    upgradefab2.setBackgroundResource(R.mipmap.nolevelupbtn);
                }
                }


            if (datafab2.getInt("Level", 0) < 1) {
                if (datafab2.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab2.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab2.getBoolean("bedingungenerfüllt", false)) {

                    upgradefab2.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }

            //Fabrik3
            upgradefab3 = (Button) bauhausdialog.findViewById(R.id.upgradefab3);
            upgradefab3.setOnClickListener(this);
            leveltextfabr3 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr3);
            leveltextfabr3.setTypeface(typeface);
            leveltextfabr3.setOnClickListener(this);
            upcdfab3 = (TextView) bauhausdialog.findViewById(R.id.upcdfab3);
            upcdfab3.setTypeface(typeface);
            leveltextfabr3.setText(datafab3.getString("leveltextfab3", "3"));
            datafab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab3.getInt("Level", 0) >= 1) {

                upgradefab3.setBackgroundResource(R.drawable.levelupbtns);
                if (datafab3.getInt("Level", 0) == 10) {
                    upgradefab3.setBackgroundResource(R.mipmap.nolevelupbtn);
                }

            }
            if (datafab3.getInt("Level", 0) < 1) {
                if (datafab3.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab3.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab3.getBoolean("bedingungenerfüllt", false)) {

                    upgradefab3.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }

            //Fabrik4
            upgradefab4 = (Button) bauhausdialog.findViewById(R.id.upgradefab4);
            upgradefab4.setOnClickListener(this);
            leveltextfabr4 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr4);
            leveltextfabr4.setTypeface(typeface);
            upcdfab4 = (TextView) bauhausdialog.findViewById(R.id.upcdfab4);
            upcdfab4.setTypeface(typeface);
            leveltextfabr4.setOnClickListener(this);
            leveltextfabr4.setText(datafab4.getString("leveltextfab4", "4"));
            datafab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");
            //btn auf upgrade wechseln, wenn gebàude gebaut
            if (datafab4.getInt("Level", 0) >= 1) {

                upgradefab4.setBackgroundResource(R.drawable.levelupbtns);
                if (datafab4.getInt("Level", 0) == 10) {
                    upgradefab4.setBackgroundResource(R.mipmap.nolevelupbtn);
                }

            }
            if (datafab4.getInt("Level", 0) < 1) {
                if (datafab4.getBoolean("bedingungenerfüllt", false)) {
                    upgradefab4.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!datafab4.getBoolean("bedingungenerfüllt", false)) {

                    upgradefab4.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }


            //Bank
            upgradeBank = (Button) bauhausdialog.findViewById(R.id.upgradeBank);
            upgradeBank.setOnClickListener(this);
            upcdBank = (TextView) bauhausdialog.findViewById(R.id.upcdBank);
            upcdBank.setTypeface(typeface);
            leveltextBank = (TextView) bauhausdialog.findViewById(R.id.leveltextBank);
            leveltextBank.setTypeface(typeface);
            leveltextBank.setOnClickListener(this);
            leveltextBank.setText(dataBank.getString("leveltext", "1"));
            if (dataBank.getInt("Level", 1) == 4) {
                upgradeBank.setBackgroundResource(R.mipmap.nolevelupbtn);
            }

            //Agency
            upgradeAgency = (Button) bauhausdialog.findViewById(R.id.upgradeAgentur);
            upgradeAgency.setOnClickListener(this);
            upcdAgency = (TextView) bauhausdialog.findViewById(R.id.upcdAgentur);
            upcdAgency.setTypeface(typeface);
            leveltextAgency = (TextView) bauhausdialog.findViewById(R.id.leveltextAgentur);
            leveltextAgency.setTypeface(typeface);
            leveltextAgency.setOnClickListener(this);
            if (dataAgency.getInt("Level", 0) == 1) {
                upgradeAgency.setBackgroundResource(R.mipmap.nolevelupbtn);
            }


            leveltextAgency.setText(dataAgency.getString("leveltext", "Agency"));

            //btn auf upgrade wechseln, wenn gebàude gebaut
          /*  if (dataAgency.getInt("Level", 0) >= 1) {

                upgradeAgency.setBackgroundResource(R.drawable.levelupbtns);

            }*/
            if (dataAgency.getInt("Level", 0) < 1) {
                if (dataAgency.getBoolean("bedingungenerfüllt", false)) {
                    upgradeAgency.setBackgroundResource(R.drawable.buildbtns);
                }
                if (!dataAgency.getBoolean("bedingungenerfüllt", false)) {

                    upgradeAgency.setBackgroundResource(R.mipmap.nobuildbtn);
                }
            }


            bauhausdialog.show();
            bauhausdialog.getWindow().setAttributes(lp);


            //Aktualisator bauhaus

            h.postDelayed(new Runnable() {
                public void run() {
                    //jede sec upgradecountdown aktualisieren


                    //Aktualisiere den Kontostatus
                    MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    String Pooltext = String.valueOf(prefs1.getInt("POOL", 0));
                    goldtextarch.setText(": " + Pooltext);

                    //BAnk
                    MyPreferences dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
                    Boolean cdBank = (dataBank.getBoolean("isLeveling", false));

                    MyPreferences BankupgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeBank");
                    int startTimeBank = BankupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownBank = BankupgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeBank = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsBank = endTimeBank - startTimeBank;
                    int restTimeBank = countdownBank - elapsedSecondsBank;
                    String dhmsBank;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdBank) {
                        upcdBank.setText("");
                    }
                    if (cdBank) {

                        if (restTimeBank >= 0) {
                            dhmsBank = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimeBank),
                                    TimeUnit.SECONDS.toHours(restTimeBank) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimeBank)),
                                    TimeUnit.SECONDS.toMinutes(restTimeBank) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimeBank)),
                                    TimeUnit.SECONDS.toSeconds(restTimeBank) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimeBank)));
                            upcdBank.setText(dhmsBank);
                        }
                        if (restTimeBank < 0) {
                            upcdBank.setText("00:00:00:00");
                        }
                    }

                    //Agency
                    MyPreferences dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                    Boolean cdAgency = (dataAgency.getBoolean("isLeveling", false));

                    MyPreferences AgencyupgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeAgency");
                    int startTimeAgency = AgencyupgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownAgency = AgencyupgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimeAgency = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsAgency = endTimeAgency - startTimeAgency;
                    int restTimeAgency = countdownAgency - elapsedSecondsAgency;
                    String dhmsAgency;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdAgency) {
                        upcdAgency.setText("");
                    }
                    if (cdAgency) {

                        if (restTimeAgency >= 0) {
                            dhmsAgency = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimeAgency),
                                    TimeUnit.SECONDS.toHours(restTimeAgency) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimeAgency)),
                                    TimeUnit.SECONDS.toMinutes(restTimeAgency) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(restTimeAgency)),
                                    TimeUnit.SECONDS.toSeconds(restTimeAgency) - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(restTimeAgency)));
                            upcdAgency.setText(dhmsAgency);
                        }
                        if (restTimeAgency < 0) {
                            upcdAgency.setText("00:00:00:00");
                        }
                    }


                    //Fabrik1
                    Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));

                    MyPreferences Fab1upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                    int startTimefab1 = Fab1upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab1 = Fab1upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab1 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab1 = endTimefab1 - startTimefab1;
                    int restTimefab1 = countdownfab1 - elapsedSecondsfab1;
                    String dhmsfab1;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab1) {
                        upcdfab1.setText("");
                    }
                    if (cdfab1) {

                        if (restTimefab1 >= 0) {
                            dhmsfab1 = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab1),
                                    TimeUnit.SECONDS.toHours(restTimefab1) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimefab1)),
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

                    MyPreferences Fab2upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                    int startTimefab2 = Fab2upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab2 = Fab2upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab2 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab2 = endTimefab2 - startTimefab2;
                    int restTimefab2 = countdownfab2 - elapsedSecondsfab2;
                    String dhmsfab2;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab2) {
                        upcdfab2.setText("");
                    }
                    if (cdfab2) {

                        if (restTimefab2 >= 0) {
                            dhmsfab2 = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab2),
                                    TimeUnit.SECONDS.toHours(restTimefab2) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimefab2)),
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

                    MyPreferences Fab3upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                    int startTimefab3 = Fab3upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab3 = Fab3upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab3 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab3 = endTimefab3 - startTimefab3;
                    int restTimefab3 = countdownfab3 - elapsedSecondsfab3;
                    String dhmsfab3;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab3) {
                        upcdfab3.setText("");
                    }
                    if (cdfab3) {

                        if (restTimefab3 >= 0) {
                            dhmsfab3 = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab3),
                                    TimeUnit.SECONDS.toHours(restTimefab3) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimefab3)),
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

                    MyPreferences Fab4upgradeContdown = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                    int startTimefab4 = Fab4upgradeContdown.getInt("startTime", 0); //0 is the default value.
                    int countdownfab4 = Fab4upgradeContdown.getInt("countdown", 0); //0 is the default value.
                    //endzeit als jetzt definieren
                    int endTimefab4 = ((int) System.currentTimeMillis() / 1000);
                    int elapsedSecondsfab4 = endTimefab4 - startTimefab4;
                    int restTimefab4 = countdownfab4 - elapsedSecondsfab4;
                    String dhmsfab4;
                    //wenn nicht am leveln, counter ausblenden
                    if (!cdfab4) {
                        upcdfab4.setText("");
                    }
                    if (cdfab4) {

                        if (restTimefab4 >= 0) {
                            dhmsfab4 = String.format(Locale.US, "%02d:%02d:%02d:%02d", TimeUnit.SECONDS.toDays(restTimefab4),
                                    TimeUnit.SECONDS.toHours(restTimefab4) - TimeUnit.DAYS.toHours(TimeUnit.SECONDS.toDays(restTimefab4)),
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

            if(bauhausdialog!=null){
if(bauhausdialog.isShowing()) {
    bauhausdialog.dismiss();
}
}
            if(dialog!=null){
                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

            }
            //tutorial

            SharedPreferences hangar = getSharedPreferences("firstope", MODE_PRIVATE);
            Boolean hangtut = hangar.getBoolean("showHangarTutorial", false);
            if (hangtut) {

                Intent intent = new Intent(this, hangartut.class);
                startActivity(intent);

                SharedPreferences.Editor firstopen1 = getSharedPreferences("firstope", MODE_PRIVATE).edit();
                firstopen1.putBoolean("showHangarTutorial", false);
                firstopen1.apply();
            }


    }

//SYNC

        //race
        if (id == R.id.werkstatt) {
            //Dias sync
            final MyPreferences google = MyPreferences.getInstance(MainActivity.this,"google");
            String dat = "request";
            String gid =google.getString("id", "0");;
            bgworkerdias lol = new bgworkerdias(context);
            lol.execute(dat, gid, "diacollect");

            Intent intent = new Intent(this, multiplayer.class);
            startActivity(intent);
        }
        //agency
        if (id == R.id.openagency) {

            Intent intent = new Intent(this, AgencyActivity.class);
            startActivity(intent);
        }


        //Agency
        if (id == R.id.upgradeAgentur | id == R.id.leveltextAgentur) {

            dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
            Boolean cdAgency = dataAgency.getBoolean("isLeveling", false);

            //wenn ein upgrade am laufen ist, toast machen
            if (cdAgency) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();


            }

            //sonst, upgradedialog
            if (!cdAgency) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.agency));

                // set dialog message

                if (dataAgency.getInt("Level", 0) == 0) {
                    //text Agency lvl1
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1), ": \n\n", getString(R.string.agencybenefits), "\n\n", getString(R.string.Costs), "200", getString(R.string.Gold), " \n \n", getString(R.string.time), "5:00:00"));
                }


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(getString(R.string.upgrade), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {


                                //Upgrade
                                MyPreferences dataAgency = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");


                                //Level 1
                                if (dataAgency.getInt("Level", 0) == 0) {

                                    Boolean bederfüllt = dataAgency.getBoolean("bedingungenerfüllt", false);


                                    //bedingung erfüllt?
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {


                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");


                                        if ((prefs.getInt("POOL", 0)) < 100) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 100) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 100));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeAgency");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //// TODO: 3600
                                            editor3.putInt("countdown", 36);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"dataAgency");
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();


                                        }
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


        //BANK
        if (id == R.id.upgradeBank | id == R.id.leveltextBank) {

            dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
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
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2), ": \n\n", getString(R.string.goldstorage), " 1000", Html.fromHtml(" <font color=#00ff00> + 19000</font>"), "\n\n ", getString(R.string.unlock), " \n \n", getString(R.string.factories), " ", getString(R.string.level4), ",", getString(R.string.factory_2), ", ", getString(R.string.agency), " \n \n", getString(R.string.Costs), "100", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00:00"));
                }
                if (dataBank.getInt("Level", 1) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3), ": \n\n", getString(R.string.goldstorage), "20000", Html.fromHtml(" <font color=#00ff00> + 55000</font>"), "\n\n ", getString(R.string.Costs), "20000", getString(R.string.Gold), " \n \n", getString(R.string.time), "5:00:00"));
                }
                if (dataBank.getInt("Level", 1) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4), ": \n\n", getString(R.string.goldstorage), "75000", Html.fromHtml(" <font color=#00ff00> + 75000</font>"), "\n \n", getString(R.string.Costs), "75000", getString(R.string.Gold), " \n \n", getString(R.string.time), "2d"));
                }


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(getString(R.string.upgrade), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {


                                //Upgrade
                                MyPreferences dataBank = MyPreferences.getInstance(MainActivity.this,"dataBank");
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");

                                Boolean bederfüllt = dataBank.getBoolean("bedingungenerfüllt", false);
                                Boolean bed2erfüllt = dataBank.getBoolean("bedingungen2erfüllt", false);


                                //Level 2
                                if (dataBank.getInt("Level", 1) == 1) {

                                    //bedingung erfüllt?
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.factory_1) + "  " + getString(R.string.zu) + "  " + getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                                        if ((prefs.getInt("POOL", 0)) < 100) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 100) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 100));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeBank");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            //TODO 3600
                                            editor3.putInt("countdown", 36);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"dataBank");
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

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.factory_1) + "  " + "+2  " + getString(R.string.zu) + "  " + getString(R.string.level6), Toast.LENGTH_SHORT).show();
                                    }
                                    if (!bed2erfüllt) {
                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.factory_1) + "  " + "+2  " + getString(R.string.zu) + "  " + getString(R.string.level6), Toast.LENGTH_SHORT).show();

                                    }
                                    if (bederfüllt) {

                                        if (bed2erfüllt) {

                                            MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                            if ((prefs.getInt("POOL", 0)) < 20000) {
                                                Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                            }
                                            if ((prefs.getInt("POOL", 0)) >= 20000) {
                                                //bezahlen
                                                editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                                editor2.apply();

                                                //Countdown Starten
                                                MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeBank");
                                                editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                                //Countdownzeit definieren
                                                editor3.putInt("countdown", 18000);
                                                editor3.apply();

                                                MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"dataBank");
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
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) < 75000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 75000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 75000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeBank");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 172800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"dataBank");
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
        if (id == R.id.upgradefab1 | id == R.id.leveltextfabr1) {


            //ist ein upgrade am laufen?


            datafab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {

                Toast.makeText(MainActivity.this, R.string.waitforupgradefinish, Toast.LENGTH_SHORT).show();


            }

            if (!cdfab1) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.factory_1));

                // set dialog message

                if (datafab1.getInt("Level", 1) == 1) {
                    //text lvl2


                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2), ": \n\n", getString(R.string.gold_per_hour), " 180", Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ", getString(R.string.storage), " 500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.unlock), " \n \n", getString(R.string.bank), " ", getString(R.string.level2), "\n\n", getString(R.string.Costs), "50", getString(R.string.Gold), " \n \n", getString(R.string.time), "2:00"));

                }
                if (datafab1.getInt("Level", 1) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3), ": \n\n", getString(R.string.gold_per_hour), "360", Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ", getString(R.string.storage), " 1000 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "500", getString(R.string.Gold), " \n \n", getString(R.string.time), "10:00"));
                }
                if (datafab1.getInt("Level", 1) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4), ": \n\n", getString(R.string.gold_per_hour), "600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 1500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "1500", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00:00"));
                }
                if (datafab1.getInt("Level", 1) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5), ": \n\n", getString(R.string.gold_per_hour), "800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 2000 ", Html.fromHtml("<font color=#00ff00> + 3000</font>"), " \n \n", getString(R.string.Costs), "5000", getString(R.string.Gold), " \n \n", getString(R.string.time), "6:00:00"));
                }

                if (datafab1.getInt("Level", 1) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6), ": \n\n", getString(R.string.gold_per_hour), "1000", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 5000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "20000", getString(R.string.Gold), " \n \n", getString(R.string.time), "10:00:00"));
                }

                if (datafab1.getInt("Level", 1) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7), ": \n\n", getString(R.string.gold_per_hour), "1200", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 10000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "35000", getString(R.string.Gold), " \n \n", getString(R.string.time), "12:00:00"));
                }

                if (datafab1.getInt("Level", 1) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8), ": \n\n", getString(R.string.gold_per_hour), "1400", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 15000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "70000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1d"));
                }

                if (datafab1.getInt("Level", 1) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9), ": \n\n", getString(R.string.gold_per_hour), "1600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 20000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "110000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1.5d"));
                }

                if (datafab1.getInt("Level", 1) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10), ": \n\n", getString(R.string.gold_per_hour), "1800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 25000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "150000", getString(R.string.Gold), " \n \n", getString(R.string.time), "2d"));
                }


                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(getString(R.string.upgrade), new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                Boolean bederfüllt = datafab1.getBoolean("bedingungenerfüllt", false);


                                //Upgrade
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");

                                if (datafab1.getInt("Level", 1) == 1) {

                                    //LeveL2

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 120
                                        editor3.putInt("countdown", 10);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();


                                        MyPreferences firstopen1 = MyPreferences.getInstance(MainActivity.this,"firstope");
                                        firstopen1.putBoolean("showHangarTutorial", true);
                                        firstopen1.apply();
                                    }


                                }

                                //LEVEL3

                                if (datafab1.getInt("Level", 1) == 2) {
                                    //bedingung erfüllt?

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        //bedingung ist nicht mehr erfüllt für die kommenden lvl
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 3) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

                                        //LEVEL4
                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                                        if ((prefs.getInt("POOL", 0)) <= 1500) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 1500) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1500));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            editor3.putInt("countdown", 3600);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }

                                }

                                if (datafab1.getInt("Level", 1) == 4) {

                                    //LEVEL5
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 5000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 5000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 5000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 21600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 5) {

                                    //LEVEL6
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 36000);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab1.getInt("Level", 1) == 6) {

                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {

                                        //LEVEL7
                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                        if ((prefs.getInt("POOL", 0)) <= 35000) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 35000) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 35000));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            editor3.putInt("countdown", 43200);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                            editor1.putBoolean("isLeveling", true);
                                            editor1.apply();

                                        }
                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 7) {

                                    //LEVEL8
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 70000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 70000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 70000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 86400);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 8) {

                                    //LEVEL9
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 110000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 110000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 110000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 129600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab1.getInt("Level", 1) == 9) {

                                    //LEVEL10
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 150000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 150000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 150000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab1");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 172800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab1");
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
        if (id == R.id.upgradefab2 | id == R.id.leveltextfabr2) {


            //ist ein upgrade am laufen?


            datafab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");
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
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1), ": \n\n", getString(R.string.gold_per_hour), " 180", "\n\n ", getString(R.string.storage), " 500 ", " \n \n", getString(R.string.Costs), "500", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00"));
                }
                if (datafab2.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2), ": \n\n", getString(R.string.gold_per_hour), " 180", Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ", getString(R.string.storage), " 500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), "\n\n", getString(R.string.Costs), "50", getString(R.string.Gold), " \n \n", getString(R.string.time), "30:00"));
                }
                if (datafab2.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3), ": \n\n", getString(R.string.gold_per_hour), "360", Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ", getString(R.string.storage), " 1000 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "500", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00:00"));
                }
                if (datafab2.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4), ": \n\n", getString(R.string.gold_per_hour), "600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 1500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "1500", getString(R.string.Gold), " \n \n", getString(R.string.time), "3:00:00"));
                }
                if (datafab2.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5), ": \n\n", getString(R.string.gold_per_hour), "800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 2000 ", Html.fromHtml("<font color=#00ff00> + 3000</font>"), " \n \n", getString(R.string.Costs), "5000", getString(R.string.Gold), " \n \n", getString(R.string.time), "6:00:00"));
                }

                if (datafab2.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6), ": \n\n", getString(R.string.gold_per_hour), "1000", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 5000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "20000", getString(R.string.Gold), " \n \n", getString(R.string.time), "10:00:00"));
                }

                if (datafab2.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7), ": \n\n", getString(R.string.gold_per_hour), "1200", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 10000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "35000", getString(R.string.Gold), " \n \n", getString(R.string.time), "12:00:00"));
                }

                if (datafab2.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8), ": \n\n", getString(R.string.gold_per_hour), "1400", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 15000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "70000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1d"));
                }

                if (datafab2.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9), ": \n\n", getString(R.string.gold_per_hour), "1600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 20000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "110000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1.5d"));
                }

                if (datafab2.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10), ": \n\n", getString(R.string.gold_per_hour), "1800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 25000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "150000", getString(R.string.Gold), " \n \n", getString(R.string.time), "2d"));
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
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");

                                //bedingung erfüllt?


                                if (datafab2.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level2), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        //LeveL1

                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                        if ((prefs.getInt("POOL", 0)) < 500) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 500) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            editor3.putInt("countdown", 60);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                            editor1.putBoolean("isLeveling", true);
                                            //bedingungen nicht mehr erfüllt
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }
                                }

                                if (datafab2.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 1800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab2.getInt("Level", 0) == 2) {

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 3600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);

                                        editor1.apply();

                                    }
                                }


                                if (datafab2.getInt("Level", 0) == 3) {

                                    //LEVEL4
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                                    if ((prefs.getInt("POOL", 0)) <= 1500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 1500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 10800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 4) {

                                    //LEVEL5
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 5000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 5000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 5000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 21600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 5) {

                                    //LEVEL6
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 36000);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab2.getInt("Level", 0) == 6) {


                                    //LEVEL7
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 35000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 35000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 35000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 43200);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab2.getInt("Level", 0) == 7) {

                                    //LEVEL8
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 70000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 70000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 70000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 86400);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab2.getInt("Level", 0) == 8) {

                                    //LEVEL9
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 110000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 110000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 110000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 129000);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab2.getInt("Level", 0) == 9) {

                                    //LEVEL10
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 150000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 150000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 150000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab2");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 172800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab2");
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
        if (id == R.id.upgradefab3 | id == R.id.leveltextfabr3) {


            //ist ein upgrade am laufen?


            datafab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");
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
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1), ": \n\n", getString(R.string.gold_per_hour), " 180", "\n\n ", getString(R.string.storage), " 500 ", " \n \n", getString(R.string.Costs), "1000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00"));
                }
                if (datafab3.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2), ": \n\n", getString(R.string.gold_per_hour), " 180", Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ", getString(R.string.storage), " 500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), "\n\n", getString(R.string.Costs), "50", getString(R.string.Gold), " \n \n", getString(R.string.time), "30:00"));
                }
                if (datafab3.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3), ": \n\n", getString(R.string.gold_per_hour), "360", Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ", getString(R.string.storage), " 1000 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "500", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00:00"));
                }
                if (datafab3.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4), ": \n\n", getString(R.string.gold_per_hour), "600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 1500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "1500", getString(R.string.Gold), " \n \n", getString(R.string.time), "3:00:00"));
                }
                if (datafab3.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5), ": \n\n", getString(R.string.gold_per_hour), "800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 2000 ", Html.fromHtml("<font color=#00ff00> + 3000</font>"), " \n \n", getString(R.string.Costs), "5000", getString(R.string.Gold), " \n \n", getString(R.string.time), "6:00:00"));
                }

                if (datafab3.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6), ": \n\n", getString(R.string.gold_per_hour), "1000", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 5000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "20000", getString(R.string.Gold), " \n \n", getString(R.string.time), "10:00:00"));
                }

                if (datafab3.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7), ": \n\n", getString(R.string.gold_per_hour), "1200", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 10000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "35000", getString(R.string.Gold), " \n \n", getString(R.string.time), "12:00:00"));
                }

                if (datafab3.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8), ": \n\n", getString(R.string.gold_per_hour), "1400", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 15000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "70000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1d"));
                }

                if (datafab3.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9), ": \n\n", getString(R.string.gold_per_hour), "1600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 20000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "110000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1.5d"));
                }

                if (datafab3.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10), ": \n\n", getString(R.string.gold_per_hour), "1800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 25000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "150000", getString(R.string.Gold), " \n \n", getString(R.string.time), "2d"));
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
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");

                                //bedingung erfüllt?


                                if (datafab3.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level3), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        //LeveL1

                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                        if ((prefs.getInt("POOL", 0)) < 1000) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 1000) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1000));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            editor3.putInt("countdown", 60);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                            editor1.putBoolean("isLeveling", true);
                                            //bedingungen nicht mehr erfüllt
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }
                                }

                                if (datafab3.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 1800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab3.getInt("Level", 0) == 2) {

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 3600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);

                                        editor1.apply();

                                    }
                                }


                                if (datafab3.getInt("Level", 0) == 3) {

                                    //LEVEL4
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                                    if ((prefs.getInt("POOL", 0)) <= 1500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 1500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 10800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 4) {

                                    //LEVEL5
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 5000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 5000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 5000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 21600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 5) {

                                    //LEVEL6
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 36000);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab3.getInt("Level", 0) == 6) {


                                    //LEVEL7
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 35000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 35000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 35000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 43200);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab3.getInt("Level", 0) == 7) {

                                    //LEVEL8
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 70000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 70000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 70000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 86400);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab3.getInt("Level", 0) == 8) {

                                    //LEVEL9
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 110000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 110000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 110000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 129600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab3.getInt("Level", 0) == 9) {

                                    //LEVEL10
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 150000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 150000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 150000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab3");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 172800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab3");
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
        if (id == R.id.upgradefab4 | id == R.id.leveltextfabr4) {


            //ist ein upgrade am laufen?


            datafab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");
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
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level1), ": \n\n", getString(R.string.gold_per_hour), " 180", "\n\n ", getString(R.string.storage), " 500 ", " \n \n", getString(R.string.Costs), "10000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00"));
                }
                if (datafab4.getInt("Level", 0) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level2), ": \n\n", getString(R.string.gold_per_hour), " 180", Html.fromHtml(" <font color=#00ff00> + 180</font>"), "\n\n ", getString(R.string.storage), " 500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), "\n\n", getString(R.string.Costs), "50", getString(R.string.Gold), " \n \n", getString(R.string.time), "30:00"));
                }
                if (datafab4.getInt("Level", 0) == 2) {
                    //text lvl3
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level3), ": \n\n", getString(R.string.gold_per_hour), "360", Html.fromHtml(" <font color=#00ff00> + 220</font>"), "\n\n ", getString(R.string.storage), " 1000 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "500", getString(R.string.Gold), " \n \n", getString(R.string.time), "1:00:00"));
                }
                if (datafab4.getInt("Level", 0) == 3) {
                    //text lvl4
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level4), ": \n\n", getString(R.string.gold_per_hour), "600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 1500 ", Html.fromHtml("<font color=#00ff00> + 500</font>"), " \n \n", getString(R.string.Costs), "1500", getString(R.string.Gold), " \n \n", getString(R.string.time), "3:00:00"));
                }
                if (datafab4.getInt("Level", 0) == 4) {
                    //text lvl5
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level5), ": \n\n", getString(R.string.gold_per_hour), "800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 2000 ", Html.fromHtml("<font color=#00ff00> + 3000</font>"), " \n \n", getString(R.string.Costs), "5000", getString(R.string.Gold), " \n \n", getString(R.string.time), "6:00:00"));
                }

                if (datafab4.getInt("Level", 0) == 5) {
                    //text lvl6
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level6), ": \n\n", getString(R.string.gold_per_hour), "1000", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 5000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "20000", getString(R.string.Gold), " \n \n", getString(R.string.time), "10:00:00"));
                }

                if (datafab4.getInt("Level", 0) == 6) {
                    //text lvl7
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level7), ": \n\n", getString(R.string.gold_per_hour), "1200", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 10000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "35000", getString(R.string.Gold), " \n \n", getString(R.string.time), "12:00:00"));
                }

                if (datafab4.getInt("Level", 0) == 7) {
                    //text lvl8
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level8), ": \n\n", getString(R.string.gold_per_hour), "1400", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 15000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "70000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1d"));
                }

                if (datafab4.getInt("Level", 0) == 8) {
                    //text lvl9
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level9), ": \n\n", getString(R.string.gold_per_hour), "1600", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 20000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "110000", getString(R.string.Gold), " \n \n", getString(R.string.time), "1.5d"));
                }

                if (datafab4.getInt("Level", 0) == 9) {
                    //text lvl10
                    alertDialogBuilder.setMessage(TextUtils.concat(getString(R.string.level10), ": \n\n", getString(R.string.gold_per_hour), "1800", Html.fromHtml(" <font color=#00ff00> + 200</font>"), "\n\n ", getString(R.string.storage), " 25000 ", Html.fromHtml("<font color=#00ff00> + 5000</font>"), " \n \n", getString(R.string.Costs), "150000", getString(R.string.Gold), " \n \n", getString(R.string.time), "2d"));
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
                                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"POOL");

                                //bedingung erfüllt?


                                if (datafab4.getInt("Level", 0) == 0) {
                                    if (!bederfüllt) {

                                        Toast.makeText(MainActivity.this, getString(R.string.upgrade) + "  " + getString(R.string.bank) + "  " + getString(R.string.zu) + "  " + getString(R.string.level4), Toast.LENGTH_SHORT).show();


                                    }
                                    if (bederfüllt) {
                                        //LeveL1

                                        MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                        if ((prefs.getInt("POOL", 0)) < 10000) {
                                            Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                        }
                                        if ((prefs.getInt("POOL", 0)) >= 10000) {
                                            //bezahlen
                                            editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 10000));
                                            editor2.apply();

                                            //Countdown Starten
                                            MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                            editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                            //Countdownzeit definieren
                                            editor3.putInt("countdown", 60);
                                            editor3.apply();

                                            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                            editor1.putBoolean("isLeveling", true);
                                            //bedingungen nicht mehr erfüllt
                                            editor1.putBoolean("bedingungenerfüllt", false);
                                            editor1.apply();

                                        }
                                    }
                                }

                                if (datafab4.getInt("Level", 0) == 1) {

                                    //LeveL2

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) < 50) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 1800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                //LEVEL3

                                if (datafab4.getInt("Level", 0) == 2) {

                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 3600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);

                                        editor1.apply();

                                    }
                                }


                                if (datafab4.getInt("Level", 0) == 3) {

                                    //LEVEL4
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                                    if ((prefs.getInt("POOL", 0)) <= 1500) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 1500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 1500));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 10800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 4) {

                                    //LEVEL5
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 5000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 5000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 5000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 21600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 5) {

                                    //LEVEL6
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 20000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 20000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 20000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 36000);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }

                                if (datafab4.getInt("Level", 0) == 6) {


                                    //LEVEL7
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 35000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 35000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 35000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 43200);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab4.getInt("Level", 0) == 7) {

                                    //LEVEL8
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 70000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 70000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 70000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 86400);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab4.getInt("Level", 0) == 8) {

                                    //LEVEL9
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 110000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 110000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 110000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 129600);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.apply();

                                    }


                                }
                                if (datafab4.getInt("Level", 0) == 9) {

                                    //LEVEL10
                                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                                    if ((prefs.getInt("POOL", 0)) <= 150000) {
                                        Toast.makeText(MainActivity.this, getString(R.string.Cantafford), Toast.LENGTH_SHORT).show();

                                    }
                                    if ((prefs.getInt("POOL", 0)) >= 150000) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 150000));
                                        editor2.apply();

                                        //Countdown Starten
                                        MyPreferences editor3 = MyPreferences.getInstance(MainActivity.this,"startTimeUpgradeFab4");
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);


                                        //Countdownzeit definieren
                                        editor3.putInt("countdown", 172800);
                                        editor3.apply();

                                        MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"datafab4");
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
    public void dialogFabr1() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik1);

        closeBauhaus = (Button) dialog.findViewById(R.id.closeBauhaus);
        closeBauhaus.setOnClickListener(this);


        datafab1 = MyPreferences.getInstance(MainActivity.this,"datafab1");


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

                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
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

                String maxgo = strI + "/" + "" +
                        datafab1.getInt("maxfabrik1", 500);

                countfab1.setText(maxgo);

                double goldphfab160 = datafab1.getFloat("goldphfab1", (float) 0.05) * 60 * 60;

                int goldphfab1int = (int) goldphfab160;

                String goph = getString(R.string.gold_per_hour) + "" +
                        goldphfab1int;
                GperH.setText(goph);


                h.postDelayed(this, delay);
            }
        }, delay);


        dialog.show();


    }

    public void sammelnfab1() {


        MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.


        int endTime = ((int) System.currentTimeMillis() / 1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab1.getFloat("goldphfab1", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int) (gold);

        //checken ob das max schon erreicht wurde

        if (goldint >= datafab1.getInt("maxfabrik1", 500)) {

            goldint = datafab1.getInt("maxfabrik1", 500);
        }

        //schauen, ist der pool schon auf maxStorage der bank?
        MyPreferences BankMax = MyPreferences.getInstance(MainActivity.this,"dataBank");
        int intBankmax = BankMax.getInt("maxGoldStorage", 1000);
        MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"POOL");
        if (intBankmax <= (prefs1.getInt("POOL", 0))) {

            Toast.makeText(MainActivity.this, getString(R.string.Bankisfull), Toast.LENGTH_SHORT).show();
        }


        if (intBankmax > (prefs1.getInt("POOL", 0))) {


            MyPreferences doubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
            Boolean doblegold = doubler.getBoolean("doublecoll", false);





            if(!doblegold) {
                //rücklauf falls das max erfüllt wurde(nicht vorhanden

                int vorhandenesgold = (goldint + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab1.getFloat("goldphfab1", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (collect), Toast.LENGTH_SHORT).show();
                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {
                    //Timer Zurücksetzen
                    MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor1.apply();

                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL", goldint + (prefs1.getInt("POOL", 0)));
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

                }

            }
        if(doblegold) {
            //rücklauf falls das max erfüllt wurde(nicht vorhanden

            int vorhandenesgold = (goldint*2 + prefs1.getInt("POOL", 0));
            if (vorhandenesgold > intBankmax) {
                int uberlauf = vorhandenesgold - intBankmax;
                int collect = goldint*2 - uberlauf;


                float zeitabzugtemp = (uberlauf / datafab1.getFloat("goldphfab1", (float) 0.05));

                int zeitabzug = (int) zeitabzugtemp;

                //Timer Zurücksetzen
                MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                editor1.apply();

                //gold dem pool hinzufügen
                MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                editor2.putInt("POOL",intBankmax);
                editor2.apply();

                MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                dedubler.putBoolean("doublecoll", false);
                dedubler.apply();
                Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + collect, Toast.LENGTH_SHORT).show();

            }
                //kein überlauf
            if (vorhandenesgold <= intBankmax) {

                //gold dem pool hinzufügen
                MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                editor2.putInt("POOL", (goldint * 2) + (prefs1.getInt("POOL", 0)));
                editor2.apply();

                MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                dedubler.putBoolean("doublecoll", false);
                dedubler.apply();
                Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + (goldint * 2), Toast.LENGTH_SHORT).show();
                //Timer Zurücksetzen
                MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeit1");
                editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) );
                editor1.apply();
            }
        }



        }

    }


    //Fabrik2
    public void dialogFabr2() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik2);
        closeBauhaus = (Button) dialog.findViewById(R.id.closeBauhaus);
        closeBauhaus.setOnClickListener(this);

        datafab2 = MyPreferences.getInstance(MainActivity.this,"datafab2");


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

                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");
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
                String maxgo = strI + "/" + "" +
                        datafab2.getInt("maxfabrik2", 500);

                countfab2.setText(maxgo);

                double goldphfab160 = datafab2.getFloat("goldphfab2", (float) 0.05) * 60 * 60;

                int goldphfab1int = (int) goldphfab160;

                String goph = getString(R.string.gold_per_hour) + "" +
                        goldphfab1int;
                GperH2.setText(goph);


                h.postDelayed(this, delay);
            }
        }, delay);


        dialog.show();


    }

    public void sammelnfab2() {


        MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.


        int endTime = ((int) System.currentTimeMillis() / 1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab2.getFloat("goldphfab2", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int) (gold);

        //checken ob das max schon erreicht wurde

        if (goldint >= datafab2.getInt("maxfabrik2", 500)) {

            goldint = datafab2.getInt("maxfabrik2", 500);
        }

        //schauen, ist der pool schon auf maxStorage der bank?
        MyPreferences BankMax = MyPreferences.getInstance(MainActivity.this,"dataBank");
        int intBankmax = BankMax.getInt("maxGoldStorage", 1000);
        MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"POOL");
        if (intBankmax <= (prefs1.getInt("POOL", 0))) {

            Toast.makeText(MainActivity.this, getString(R.string.Bankisfull), Toast.LENGTH_SHORT).show();
        }


        if (intBankmax > (prefs1.getInt("POOL", 0))) {
            //Timer Zurücksetzen
            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab2");


            MyPreferences doubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
            Boolean doblegold = doubler.getBoolean("doublecoll", false);


            if(!doblegold) {
                //rücklauf falls das max erfüllt wurde(nicht vorhanden

                int vorhandenesgold = (goldint + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab2.getFloat("goldphfab2", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (collect), Toast.LENGTH_SHORT).show();
                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor1.apply();


                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",goldint + (prefs1.getInt("POOL", 0)));
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

                }

            }
            if(doblegold) {
                //rücklauf

                int vorhandenesgold = (goldint*2 + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint*2 - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab2.getFloat("goldphfab2", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + collect, Toast.LENGTH_SHORT).show();

                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL", (goldint * 2) + (prefs1.getInt("POOL", 0)));
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + (goldint * 2), Toast.LENGTH_SHORT).show();
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) );
                    editor1.apply();
                }
            }

        }

    }


    //Fabrik3
    public void dialogFabr3() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik3);
        closeBauhaus = (Button) dialog.findViewById(R.id.closeBauhaus);
        closeBauhaus.setOnClickListener(this);

        datafab3 = MyPreferences.getInstance(MainActivity.this,"datafab3");


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

                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");
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
                String maxgo = strI + "/" + "" +
                        datafab3.getInt("maxfabrik3", 500);

                countfab3.setText(maxgo);

                double goldphfab160 = datafab3.getFloat("goldphfab3", (float) 0.05) * 60 * 60;

                int goldphfab1int = (int) goldphfab160;

                String goph = getString(R.string.gold_per_hour) + "" +
                        goldphfab1int;
                GperH3.setText(goph);


                h.postDelayed(this, delay);
            }
        }, delay);


        dialog.show();


    }

    public void sammelnfab3() {


        MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.


        int endTime = ((int) System.currentTimeMillis() / 1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab3.getFloat("goldphfab3", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int) (gold);

        //checken ob das max schon erreicht wurde

        if (goldint >= datafab3.getInt("maxfabrik3", 500)) {

            goldint = datafab3.getInt("maxfabrik3", 500);
        }

        //schauen, ist der pool schon auf maxStorage der bank?
        MyPreferences BankMax = MyPreferences.getInstance(MainActivity.this,"dataBank");
        int intBankmax = BankMax.getInt("maxGoldStorage", 1000);
        MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"POOL");
        if (intBankmax <= (prefs1.getInt("POOL", 0))) {

            Toast.makeText(MainActivity.this, getString(R.string.Bankisfull), Toast.LENGTH_SHORT).show();
        }


        if (intBankmax > (prefs1.getInt("POOL", 0))) {
            //Timer Zurücksetzen
            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab3");

            MyPreferences doubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
            Boolean doblegold = doubler.getBoolean("doublecoll", false);



            if(!doblegold) {
                //rücklauf falls das max erfüllt wurde(nicht vorhanden

                int vorhandenesgold = (goldint + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab3.getFloat("goldphfab3", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (collect), Toast.LENGTH_SHORT).show();
                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor1.apply();


                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",goldint + (prefs1.getInt("POOL", 0)));
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

                }

            }
            if(doblegold) {
                //rücklauf

                int vorhandenesgold = (goldint*2 + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint*2 - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab3.getFloat("goldphfab3", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + collect, Toast.LENGTH_SHORT).show();

                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL", (goldint * 2) + (prefs1.getInt("POOL", 0)));
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + (goldint * 2), Toast.LENGTH_SHORT).show();
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) );
                    editor1.apply();
                }
            }
        }

    }

    //Fabrik4
    public void dialogFabr4() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik4);
        closeBauhaus = (Button) dialog.findViewById(R.id.closeBauhaus);
        closeBauhaus.setOnClickListener(this);

        datafab4 = MyPreferences.getInstance(MainActivity.this,"datafab4");


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

                MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");
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
                String maxgo = strI + "/" + "" +
                        datafab4.getInt("maxfabrik4", 500);

                countfab4.setText(maxgo);

                double goldphfab160 = datafab4.getFloat("goldphfab4", (float) 0.05) * 60 * 60;

                int goldphfab1int = (int) goldphfab160;

                String goph = getString(R.string.gold_per_hour) + "" +
                        goldphfab1int;
                GperH4.setText(goph);


                h.postDelayed(this, delay);
            }
        }, delay);


        dialog.show();


    }

    public void sammelnfab4() {


        MyPreferences prefs = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");

        int startTime = prefs.getInt("startTime", 0); //0 is the default value.


        int endTime = ((int) System.currentTimeMillis() / 1000);

        int secondsElapsed = endTime - startTime;


        //Sekunden in gold mit kommas umwandeln
        double goldtemp = (secondsElapsed * datafab4.getFloat("goldphfab4", (float) 0.05));
        long gold = (long) goldtemp;

        //gold von long zu int
        int goldint = (int) (gold);

        //checken ob das max schon erreicht wurde

        if (goldint >= datafab4.getInt("maxfabrik4", 500)) {

            goldint = datafab4.getInt("maxfabrik4", 500);
        }

        //schauen, ist der pool schon auf maxStorage der bank?
        MyPreferences BankMax = MyPreferences.getInstance(MainActivity.this,"dataBank");
        int intBankmax = BankMax.getInt("maxGoldStorage", 1000);
        MyPreferences prefs1 = MyPreferences.getInstance(MainActivity.this,"POOL");
        if (intBankmax <= (prefs1.getInt("POOL", 0))) {

            Toast.makeText(MainActivity.this, getString(R.string.Bankisfull), Toast.LENGTH_SHORT).show();
        }


        if (intBankmax > (prefs1.getInt("POOL", 0))) {
            //Timer Zurücksetzen
            MyPreferences editor1 = MyPreferences.getInstance(MainActivity.this,"speichervonstartzeitfab4");

            MyPreferences doubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
            Boolean doblegold = doubler.getBoolean("doublecoll", false);


            if(!doblegold) {
                //rücklauf falls das max erfüllt wurde(nicht vorhanden

                int vorhandenesgold = (goldint + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab4.getFloat("goldphfab4", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (collect), Toast.LENGTH_SHORT).show();
                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000));
                    editor1.apply();


                    //Das Gold zum pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");
                    editor2.putInt("POOL",goldint + (prefs1.getInt("POOL", 0)));
                    editor2.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.gold_collected) + (goldint), Toast.LENGTH_SHORT).show();

                }

            }
            if(doblegold) {
                //rücklauf

                int vorhandenesgold = (goldint*2 + prefs1.getInt("POOL", 0));
                if (vorhandenesgold > intBankmax) {
                    int uberlauf = vorhandenesgold - intBankmax;
                    int collect = goldint*2 - uberlauf;


                    float zeitabzugtemp = (uberlauf / datafab4.getFloat("goldphfab4", (float) 0.05));

                    int zeitabzug = (int) zeitabzugtemp;

                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) - zeitabzug);
                    editor1.apply();

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL",intBankmax);
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + collect, Toast.LENGTH_SHORT).show();

                }
                //kein überlauf
                if (vorhandenesgold <= intBankmax) {

                    //gold dem pool hinzufügen
                    MyPreferences editor2 = MyPreferences.getInstance(MainActivity.this,"POOL");

                    editor2.putInt("POOL", (goldint * 2) + (prefs1.getInt("POOL", 0)));
                    editor2.apply();

                    MyPreferences dedubler = MyPreferences.getInstance(MainActivity.this,"doublecoll");
                    dedubler.putBoolean("doublecoll", false);
                    dedubler.apply();
                    Toast.makeText(MainActivity.this, getString(R.string.doublegoldcollect) + (goldint * 2), Toast.LENGTH_SHORT).show();
                    //Timer Zurücksetzen
                    editor1.putInt("startTime", (((int) System.currentTimeMillis()) / 1000) );
                    editor1.apply();
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.frozensparks.royalindustry/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }


}
