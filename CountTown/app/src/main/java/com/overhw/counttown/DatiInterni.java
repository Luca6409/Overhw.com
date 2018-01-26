package com.overhw.counttown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by LF on 26/01/2018.
 */

public class DatiInterni extends AppCompatActivity {

    private Toolbar toolbar;
    private AutoCompleteTextView citta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dati_interni);

        toolbar = findViewById(R.id.toolbar_dati_interni);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        citta = findViewById(R.id.dati_interni_edittext_citta);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DatiComuni.nomi_citta);
        citta.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
