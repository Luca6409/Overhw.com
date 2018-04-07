package com.overhw.counttown;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Luca Fabris on 20/02/2018.
 */

public class ListaAppaltiActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton back, search;
    private AutoCompleteTextView citta;
    private Spinner spinner;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_appalti);

        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        recyclerView = findViewById(R.id.lista_appalti_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(new ArrayList<Appalto>(), this);
        recyclerView.setAdapter(adapter);

        toolbar = findViewById(R.id.lista_appalti_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        back = findViewById(R.id.lista_appalti_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        search = findViewById(R.id.lista_appalti_search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(citta.getWindowToken(), 0);
                new DetailsEchoAppalti().execute("https://overhw.com/counttown/scripts/appalti_details_anno.php?ncomune=" + citta.getText().toString() + "&anno=" + spinner.getSelectedItem().toString());
            }
        });

        citta = findViewById(R.id.lista_appalti_edittext_citta);
        ArrayAdapter<String> adapterString = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DatiComuni.nomi_citta);
        citta.setAdapter(adapterString);
        citta.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    switch(i){
                        case KeyEvent.KEYCODE_ENTER:
                            imm.hideSoftInputFromWindow(citta.getWindowToken(), 0);
                            new DetailsEchoAppalti().execute("https://overhw.com/counttown/scripts/appalti_details_anno.php?ncomune=" + citta.getText().toString() + "&anno=" + spinner.getSelectedItem().toString());
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        spinner = findViewById(R.id.spinner_lista_appalti);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.anniappalti, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        initData();
    }

    private void initData() {
        if(DatiComuni.dettagli_comune != null){
            citta.setText(DatiComuni.dettagli_comune.getNome());
        }
    }

    private void searchCityContracts(String s) {
        System.out.println("Per il comune di " + s + " sono stati trovati " + DatiComuni.appalti.size() + " appalti");

        if(DatiComuni.appalti.size() > 0){
            RecyclerViewAdapter newAdapter = new RecyclerViewAdapter(DatiComuni.appalti, this);
            recyclerView.swapAdapter(newAdapter, true);
        }
        else{
            Snackbar.make(recyclerView, "Nessun appalto trovato", Snackbar.LENGTH_SHORT).show();
        }
    }





    class DetailsEchoAppalti extends AsyncTask<String, Integer, String> {
        ProgressDialog pDialog;
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(ListaAppaltiActivity.this);
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

            DatiComuni.appalti.clear();

            if(!result.equalsIgnoreCase("unsuccessful")) {
                String[] appalto = result.split("€");
                System.out.println("NUMERO APPALTI: " + appalto.length);
                int i = 0;
                Appalto common;
                String[] tokens;
                while (i < appalto.length) {
                    tokens = appalto[i].split(";");
                    common = new Appalto(tokens);
                    if(common.getIdGara() != null) {
                        DatiComuni.appalti.add(common);
                        System.out.println("ADD: " + common.getIdGara());
                    }
                    i++;
                }
            }
            searchCityContracts(citta.getText().toString());
            pDialog.dismiss();
        }
    }
}



