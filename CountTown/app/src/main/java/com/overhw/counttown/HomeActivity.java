package com.overhw.counttown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button datiInterni;
    private Button appalti;

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

        if(DatiComuni.dettagli_citta.isEmpty()){
            csvUtil.downloadTownsDetails();
        }
        if(DatiComuni.appalti.isEmpty()){
            csvUtil.downloadBandi();
        }
    }
}
