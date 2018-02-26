package com.overhw.counttown;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Luca Fabris on 26/02/2018.
 */

public class CsvUtil {

    private Context context;

    public CsvUtil(Context context){
        this.context = context;
    }

    public void downloadTownsDetails(){
        File townsDetailsFile = new File(Environment.getExternalStorageDirectory() + "/towns_details.csv");
        if(townsDetailsFile.exists()){
            String line;
            DatiComuni.dettagli_citta.clear();
            DatiComuni.nomi_citta.clear();
            try {
                BufferedReader br = new BufferedReader(new FileReader(townsDetailsFile));
                while ((line = br.readLine()) != null) {
                    // split bt ';'
                    String[] tokens = line.split(";");
                    Comune comune = new Comune(tokens);
                    DatiComuni.dettagli_citta.add(comune);
                    DatiComuni.nomi_citta.add(comune.getNome());
                    System.out.println("Città aggiunta:  " + comune.getNome());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            new DownloadFileFromURL().execute("http://overhw.com/counttown/towns_details.csv");
        }
    }

    public void downloadBandi(){
        File bandi = new File(Environment.getExternalStorageDirectory() + "/scpbandi2.csv");
        if(bandi.exists()){
            String line;
            DatiComuni.appalti.clear();
            try{
                BufferedReader br = new BufferedReader(new FileReader(bandi));
                while((line = br.readLine()) != null){
                    String[] tokens = line.split(",");
                    Appalto appalto = new Appalto(tokens);
                    DatiComuni.appalti.add(appalto);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            new DownloadBandiURL().execute("http://overhw.com/counttown/scpbandi2.csv");
        }
    }

    /** CLASSE PER SCARICA IL FILE TOWNS_DETAILS.CSV */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download towns_details.csv");

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            //pDialog.show();
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String root = Environment.getExternalStorageDirectory().toString();
            try {
                System.out.println("Downloading");
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/towns_details.csv");
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + total*100/lenghtOfFile);
                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return root + "/towns_details.csv";
        }


        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("towns_details.csv downloaded");
            ArrayList<Comune> townsList = DatiComuni.dettagli_citta;
            ArrayList<String> nameTown = DatiComuni.nomi_citta;

            townsList.clear();
            nameTown.clear();

            String line;
            try {
                File towns = new File(file_url);
                if(towns.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(towns));
                    while ((line = br.readLine()) != null) {
                        // split bt ';'
                        String[] tokens = line.split(";");
                        Comune comune = new Comune(tokens);
                        townsList.add(comune);
                        nameTown.add(comune.getNome());
                        System.out.println("Città aggiunta (IN DOWNLOAD):  " + comune.getNome());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }

        @Override
        public void onProgressUpdate(String... args){
            pDialog.setProgress(Integer.parseInt(args[0]));
        }
    }


    /** CLASSE PER SCARICA IL FILE TOWNS_DETAILS.CSV */
    class DownloadBandiURL extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download scpbandi2.csv");

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Download Bandi in corso... Attendere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.show();

        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String root = Environment.getExternalStorageDirectory().toString();
            try {
                System.out.println("Downloading");
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/scpbandi2.csv");
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + total*100 / lenghtOfFile);
                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return root + "/scpbandi2.csv";
        }


        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("scpbandi2.csv downloaded");
            ArrayList<Appalto> appaltiList = DatiComuni.appalti;

            appaltiList.clear();

            String line;
            try {
                File towns = new File(file_url);
                if(towns.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(towns));
                    while ((line = br.readLine()) != null) {
                        // split bt ';'
                        String[] tokens = line.split(",");
                        Appalto appalto = new Appalto(tokens);
                        appaltiList.add(appalto);
                        System.out.println("Appalto aggiunto (IN DOWNLOAD):  " + appalto.getIdGara());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }

        @Override
        public void onProgressUpdate(String... args){
            pDialog.setProgress(Integer.parseInt(args[0]));
        }
    }
}
