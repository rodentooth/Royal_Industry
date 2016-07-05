package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Tutorial extends AppCompatActivity  implements View.OnClickListener {
    Context context = this;
    Typeface typeface;

    Button cont;
    Button tutfab;
    Button samtutfab;
    Button tutbank;
    Button tutarch;

    TextView welcome;
    TextView to_tutorial;
    TextView ri_tutorial;
    TextView facpro;
    TextView gold;
    TextView insidebank;
    MediaPlayer mp;
    int lol = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
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
        AssetManager am = context.getApplicationContext().getAssets();

        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%2s", "OldGlyphs.ttf"));

        cont = (Button) findViewById(R.id.cont);
        cont.setOnClickListener(this);

        tutfab = (Button) findViewById(R.id.tutfab);

        samtutfab = (Button) findViewById(R.id.samtutfab);
        samtutfab.setOnClickListener(this);

        tutbank = (Button) findViewById(R.id.tutbank);

        tutarch = (Button) findViewById(R.id.tutarch);

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTypeface(typeface);


        to_tutorial = (TextView) findViewById(R.id.to_tutorial);
        to_tutorial.setTypeface(typeface);

        ri_tutorial = (TextView) findViewById(R.id.ri_tutorial);
        ri_tutorial.setTypeface(typeface);



        facpro = (TextView) findViewById(R.id.facprod);
        facpro.setTypeface(typeface);

        gold = (TextView) findViewById(R.id.gold);
        gold.setTypeface(typeface);

        insidebank = (TextView) findViewById(R.id.insidebank);
        insidebank.setTypeface(typeface);


        mp = MediaPlayer.create(this, R.raw.aw_spectre);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.reset();
                mp.release();
                mp=null;
            }

        });
        mp.start();


        cont.setVisibility(View.GONE);
        ri_tutorial.setVisibility(View.GONE);
        to_tutorial.setVisibility(View.GONE);
        welcome.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                destarter();
            }
        }, 100);


    }

    @Override
    public void onClick(View v) {


        int id = v.getId();
        if (id == R.id.cont) {
             lol = lol+1;

            if (lol == 1){
                welcome.setVisibility(View.INVISIBLE);
                ri_tutorial.clearAnimation();
                ri_tutorial.setVisibility(View.INVISIBLE);
                to_tutorial.setVisibility(View.INVISIBLE);

                tutfab.setVisibility(View.VISIBLE);
                facpro.setVisibility(View.VISIBLE);
            }
            if (lol == 2){
                samtutfab.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                cont.setVisibility(View.INVISIBLE);
            }
            if (lol == 3){
                facpro.setVisibility(View.INVISIBLE);
                insidebank.setVisibility(View.VISIBLE);
                gold.setVisibility(View.INVISIBLE);

            }
            if (lol == 4){
                insidebank.setText(R.string.insidearch);
                tutbank.setVisibility(View.INVISIBLE);
                tutarch.setVisibility(View.VISIBLE);
            }

            if (lol == 5) {
                tutarch.setVisibility(View.INVISIBLE);

                insidebank.setText(R.string.tutcomplete);


            }



                if (lol == 6){


                SharedPreferences.Editor firstopen1 = getSharedPreferences("firstope", MODE_PRIVATE).edit();
                firstopen1.putBoolean("firstope", false);
                firstopen1.putBoolean("welcomepr", true);

                    firstopen1.apply();

                Intent intent = new Intent(Tutorial.this, MainActivity.class);
                startActivity(intent);
                Tutorial.this.finish();
            }



        }
        if (id == R.id.samtutfab) {
            Toast.makeText(Tutorial.this, R.string.goldcollectedtutorial, Toast.LENGTH_SHORT).show();
            samtutfab.setVisibility(View.INVISIBLE);
            gold.setText(R.string.banktutorial);
            tutbank.setVisibility(View.VISIBLE);
            cont.setVisibility(View.VISIBLE);
            welcome.setVisibility(View.INVISIBLE);
            tutfab.setVisibility(View.INVISIBLE);
            facpro.setVisibility(View.INVISIBLE);



        }

    }
    @Override
    public void onStart() {


        super.onStart();

    }
public void destarter() {

    cont.setVisibility(View.INVISIBLE);
    ri_tutorial.setVisibility(View.INVISIBLE);
    to_tutorial.setVisibility(View.INVISIBLE);
    welcome.setVisibility(View.INVISIBLE);

    if (lol == -1) {
        lol = 0;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                fadeOutAnimation.setDuration(1000); // time for animation in milliseconds
                fadeOutAnimation.setFillAfter(true); // make the transformation persist
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        welcome.setVisibility(View.VISIBLE);

                    }
                });

                welcome.setAnimation(fadeOutAnimation);


            }
        }, 100);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                fadeOutAnimation.setDuration(2000); // time for animation in milliseconds
                fadeOutAnimation.setFillAfter(true); // make the transformation persist
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        cont.setVisibility(View.VISIBLE);
                        welcome.clearAnimation();
                        to_tutorial.clearAnimation();
                        welcome.setVisibility(View.GONE);
                        to_tutorial.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        ri_tutorial.setVisibility(View.VISIBLE);
                    }
                });

                ri_tutorial.setAnimation(fadeOutAnimation);


            }
        }, 2100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                fadeOutAnimation.setDuration(1000); // time for animation in milliseconds
                fadeOutAnimation.setFillAfter(true); // make the transformation persist
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        to_tutorial.setVisibility(View.VISIBLE);
                    }
                });

                to_tutorial.setAnimation(fadeOutAnimation);


            }
        }, 1100);
    }
}

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        mp.reset();
        mp.release();
    }
}
