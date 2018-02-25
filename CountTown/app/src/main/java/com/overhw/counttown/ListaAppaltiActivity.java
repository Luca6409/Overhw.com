package com.overhw.counttown;

import android.content.Context;
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
import android.widget.Toast;

/**
 * Created by Luca Fabris on 20/02/2018.
 */

public class ListaAppaltiActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton back, search;
    private AutoCompleteTextView citta;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_appalti);

        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

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
                searchCityContracts(citta.getText().toString());
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
                            searchCityContracts(citta.getText().toString());
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        initData();
        recyclerView = findViewById(R.id.lista_appalti_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(DatiComuni.appalti, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        /*DatiComuni.appalti.add(new Appalto("Manutenzione ""1", "Oggetto1", "Luogo1"));
        DatiComuni.appalti.add(new Appalto("2", "Oggetto2", "Luogo2"));
        DatiComuni.appalti.add(new Appalto("3", "Oggetto3", "Luogo3"));
        DatiComuni.appalti.add(new Appalto("4", "Oggetto4", "Luogo4"));
        DatiComuni.appalti.add(new Appalto("5", "Oggetto5", "Luogo5"));
        DatiComuni.appalti.add(new Appalto("6", "Oggetto6", "Luogo6"));
        DatiComuni.appalti.add(new Appalto("7", "Oggetto7", "Luogo7"));
        DatiComuni.appalti.add(new Appalto("8", "Oggetto8", "Luogo8"));*/


    }

    private void searchCityContracts(String s) {
        Toast.makeText(this, "Searching City", Toast.LENGTH_SHORT).show();
    }
}
