package com.frozensparks.royalindustry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


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
   /* int Fabrik1Level;
    int maxfabrik1;
    int minfabrik1;
    double goldphfab1;
    wird von sharedpreferences übernommen*/
    SharedPreferences datafab1;
    TextView countfab1;
    TextView leveltextfabr1;


    //Bank
    Button Bank;
    int POOL;
    TextView POOLtext;


    //Aktualisator
    Handler h = new Handler();
    int delay = 100; //milliseconds

    //Bauhaus
    Button bauhaus;
    Dialog bauhausdialog;
    Button upgradefab1;
    ProgressBar  progressBarUpgradefab1;


    //FULLSCREEN

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



        //Bauhaus
        bauhaus = (Button) findViewById(R.id.bauhaus);
        bauhaus.setOnClickListener(this);




        //Fabrik1
        Fabrik1 = (Button) findViewById(R.id.Fabrik1);
        Fabrik1.setOnClickListener(this);
        SammelnFabrik1 = (Button) findViewById(R.id.SammelnFabrik1);
        SammelnFabrik1.setOnClickListener(this);
        leveltextfabr1 = (TextView) findViewById(R.id.leveltextfabr1);
        progressBarUpgradefab1 = (ProgressBar) findViewById(R.id.progressBarUpgradefab1);
        progressBarUpgradefab1.setVisibility(View.INVISIBLE);

        //counter für fabriken. Hat die fabrik schonmal produziert?
         datafab1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);



        if ((datafab1.getInt("Level", 1)  == 1)) {
            //erste startzeit speichern
            SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
            editor.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
            editor.commit();

            //Fabrik auf lvl 1 setzen
            SharedPreferences.Editor editor1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE).edit();
            editor1.putInt("maxfabrik1", 500);
            editor1.putInt("minfabrik1", 1);
            editor1.putFloat("goldphfab1", (float) 0.05);
            editor1.putString("leveltextfab1", "Fabrik 1 Level: 1");
            editor1.commit();

        }




        //Aktualisator

        h.postDelayed(new Runnable(){
            public void run(){
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE);
                SharedPreferences prefs1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);
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


                    //Fabrik1
                datafab1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);
                    Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
                    if (cdfab1) {

                        progressBarUpgradefab1.setVisibility(View.VISIBLE);
                        SammelnFabrik1.setVisibility(View.INVISIBLE);
                    leveltextfabr1.setText(prefs1.getString("leveltextfab1", "1"));
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
                        stopgold.commit();

                    if (elapsedSecondsfab1 > Fab1upgradeContdown.getInt("countdown", 0)) {

                        SharedPreferences.Editor editor1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE).edit();

                        if (datafab1.getInt("Level", 1) == 1) {


                            //definition level2
                            editor1.putInt("maxfabrik1", 1000);
                            editor1.putInt("minfabrik1", 2);
                            editor1.putFloat("goldphfab1", (float) 0.1);
                            editor1.putString("leveltextfab1", "Fabrik 1 Level: 2");
                            editor1.putBoolean("isLeveling", false);
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);
                        }


                        if (datafab1.getInt("Level", 1) == 2) {

                            //LEVEL3
                            editor1.putInt("maxfabrik1", 1500);
                            editor1.putInt("minfabrik1", 3);
                            editor1.putFloat("goldphfab1", (float) 0.16667);
                            editor1.putString("leveltextfab1", "Fabrik 1 Level: 3");
                            editor1.putBoolean("isLeveling", false);
                            progressBarUpgradefab1.setVisibility(View.INVISIBLE);
                            SammelnFabrik1.setVisibility(View.VISIBLE);

                        }
                        if (datafab1.getInt("Level", 1) == 2) {

                            //LvL4
                            Toast.makeText(MainActivity.this, "you are already lvl 3 \n amana", Toast.LENGTH_SHORT).show();
                        }
                        editor1.putInt("Level", datafab1.getInt("Level", 1) + 1);
                        editor1.commit();

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
        datafab1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);

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

            upgradefab1 = (Button) bauhausdialog.findViewById(R.id.upgradefab1);
            upgradefab1.setOnClickListener(this);

            leveltextfabr1 = (TextView) bauhausdialog.findViewById(R.id.leveltextfabr1);
            leveltextfabr1.setText(datafab1.getString("leveltextfab1", "1"));

            bauhausdialog.show();

        }

        if (id == R.id.upgradefab1) {


            //ist ein upgrade am laufen?


            datafab1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);

            Boolean cdfab1 = (datafab1.getBoolean("isLeveling", false));
            if (cdfab1) {

                Toast.makeText(MainActivity.this, "Wait until upgrade is finished", Toast.LENGTH_SHORT).show();

            }

            if (!cdfab1) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Fabrik 1");

                // set dialog message

                if (datafab1.getInt("Level", 1) == 1) {
                    //text lvl2
                    alertDialogBuilder.setMessage("Upgradebenefits & costs level 2: \n GOLD PER HOUR: 360 \n STORAGE: 1000 \n \n COST: 50 GOLD");
                }
                if (datafab1.getInt("Level", 1) == 2) {
                    //text lvl2
                    alertDialogBuilder.setMessage("Upgradebenefits & costs level 3: \n GOLD PER HOUR: 600 \n STORAGE: 1500 \n \n COST: 500 GOLD");
                }

                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Upgrade!", new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {


                                //Upgrade
                                SharedPreferences prefs = getSharedPreferences("POOL", MODE_PRIVATE);

                                if (datafab1.getInt("Level", 1) == 1) {
                                    //LeveL2

                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

                                    if ((prefs.getInt("POOL", 0)) >= 50) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 50));
                                        editor2.commit();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO 3600 secs
                                        editor3.putInt("countdown", 36);
                                        editor3.commit();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.commit();

                                    }

                                    if ((prefs.getInt("POOL", 0)) <= 50) {
                                        Toast.makeText(MainActivity.this, "NOT ENOUGH GOLD", Toast.LENGTH_SHORT).show();

                                    }
                                }


                                if (datafab1.getInt("Level", 1) == 2) {

                                    //LEVEL3
                                    SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();

                                    if ((prefs.getInt("POOL", 0)) >= 500) {
                                        //bezahlen
                                        editor2.putInt("POOL", (prefs.getInt("POOL", 0) - 500));
                                        editor2.commit();

                                        //Countdown Starten
                                        SharedPreferences.Editor editor3 = getSharedPreferences("startTimeUpgradeFab1", MODE_PRIVATE).edit();
                                        editor3.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);

                                        //Countdownzeit definieren
                                        //TODO lvl 3 secs
                                        editor3.putInt("countdown", 36);
                                        editor3.commit();

                                        SharedPreferences.Editor editor1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE).edit();
                                        editor1.putBoolean("isLeveling", true);
                                        editor1.commit();

                                    }

                                    if ((prefs.getInt("POOL", 0)) <= 500) {
                                        Toast.makeText(MainActivity.this, "NOT ENOUGH GOLD", Toast.LENGTH_SHORT).show();

                                    }
                                }
                                    }
                                }


                        )
                        .

                                setNegativeButton("No", new DialogInterface.OnClickListener() {
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





        datafab1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);



        countfab1 = (TextView) dialog.findViewById(R.id.countfab1);
        final TextView GperH = (TextView) dialog.findViewById(R.id.GperH);
        progressBarFabrik1 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik1);
        progressBarFabrik1.setMax(datafab1.getInt("maxfabrik1", 500));


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
                GperH.setText(goph + "GOLD PER HOUR");


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



        //Timer Zurücksetzen
        SharedPreferences.Editor editor1 = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
        editor1.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
        editor1.commit();




       //Sekunden in gold mit kommas umwandeln
       double goldtemp = (secondsElapsed * datafab1.getFloat("goldphfab1", (float) 0.05));
       long gold = (long) goldtemp;








       //gold von long zu int
      int goldint = (int)(gold);

       //checken ob das max schon erreicht wurde

       if(goldint>= datafab1.getInt("maxfabrik1", 500)) {

           goldint = datafab1.getInt("maxfabrik1", 500);
       }


        //Das Gold zum pool hinzufügen
        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
        SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
        //TODO 10000 löschen
        editor2.putInt("POOL", 10000 + goldint + (prefs1.getInt("POOL", 0)));
        editor2.commit();



        Toast.makeText(MainActivity.this, "GOLD COLLECTED: " +(goldint), Toast.LENGTH_SHORT).show();



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
