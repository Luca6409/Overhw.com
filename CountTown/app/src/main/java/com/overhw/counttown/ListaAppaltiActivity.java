package com.overhw.counttown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Luca Fabris on 20/02/2018.
 */


public class ListaAppaltiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Appalto> appalti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_appalti);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.lista_appalti_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(appalti);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        appalti.add(new Appalto("1", "Oggetto1", "Luogo1"));
        appalti.add(new Appalto("2", "Oggetto2", "Luogo2"));
        appalti.add(new Appalto("3", "Oggetto3", "Luogo3"));
        appalti.add(new Appalto("4", "Oggetto4", "Luogo4"));
        appalti.add(new Appalto("5", "Oggetto5", "Luogo5"));
        appalti.add(new Appalto("6", "Oggetto6", "Luogo6"));
        appalti.add(new Appalto("7", "Oggetto7", "Luogo7"));
        appalti.add(new Appalto("8", "Oggetto8", "Luogo8"));
    }
}
