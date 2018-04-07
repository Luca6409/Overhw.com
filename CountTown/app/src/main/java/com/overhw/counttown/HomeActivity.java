package com.overhw.counttown;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private Button datiInterni;
    private Button appalti;
    private Button benchmarkComuni;
    private Button info;
    private AutoCompleteTextView citta;
    private ImageButton salvaCitta;
    private CsvUtil csvUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        csvUtil = new CsvUtil(this);

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


        benchmarkComuni = (Button) findViewById(R.id.bottone_benchmark_comuni);

        benchmarkComuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent benchmarkMultiploPage = new Intent(HomeActivity.this,Benchmark.class);
                startActivity(benchmarkMultiploPage);
            }
        });

        info = (Button) findViewById(R.id.bottone_info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infopage = new Intent(HomeActivity.this,Info.class);
                startActivity(infopage);
            }
        });

        citta = findViewById(R.id.citta);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DatiComuni.nomi_citta);
        citta.setAdapter(adapter);
        citta.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    switch(i){
                        case KeyEvent.KEYCODE_ENTER:
                            imm.hideSoftInputFromWindow(citta.getWindowToken(), 0);
                            String newcity = citta.getText().toString().substring(0,1).toUpperCase() + citta.getText().toString().substring(1,citta.getText().toString().length());
                            System.out.println(newcity);
                            citta.setText(newcity);
                            int position = DatiComuni.nomi_citta.indexOf(newcity);
                            DatiComuni.comune_utente = DatiComuni.dettagli_citta.get(position);
                            Snackbar.make(view, "Comune salvato con successo", Snackbar.LENGTH_SHORT).show();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        salvaCitta = findViewById(R.id.salvaCitta);

        salvaCitta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = DatiComuni.nomi_citta.indexOf(citta.getText().toString());
                DatiComuni.comune_utente = DatiComuni.dettagli_citta.get(position);
                Snackbar.make(view, "Comune salvato con successo", Snackbar.LENGTH_SHORT).show();
            }
        });


        if(DatiComuni.dettagli_citta.isEmpty()){
            csvUtil.downloadTownsDetails();
        }
        if(DatiComuni.appalti.isEmpty()){
            csvUtil.downloadBandi();
        }
    }
}
