package com.frozensparks.royalindustry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.example.games.basegameutils.BaseGameUtils;

public class IntroActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private TextView mStatusTextView;
    TextView splash;
    String personName;
    String personEmail;
    String personId;
    Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
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

    splash = (TextView)findViewById(R.id.splash);
        splash.setVisibility(View.INVISIBLE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]


    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
        @Override
        public void onStart() {
            super.onStart();


            signIn();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {

                    AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                    fadeOutAnimation.setDuration(700); // time for animation in milliseconds
                    fadeOutAnimation.setFillAfter(true); // make the transformation persist
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) { }

                        @Override
                        public void onAnimationStart(Animation animation) {splash.setVisibility(View.VISIBLE); }
                    });

                    splash.setAnimation(fadeOutAnimation);


                }
            }, 100);
        }




    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            personName = acct.getDisplayName();
             personEmail = acct.getEmail();
             personId = acct.getId();
             personPhoto = acct.getPhotoUrl();
            String pph = "0";
            String type = "logreg";
            if (personPhoto != null){
                 pph = personPhoto.toString();
            }
            if (personName == null){
                personName = "0";
            }
            if (personEmail == null){
                personEmail = "0";
            }

            bgworker bgworker = new bgworker(this);
            bgworker.execute(type, personId, personEmail, personName, pph);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    AlphaAnimation fadeOutAnimation = new AlphaAnimation(1, 0); // start alpha, end alpha
                    fadeOutAnimation.setDuration(500); // time for animation in milliseconds
                    fadeOutAnimation.setFillAfter(true); // make the transformation persist
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            splash.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) { }

                        @Override
                        public void onAnimationStart(Animation animation) { }
                    });

                    splash.setAnimation(fadeOutAnimation);

                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            IntroActivity.this.finish();
                }
            }, 200);
                }
                }, 200);


        } else {
            // Signed out, show unauthenticated UI.
            signIn();
            Toast.makeText(IntroActivity.this, "log in!!", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
        public void onConnectionSuspended ( int i){
        }

        @Override
        public void onClick (View v){

        }
    }
