package com.frozensparks.royalindustry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    TextView facpro;
    TextView gold;
    TextView insidebank;

    int lol = 0;

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

        facpro = (TextView) findViewById(R.id.facprod);
        facpro.setTypeface(typeface);

        gold = (TextView) findViewById(R.id.gold);
        gold.setTypeface(typeface);

        insidebank = (TextView) findViewById(R.id.insidebank);
        insidebank.setTypeface(typeface);


    }

    @Override
    public void onClick(View v) {


        int id = v.getId();
        if (id == R.id.cont) {
             lol = lol+1;

            if (lol == 1){
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
}
