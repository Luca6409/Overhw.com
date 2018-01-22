package com.overhw.counttown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button datiInterni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        datiInterni = findViewById(R.id.dati_interni);

        datiInterni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dati = new Intent(Home.this, DatiInterni.class);
                startActivity(dati);
            }
        });
    }
}
