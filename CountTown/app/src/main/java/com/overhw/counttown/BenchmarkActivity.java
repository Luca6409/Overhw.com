package com.overhw.counttown;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by LP on 04/02/2018.
 */

public class BenchmarkActivity extends AppCompatActivity {

    static String[] cinqueComuni = new String[5];
    static String benComune1, benComune2, benComune3, benComune4, benComune5;
    private TextView ncomune, comune1, comune2, comune3, comune4, comune5;

    public void callsettaComuni(String[] cinqueComuni) {


        boolean check = settaComuni(cinqueComuni);
        if (check == false) {
            Intent home = new Intent(BenchmarkActivity.this, HomeActivity.class);  /*Se non trova il nome del comune riporta alla home */
            startActivity(home);
            Context context = getApplicationContext();
            CharSequence text = "Non sono stati trovati abbastanza comuni per il benchmark!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public boolean settaComuni(String[] cinqueComuni) {
        if (cinqueComuni != null) {
            ncomune = findViewById(R.id.benchNcomune);
            comune1 = findViewById(R.id.benchComune1);
            comune2 = findViewById(R.id.benchComune2);
            comune3 = findViewById(R.id.benchComune3);
            comune4 = findViewById(R.id.benchComune4);
            comune5 = findViewById(R.id.benchComune5);

            /*Set*/
            ncomune.setText(DatiComuni.dettagli_comune.getNome());
            comune1.setText(cinqueComuni[0]);
            BenchmarkActivity.benComune1 = cinqueComuni[0];

            comune2.setText(cinqueComuni[1]);
            BenchmarkActivity.benComune2 = cinqueComuni[1];

            comune3.setText(cinqueComuni[2]);
            BenchmarkActivity.benComune3 = cinqueComuni[2];

            comune4.setText(cinqueComuni[3]);
            BenchmarkActivity.benComune4 = cinqueComuni[3];

            comune5.setText(cinqueComuni[4]);
            BenchmarkActivity.benComune5 = cinqueComuni[4];

            /*On click listener*/
            comune1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent comune1act = new Intent(BenchmarkActivity.this, BenComune1Activity.class);
                    startActivity(comune1act);
                }
            });
            comune2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent comune2act = new Intent(BenchmarkActivity.this, BenComune2Activity.class);
                    startActivity(comune2act);
                }
            });
            comune3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent comune3act = new Intent(BenchmarkActivity.this, BenComune3Activity.class);
                    startActivity(comune3act);
                }
            });
            comune4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent comune4act = new Intent(BenchmarkActivity.this, BenComune4Activity.class);
                    startActivity(comune4act);
                }
            });
            comune5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent comune5act = new Intent(BenchmarkActivity.this, BenComune5Activity.class);
                    startActivity(comune5act);
                }
            });
            return true;

        } else {
            return false;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benchmark_comuni);
        if (DatiComuni.dettagli_comune != null) {

            new ChooseIndexEcho().execute("https://overhw.com/counttown/scripts/choose_town.php?ncomune=" + DatiComuni.dettagli_comune.getNome()); /*Salva in cinqueComuni il risultato*/
            callsettaComuni(BenchmarkActivity.cinqueComuni);


        }/*Chiusura if */ else {
            Intent home = new Intent(BenchmarkActivity.this, HomeActivity.class);  /*Se non trova il nome del comune riporta alla home */
            startActivity(home);
            Context context = getApplicationContext();
            CharSequence text = "Prima devi salvare il comune nella HOME!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }/* Chiusura else*/

    }    /*Chiusura onCreate */

}/*Chiusura classe BenchmarkActivity*/


/*CLASSE PER SALVARE DATI DA ChooseIndex */
class ChooseIndexEcho extends AsyncTask<String, Integer, String>  {
    HttpURLConnection conn;
    URL url = null;

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
    } /*Chiusura do it in background*/

    @Override
    protected void onPostExecute(String result) {

        if(!result.equalsIgnoreCase("unsuccessful")) {

            BenchmarkActivity.cinqueComuni= null;
            String [] res = result.split(",");
            BenchmarkActivity.cinqueComuni = (String[])res.clone();
            System.out.println(Arrays.toString(BenchmarkActivity.cinqueComuni));


        }
     } /*Chiusura onPostExecute*/

}