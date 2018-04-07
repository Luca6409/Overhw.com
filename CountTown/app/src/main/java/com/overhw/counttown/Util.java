package com.overhw.counttown;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Luca Fabris on 26/02/2018.
 */

public class Util {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private Context context;

    public Util(Context context){
        this.context = context;
    }

    public void downloadTownsNames(){
        if(checkInternetConnection()){
            new DetailsEcho().execute("https://overhw.com/counttown/scripts/towns_details.php");
        }
        else{
            Toast.makeText(context, "Nessuna connessione attiva", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkInternetConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    /** CLASSE PER SCARICA IL FILE TOWNS_DETAILS.CSV */
    class DetailsEcho extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setTitle("Caricamento città...");
            pDialog.setMessage("\tAttendere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                // Enter URL address where your php file resides
                url = new URL(strings[0]);

                // Setup HttpURLConnection class to send and receive data from php
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {
            ArrayList<String> names = DatiComuni.nomi_citta;
            names.clear();

            if(!result.equalsIgnoreCase("unsuccessful")) {

                String[] comuni = result.split(";");
                int i = 0;
                Comune common;
                while (i < comuni.length) {
                    String[] tokens = comuni[i].split(":");
                    System.out.println("NUM POSITION " + i + " : " + tokens.length);
                    common = new Comune(tokens);
                    System.out.println("ADD: " + common.getNome());
                    names.add(common.getNome());
                    i++;
                }
            }
            pDialog.dismiss();
        }
    }

    /** CLASSE PER SCARICA IL FILE TOWNS_DETAILS.CSV */
    class DetailsEchoAppalti extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setTitle("Caricamento appalti...");
            pDialog.setMessage("\tAttendere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                // Enter URL address where your php file resides
                url = new URL(strings[0]);

                // Setup HttpURLConnection class to send and receive data from php
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {

            ArrayList<Appalto> appalti = DatiComuni.appalti;

            appalti.clear();

            if(!result.equalsIgnoreCase("unsuccessful")) {

                String[] appalto = result.split("|");
                int i = 0;
                while (i < appalto.length) {
                    String[] tokens = appalto[i].split(";");
                    System.out.println("NUM POSITION " + i + " : " + tokens.length);
                    Appalto common = new Appalto(tokens);
                    System.out.println("ADD: " + common.getIdGara());
                    appalti.add(common);
                    i++;
                }
            }
            pDialog.dismiss();
        }
    }
}
