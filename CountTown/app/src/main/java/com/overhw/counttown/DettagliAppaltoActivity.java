package com.overhw.counttown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class DettagliAppaltoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_appalto);

        toolbar = findViewById(R.id.dettagli_appalto_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Bundle dati = getIntent().getExtras();
        int position = dati.getInt("Appalto");

        Appalto dettagliAppalto = DatiComuni.appalti.get(position);

        back = findViewById(R.id.dettagli_appalto_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
