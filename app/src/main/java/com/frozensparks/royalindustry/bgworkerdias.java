
package com.frozensparks.royalindustry;


import android.content.Context;
import android.content.SharedPreferences;
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
        String goldnlvls_url = "http://frozensparks.com/goldnlvls.php";



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
        if (type.equals("goldnlevels")) {
            try {
                doafter = "goldnlvlupdate";
                String googleID = params[1];
                String levelfab1 = params[2];



                    URL url = new URL(goldnlvls_url);
                    HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                    httpurlconn.setRequestMethod("POST");
                    httpurlconn.setDoOutput(true);
                    httpurlconn.setDoInput(true);
                    OutputStream outputStream = httpurlconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = "0";
                if (isInteger(levelfab1)) {
                    String levelfab2 = params[3];
                    String levelfab3 = params[4];
                    String levelfab4 = params[5];
                    String levelbank = params[6];
                    String levelagency = params[7];
                    String pool = params[8];
                    String lastseen = params[9];
                    String root = params[10];



                    post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                            + URLEncoder.encode("levelfab1", "UTF-8") + "=" + URLEncoder.encode(levelfab1, "UTF-8") + "&"
                            + URLEncoder.encode("levelfab2", "UTF-8") + "=" + URLEncoder.encode(levelfab2, "UTF-8") + "&"
                            + URLEncoder.encode("levelfab3", "UTF-8") + "=" + URLEncoder.encode(levelfab3, "UTF-8") + "&"
                            + URLEncoder.encode("levelfab4", "UTF-8") + "=" + URLEncoder.encode(levelfab4, "UTF-8") + "&"
                            + URLEncoder.encode("levelbank", "UTF-8") + "=" + URLEncoder.encode(levelbank, "UTF-8") + "&"
                            + URLEncoder.encode("levelagency", "UTF-8") + "=" + URLEncoder.encode(levelagency, "UTF-8") + "&"
                            + URLEncoder.encode("pool", "UTF-8") + "=" + URLEncoder.encode(pool, "UTF-8") + "&"
                            + URLEncoder.encode("lastseen", "UTF-8") + "=" + URLEncoder.encode(lastseen, "UTF-8") + "&"
                            + URLEncoder.encode("root", "UTF-8") + "=" + URLEncoder.encode(root, "UTF-8");
                    }

                if (!isInteger(levelfab1)) {

                        post_data = URLEncoder.encode("googleID", "UTF-8") + "=" + URLEncoder.encode(googleID, "UTF-8") + "&"
                            + URLEncoder.encode("hacker", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                }
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

                }catch(IOException e){
                    e.printStackTrace();

            }

        } if (type.equals("convert")) {
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
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
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


        if (doafter.equals("diacollect")) {
            if (connectcode >= 0) {
                //

                //databank aktualisieren
                SharedPreferences editor1 = new ObscuredSharedPreferences(context,context.getSharedPreferences("DIAMONDS",Context.MODE_PRIVATE));
                editor1.edit().putInt("DIAMONDS", connectcode).apply();


            }


        }
        if (doafter.equals("diasweg")) {

            if (connectcode == -1) {
                Toast.makeText(context, "not enough diamonds", Toast.LENGTH_SHORT).show();

            }
                if (connectcode >= 0) {

                    //databank aktualisieren
                    SharedPreferences editor1 = new ObscuredSharedPreferences(context, context.getSharedPreferences("DIAMONDS", Context.MODE_PRIVATE));
                    editor1.edit().putInt("DIAMONDS", connectcode).apply();

                    SharedPreferences editor2 = new ObscuredSharedPreferences(context, context.getSharedPreferences("doublecoll", Context.MODE_PRIVATE));
                    editor2.edit().putBoolean("doublecoll", true).apply();
                    Toast.makeText(context, "buy successful", Toast.LENGTH_SHORT).show();

                }

        }
        if (doafter.equals("diastogold")) {

                    //databank aktualisieren
                    SharedPreferences editor1 = new ObscuredSharedPreferences(context,context.getSharedPreferences("DIAMONDS",Context.MODE_PRIVATE));
                    editor1.edit().putInt("DIAMONDS", connectcode).apply();

                    Toast.makeText(context, "convert successful", Toast.LENGTH_SHORT).show();

                   SharedPreferences prefs = new ObscuredSharedPreferences(context,context.getSharedPreferences("POOL",Context.MODE_PRIVATE));
                   int goldpool = prefs.getInt("POOL", 0);
                    SharedPreferences prefs1 = new ObscuredSharedPreferences(context,context.getSharedPreferences("thatmanydiastogold",Context.MODE_PRIVATE));

                    int diaweg = (prefs1.getInt("thatmanydiastogold", 0) );
                    SharedPreferences editor2 = new ObscuredSharedPreferences(context,context.getSharedPreferences("POOL",Context.MODE_PRIVATE));
                    editor2.edit().putInt("POOL", (goldpool + diaweg*250)).apply();





            }

            if (doafter.equals("convert")) {


                if (connectcode >= 1) {

                    // cashout aktualisieren
                    float rappen = (float) (connectcode);
                    float rtrdrp = rappen / 100;


                    SharedPreferences editor2 = new ObscuredSharedPreferences(context,context.getSharedPreferences("cashout",Context.MODE_PRIVATE));
                    editor2.edit().putFloat("cashout", rtrdrp).apply();
                    SharedPreferences cashouttext = new ObscuredSharedPreferences(context,context.getSharedPreferences("cashout",Context.MODE_PRIVATE));
                    BankActivity.cashoutonwait.setText(String.valueOf(cashouttext.getFloat("cashout", 0) + "$"));

                }



            }
        if (doafter.equals("afterwinner")) {

            //databank aktualisieren
            SharedPreferences editor1 = new ObscuredSharedPreferences(context,context.getSharedPreferences("DIAMONDS",Context.MODE_PRIVATE));
            editor1.edit().putInt("DIAMONDS", connectcode).apply();



        }


    }
}