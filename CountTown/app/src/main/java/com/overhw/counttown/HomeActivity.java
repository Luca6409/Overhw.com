package com.overhw.counttown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button datiInterni;
    private Button appalti;
    private Button benchmarkComuni;
    private Button info;
    private CsvUtil csvUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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


        if(DatiComuni.dettagli_citta.isEmpty()){
            csvUtil.downloadTownsDetails();
        }
        /*if(DatiComuni.appalti.isEmpty()){
            csvUtil.downloadBandi();
        }*/
    }
}
