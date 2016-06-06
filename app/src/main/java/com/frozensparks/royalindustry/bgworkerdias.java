
package com.frozensparks.royalindustry;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class bgworkerdias extends AsyncTask<String, Void, String> {
    String doafter;
    String str_result;
    int connectcode;
    Context context;


    public bgworkerdias(Context activity) {


        context = activity;

    }


    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String collect_url = "http://frozensparks.com/collect.php";
        String convert_url = "http://frozensparks.com/cashout.php";
        String diasweg_url = "http://frozensparks.com/diasweg.php";
        String inputOppPlayerId_url = "http://frozensparks.com/inputOppPlayerId.php";
        String looserorwinner_url = "http://frozensparks.com/looserorwinner.php";



        if (type.equals("diasweg")) {
            try {
                doafter = params[3];
                String googleID = params[1];
                String diamonds = params[2];


                URL url = new URL(diasweg_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                        + URLEncoder.encode("diamonds", "UTF-8") + "=" + URLEncoder.encode(diamonds, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpurlconn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (type.equals("convert")) {
            try {
                doafter = params[3];
                String googleID = params[1];
                String diamonds = params[2];


                URL url = new URL(convert_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                        + URLEncoder.encode("diamonds", "UTF-8") + "=" + URLEncoder.encode(diamonds, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpurlconn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (type.equals("looserorwinner")) {
            try {
                String googleID = params[1];
                String myID = params[2];
                String oppid = params[3];
                String winloose = params[4];
                doafter = params[5];


                URL url = new URL(looserorwinner_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                        + URLEncoder.encode("myID", "UTF-8") + "=" + URLEncoder.encode(myID, "UTF-8") + "&"
                        + URLEncoder.encode("oppid", "UTF-8") + "=" + URLEncoder.encode(oppid, "UTF-8") + "&"
                        + URLEncoder.encode("winloose", "UTF-8") + "=" + URLEncoder.encode(winloose, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpurlconn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (type.equals("inputOpponentplayerID")) {
            try {
                doafter = params[3];
                String googleID = params[1];
                String oppPlayerId = params[2];


                URL url = new URL(inputOppPlayerId_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                        + URLEncoder.encode("oppPlayerId", "UTF-8") + "=" + URLEncoder.encode(oppPlayerId, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpurlconn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;

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
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;


                }
                bufferedReader.close();
                inputStream.close();
                httpurlconn.disconnect();

                return result;


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


        if (result != null) {
            if (result == "") {
                result = "0";
            }

            str_result = result.replace(" ", "");
            connectcode = Integer.parseInt(str_result);
        }


        if (doafter == "diacollect") {
            if (connectcode >= 1) {
                //

                //databank aktualisieren
                MyPreferences editor1 = MyPreferences.getInstance(context,"DIAMONDS");
                editor1.putInt("DIAMONDS", connectcode);
                editor1.apply();


            }


        }
        if (doafter == "diasweg") {

                //

                //databank aktualisieren
                MyPreferences editor1 = MyPreferences.getInstance(context,"DIAMONDS");
                editor1.putInt("DIAMONDS", connectcode);
                editor1.apply();

                MyPreferences editor2 = MyPreferences.getInstance(context,"doublecoll");
                editor2.putBoolean("doublecoll", true);
                editor2.apply();
                Toast.makeText(context, "buy successful", Toast.LENGTH_SHORT).show();



        }
        if (doafter == "diastogold") {

                    //databank aktualisieren
                    MyPreferences editor1 = MyPreferences.getInstance(context,"DIAMONDS");
                    editor1.putInt("DIAMONDS", connectcode);
                    editor1.apply();

                    Toast.makeText(context, "convert successful", Toast.LENGTH_SHORT).show();

                   MyPreferences prefs = MyPreferences.getInstance(context,"POOL");
                   int goldpool = prefs.getInt("POOL", 0);
                    MyPreferences prefs1 = MyPreferences.getInstance(context,"thatmanydiastogold");

                    int diaweg = (prefs1.getInt("thatmanydiastogold", 0) );
                    MyPreferences editor2 = MyPreferences.getInstance(context,"POOL");
                    editor2.putInt("POOL", (goldpool + diaweg*250));
                    editor2.apply();





            }

            if (doafter == "convert") {


                if (connectcode >= 1) {

                    // cashout aktualisieren
                    float rappen = (float) (connectcode);
                    float rtrdrp = rappen / 100;


                    MyPreferences editor2 = MyPreferences.getInstance(context,"cashout");
                    editor2.putFloat("cashout", rtrdrp);
                    editor2.apply();
                    MyPreferences cashouttext = MyPreferences.getInstance(context,"cashout");
                    BankActivity.cashoutonwait.setText(String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

                }



            }
        if (doafter == "afterwinner") {

            //databank aktualisieren
            MyPreferences editor1 = MyPreferences.getInstance(context,"DIAMONDS");
            editor1.putInt("DIAMONDS", connectcode);
            editor1.apply();



        }


    }
}