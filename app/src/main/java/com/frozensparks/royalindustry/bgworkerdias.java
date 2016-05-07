
package com.frozensparks.royalindustry;


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
import java.util.ArrayList;
import java.util.Random;

import android.app.Dialog;
import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class bgworkerdias extends AsyncTask<String, Void, String> {
    String doafter;
    String str_result;
    int connectcode;
    Context context;


    public bgworkerdias(Context activity) {




        Dialog dialog = new Dialog(activity);
        context = activity;

    }


    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String collect_url = "http://frozensparks.com/collect.php";
        String convert_url = "http://frozensparks.com/cashout.php";


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


        return null;
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {


        if (result != null) {
            if(result== "" ){
                result= "0";
            }

            str_result = result.replace(" ", "");
            connectcode = Integer.parseInt(str_result);
        }


        if (doafter == "diacollect") {
            if (connectcode >= 1) {
                //

                //databank aktualisieren
                SharedPreferences.Editor editor1 = context.getSharedPreferences("DIAMONDS", context.MODE_PRIVATE).edit();
                editor1.putInt("DIAMONDS", connectcode);
                editor1.commit();


            }


        }
        if (doafter == "convert") {


            if (connectcode >= 1) {

                // cashout aktualisieren
                float rappen = (float)(connectcode);
                float rtrdrp = rappen/100;
                String rpstr = Float.toString(rtrdrp);


                SharedPreferences.Editor editor2 = context.getSharedPreferences("cashout", context.MODE_PRIVATE).edit();
                editor2.putFloat("cashout", rtrdrp);
                editor2.commit();
                SharedPreferences cashouttext = context.getSharedPreferences("cashout", context.MODE_PRIVATE);
                BankActivity.cashoutonwait.setText(String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

            }

            // else{
            //    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

            // }

        }


    }
}