package com.overhw.counttown;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button datiInterni;
    private Button appalti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        datiInterni = findViewById(R.id.dati_interni);

        datiInterni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dati = new Intent(HomeActivity.this, DatiInterniActivity.class);
                startActivity(dati);
            }
        });

        appalti = findViewById(R.id.appalti);

        appalti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appalti = new Intent(HomeActivity.this, ListaAppaltiActivity.class);
                startActivity(appalti);
            }
        });
    }

    /** Metodo chiamato all'avvio dell'activity */
    @Override
    protected void onStart() {
        super.onStart();
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            new DownloadFileFromURL().execute("http://overhw.com/counttown/towns_details.csv");
        }
        //initialize();
    }

    /** SCARICA O CARICA IL FILE ALL'AVVIO DELL'ACTIVITY */
    private void initialize(){
        File townsDetailsFile = new File(Environment.getExternalStorageDirectory() + "/towns_details.csv");
        if(townsDetailsFile.exists()){
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(townsDetailsFile));
                while ((line = br.readLine()) != null) {
                    // split bt ';'
                    String[] tokens = line.split(";");
                    Comune comune = new Comune(tokens);
                    DatiComuni.dettagli_citta.add(comune);
                    DatiComuni.nomi_citta.add(comune.getNome());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            new DownloadFileFromURL().execute("http://overhw.com/counttown/towns_details.csv");
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
            System.out.println("Starting download");

            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
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
            System.out.println("Downloaded");
            ArrayList<Comune> townsList = DatiComuni.dettagli_citta;
            ArrayList<String> nameTown = DatiComuni.nomi_citta;

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
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }
    }


    /** SCARICA I FILE DEI BANDI */
    class DownloadBandiURL extends AsyncTask<URL, Void, ArrayList<Appalto>> {


        @Override
        protected ArrayList<Appalto> doInBackground(URL... f_urls) {
            /*int count;
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

                OutputStream output = new FileOutputStream(root + "/scpbandi.csv");
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
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
            }*/
            return downloadRemoteTextFileContent();
        }


        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(ArrayList<Appalto> appalti) {

            if(appalti != null){
                Toast.makeText(getApplicationContext(), "File bandi letto con successo", Toast.LENGTH_SHORT).show();
            }
            /*System.out.println("Downloaded");
            ArrayList<Appalto> appalti = DatiComuni.appalti;

            String line;
            try {
                File towns = new File(file_url);
                if(towns.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(towns));
                    while ((line = br.readLine()) != null) {
                        // split bt ';'
                        String[] tokens = line.split(",");
                        Appalto appalto = new Appalto(tokens);
                        appalti.add(appalto);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }


    /** CLASSE PER SCARICA IL FILE SCPBANDI2.CSV */
    class DownloadBandiFromURL extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download scpbandi2.csv");

            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
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
            System.out.println("SCPBANDI2 Downloaded");
            ArrayList<Appalto> appalti = DatiComuni.appalti;

            String line;
            try {
                File towns = new File(file_url);
                if(towns.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(towns));
                    while ((line = br.readLine()) != null) {
                        // split bt ';'
                        String[] tokens = line.split(",");
                        Appalto appalto = new Appalto(tokens);
                        appalti.add(appalto);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }
    }

    private ArrayList<Appalto> downloadRemoteTextFileContent(){
        URL mUrl = null;
        ArrayList<Appalto> csvLine = DatiComuni.appalti;
        String[] content = null;
        try {
            mUrl = new URL("http://overhw.com/counttown/scpbandi2.csv");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert mUrl != null;
            URLConnection connection = mUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null){
                content = line.split(",");
                Appalto appalto = new Appalto(content);
                csvLine.add(appalto);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvLine;
    }
}
