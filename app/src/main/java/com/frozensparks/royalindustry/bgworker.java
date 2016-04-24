package com.frozensparks.royalindustry;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

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

/**
 * Created by Emanuel Graf on 23.04.2016.
 */
public class bgworker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;



    bgworker(Context ctx){

        context = ctx;


    }


    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String login_url = "http://frozensparks.com/login.php";
        if(type.equals("logreg")){
            try {
                String googleID = params[1];
                String email = params[2];
                String name = params[3];
                String photourl = params[4];


                URL url = new URL(login_url);
                HttpURLConnection httpurlconn = (HttpURLConnection) url.openConnection();
                httpurlconn.setRequestMethod("POST");
                httpurlconn.setDoOutput(true);
                httpurlconn.setDoInput(true);
                OutputStream outputStream = httpurlconn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("googleID", "UTF-8")+"="+URLEncoder.encode(googleID, "UTF-8")+"&"
                        +URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                        +URLEncoder.encode("photourl", "UTF-8")+"="+URLEncoder.encode(photourl, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream =httpurlconn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";

                while((line=bufferedReader.readLine())!=null){
                    result +=line;


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

        int connectcode = Integer.parseInt(result);


        if (connectcode == 0){

            Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();
        }
        if (connectcode == 11){

            Toast.makeText(context, "login success", Toast.LENGTH_SHORT).show();
        }

        if (connectcode == 121){

            Toast.makeText(context, "new account created", Toast.LENGTH_SHORT).show();
        }

        // else{
        //    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

        // }

    }
    @Override
    protected void onProgressUpdate(Void ... values) {
        super.onProgressUpdate(values);

    }


}
