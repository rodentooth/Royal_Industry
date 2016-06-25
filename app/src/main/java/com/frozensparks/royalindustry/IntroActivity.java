package com.frozensparks.royalindustry;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
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


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
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
    String personId;
    int connectcode;
    String idToken;
    Context context = this;

    ConnectivityManager connectivity;

    Typeface typeface;

    String str_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //inet check
        connectivity  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);




        AssetManager am = context.getApplicationContext().getAssets();

        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%2s", "OldGlyphs.ttf"));

        splash = (TextView) findViewById(R.id.splash);
        splash.setTypeface(typeface);
        splash.setText("frozen \n sparks");
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

        splash = (TextView) findViewById(R.id.splash);
        splash.setVisibility(View.INVISIBLE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
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

        if  (isConnectingToInternet()){
            signIn();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                    fadeOutAnimation.setDuration(2000); // time for animation in milliseconds
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
                            splash.setVisibility(View.VISIBLE);
                        }
                    });

                    splash.setAnimation(fadeOutAnimation);


                }
            }, 100);
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }
    // check internet connection
    public boolean isConnectingToInternet() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            return true;

        } else {
            // not connected to the internet


            AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
            fadeOutAnimation.setDuration(5000); // time for animation in milliseconds
            fadeOutAnimation.setFillAfter(true); // make the transformation persist
            fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationStart(Animation animation) {
                    splash.setVisibility(View.VISIBLE);
                }
            });

            splash.setAnimation(fadeOutAnimation);


            Toast.makeText(IntroActivity.this, R.string.checkinet, Toast.LENGTH_LONG).show();
            return false;

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result != null) {
                handleSignInResult(result);
            }
            if (result == null) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }

        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            personId = acct.getId();
            idToken = acct.getIdToken();
            String type = "logreg";

            bgworkerintro bgworkerintro = new bgworkerintro(IntroActivity.this);
            try {
                str_result = bgworkerintro.execute(type, idToken, personId).get();


                String dat = "request";
                String gid = personId;
                bgworkerdias lol = new bgworkerdias(context);
                lol.execute(dat, gid, "diacollect");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (str_result != null)
                if (!str_result.toLowerCase().contains("-")) {

                    connectcode = Integer.parseInt(str_result);
                }

            if (connectcode == 0) {

                Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show();
            }
            if (connectcode == 10) {

                Toast.makeText(IntroActivity.this, "make sure you log in with a valid google acocunt", Toast.LENGTH_SHORT).show();

                signOut();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
            if (connectcode == 11) {

                //Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
                SharedPreferences editor1 = new ObscuredSharedPreferences(IntroActivity.this, IntroActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                editor1.edit().putString("id", personId).apply();

            }

            if (connectcode == 121) {

                Toast.makeText(this, "new account created", Toast.LENGTH_SHORT).show();
                SharedPreferences editor1 = new ObscuredSharedPreferences(IntroActivity.this, IntroActivity.this.getSharedPreferences("google", MODE_PRIVATE));
                editor1.edit().putString("id", personId).apply();

                SharedPreferences editorfab3 = new ObscuredSharedPreferences(context, context.getSharedPreferences("referrer", context.MODE_PRIVATE));
                String referrer = editorfab3.getString("gid", "0");
                int done = editorfab3.getInt("done", 0);

                if (!referrer.equals("0")) {
                    if (done == 0) {
                        editorfab3.edit().putInt("done", 1).apply();
                        bgworkerdias2 lol = new bgworkerdias2(context);
                        lol.execute("referrer", personId, referrer, "diacollect");
                    }
                }
            }
            if (str_result!=null) {

                if (str_result.toLowerCase().contains("error")) {
                    Toast.makeText(this, str_result, Toast.LENGTH_SHORT).show();
                }
            }

            new Handler().postDelayed(new Runnable() {
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
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                    });

                    splash.setAnimation(fadeOutAnimation);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                /* Create an Intent that will start the Menu-Activity. */

                            SharedPreferences firstopen = new ObscuredSharedPreferences(IntroActivity.this,IntroActivity.this.getSharedPreferences("firstope", MODE_PRIVATE));
                            Boolean datopen = firstopen.getBoolean("firstope", true);
                            if (!datopen){

                                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                                startActivity(intent);
                                IntroActivity.this.finish();
                            }



                            if (datopen) {
                                Intent intent = new Intent(IntroActivity.this, Tutorial.class);
                                startActivity(intent);

                                IntroActivity.this.finish();

                            }

                        }
                    }, 200);
                }
            }, 200);


        } else {
            // Signed out, show unauthenticated UI.
            signOut();
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        signIn();

                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onClick(View v) {

    }


    class bgworkerintro extends AsyncTask<String, Void, String> {
        String doafter;


        public bgworkerintro(IntroActivity activity) {

            Dialog dialog = new Dialog(activity);
        }


        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            String login_url = "http://frozensparks.com/login.php";
            String veri_url = "http://frozensparks.com/verify.php";
            String collect_url = "http://frozensparks.com/collect.php";


            if (type.equals("logreg")) {
                try {
                    String googleID = params[2];
                    String idtoken = params[1];



                    URL url = new URL(login_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                            + URLEncoder.encode("idtoken", "UTF-8") + "=" + URLEncoder.encode(idtoken, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            if (type.equals("request")) {
                try {
                    doafter = params[2];
                    String googleID = params[1];


                    URL url = new URL(collect_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            if (type.equals("verify")) {
                try {
                    String googleID = params[1];
                    String deviceID = params[2];
                    String number = params[3];


                    URL url = new URL(veri_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                            + URLEncoder.encode("deviceID", "UTF-8") + "=" + URLEncoder.encode(deviceID, "UTF-8") + "&"
                            + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(number, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpurlconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;


                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpurlconn.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


            return null;
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String result) {


            if (str_result != null) {
                str_result = str_result.replace(" ", "");

                try {
                    connectcode = Integer.parseInt(str_result);
                } catch (NumberFormatException e) {

                    AlphaAnimation fadeOutAnimation = new AlphaAnimation(0, 1); // start alpha, end alpha
                    fadeOutAnimation.setDuration(5000); // time for animation in milliseconds
                    fadeOutAnimation.setFillAfter(true); // make the transformation persist
                    fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationStart(Animation animation) {
                            splash.setVisibility(View.VISIBLE);
                        }
                    });

                    splash.setAnimation(fadeOutAnimation);


                    Toast.makeText(IntroActivity.this, R.string.checkinet, Toast.LENGTH_LONG).show();


                }                }






            if (doafter == "diacollect") {
                if (connectcode >= 1) {
                    //
                    //    Toast.makeText(IntroActivity.this, str_result, Toast.LENGTH_SHORT).show();




                }


            }

            // else{
            //    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

            // }

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }


    }
}