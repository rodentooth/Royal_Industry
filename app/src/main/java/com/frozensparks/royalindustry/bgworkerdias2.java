
package com.frozensparks.royalindustry;


import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.os.AsyncTask;

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


public class bgworkerdias2 extends AsyncTask<String, Void, String> {
    String doafter;
    String str_result;
    int connectcode;
    Context context;


    public bgworkerdias2(Context activity) {


        context = activity;

    }


    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String collect_url = "http://frozensparks.com/collect.php";
        String convert_url = "http://frozensparks.com/cashout.php";
        String referrer_url = "http://frozensparks.com/referrer.php";



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

        if (type.equals("referrer")) {
            try {
                doafter = params[3];
                String googleID = params[1];
                String referral = params[2];


                URL url = new URL(referrer_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                        + URLEncoder.encode("referral", "UTF-8") + "=" + URLEncoder.encode(referral, "UTF-8");

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

                str_result = result.replace(" ", "");
                if (str_result != "") {
                connectcode = Integer.parseInt(str_result);
            }
        }


        if (doafter.equals("diacollect")) {
            if (connectcode >= 0) {
                //

                //databank aktualisieren
                SharedPreferences editor1 = new ObscuredSharedPreferences(context,context.getSharedPreferences("DIAMONDS",Context.MODE_PRIVATE));
                editor1.edit().putInt("DIAMONDS", connectcode).apply();


            }


        }
        if (doafter.equals("convert")) {


            if (connectcode >= 1) {

                // cashout aktualisieren
                float rappen = (float)(connectcode);
                float rtrdrp = rappen/100;
                String rpstr = Float.toString(rtrdrp);


                SharedPreferences editor2 = new ObscuredSharedPreferences(context,context.getSharedPreferences("cashout",Context.MODE_PRIVATE));
                editor2.edit().putFloat("cashout", rtrdrp).apply();
                SharedPreferences cashouttext = new ObscuredSharedPreferences(context,context.getSharedPreferences("cashout",Context.MODE_PRIVATE));
                BankActivity.cashoutonwait.setText("waiting for payout:" + String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

            }

            // else{
            //    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

            // }

        }


    }
}