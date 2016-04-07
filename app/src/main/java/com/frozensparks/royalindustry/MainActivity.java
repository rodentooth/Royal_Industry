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


    Button Fabrik1;
    Button SammelnFabrik1;
    Button Bank;


    int POOL;
    TextView POOLtext;
    TextView countfab1;


    Handler h = new Handler();
    int delay = 100; //milliseconds

    ProgressBar progressBarFabrik1;

    final Context context = this;

    int Fabrik1Level;
    int maxfabrik1;
    int minfabrik1;
    double goldphfab1;




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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



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

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.


        Fabrik1 = (Button) findViewById(R.id.Fabrik1);
        Fabrik1.setOnClickListener(this);

        SammelnFabrik1 = (Button) findViewById(R.id.SammelnFabrik1);
        SammelnFabrik1.setOnClickListener(this);

        Bank = (Button) findViewById(R.id.Bank);
        Bank.setOnClickListener(this);








        //counter für fabriken. Hat die fabrik schonmal produziert?
        SharedPreferences prefs = getSharedPreferences("resultatdersession", MODE_PRIVATE);

        int secondsElapsed = prefs.getInt("secondsElapsed", 0); //0 is the default value.

        if (secondsElapsed <= 2) {
            //erste startzeit speichern
            SharedPreferences.Editor editor = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE).edit();
            editor.putInt("startTime", ((int) System.currentTimeMillis()) / 1000);
            editor.commit();

            //Fabrik auf lvl 1 setzen
            SharedPreferences.Editor editor1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE).edit();
            editor1.putInt("Fabrik1Level", 1);
            editor1.commit();

        }



        h.postDelayed(new Runnable(){
            public void run(){
                //jede sekunde gold checken
                //Jede sekunde sammler button aktualisieren
                //wenn Long von gold über 1 dann visible, sammelbar machen.

                SharedPreferences prefs = getSharedPreferences("speichervonstartzeit1", MODE_PRIVATE);
                int startTime = prefs.getInt("startTime", 0); //0 is the default value.

                int endTime = ((int) System.currentTimeMillis()/1000);
                //aktualisierungszeit ausrechnen
                int secondsElapsed = endTime - startTime;
                //einheiten umwandeln
                double goldtemp = (secondsElapsed * goldphfab1);
                long gold = (long) goldtemp;
                //checken obs soweit ist
                if (gold<= minfabrik1) {
                    SammelnFabrik1.setVisibility(View.GONE);
                }
                if(gold>= minfabrik1) {
                    SammelnFabrik1.setVisibility(View.VISIBLE);
                }

                //check, welches level die Fabrik 1 hat
                SharedPreferences prefs1 = getSharedPreferences("Fabrik1Level", MODE_PRIVATE);

                if (prefs1.getInt("Fabrik1Level", 1) <= 1){

                    maxfabrik1 = 500;
                    minfabrik1 = 1;
                    goldphfab1 = 0.05;
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


    }
    public void dialogFabr1(){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fabrik1);



        countfab1 = (TextView) dialog.findViewById(R.id.countfab1);
        final TextView GperH = (TextView) dialog.findViewById(R.id.GperH);
        progressBarFabrik1 = (ProgressBar) dialog.findViewById(R.id.progressBarFabrik1);
        progressBarFabrik1.setMax(maxfabrik1);


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
                double goldtemp = (secondsElapsed * goldphfab1);
                long gold = (long) goldtemp;
                int goldprogress1 = (int) gold;

                if (goldprogress1 >= maxfabrik1) {
                    goldprogress1 = maxfabrik1;
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
                maxg.append(maxfabrik1);
                String maxgo = maxg.toString();

                countfab1.setText(strI + "/" + maxgo);

              double goldphfab160= goldphfab1*60*60;

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
       double goldtemp = (secondsElapsed * goldphfab1);
       long gold = (long) goldtemp;








       //gold von long zu int
      int goldint = (int)(gold);

       //checken ob das max schon erreicht wurde

       if(goldint>= maxfabrik1) {

           goldint = maxfabrik1;
       }


        //Das Gold zum pool hinzufügen
        SharedPreferences.Editor editor2 = getSharedPreferences("POOL", MODE_PRIVATE).edit();
        SharedPreferences prefs1 = getSharedPreferences("POOL", MODE_PRIVATE);
        editor2.putInt("POOL", goldint + (prefs1.getInt("POOL", 0)));
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
