package com.overhw.counttown;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * Created by Luca Fabris on 26/01/2018.
 */

public class DatiInterniActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap googleMap;

    private Toolbar toolbar;
    private AutoCompleteTextView citta;
    private ImageButton back, search;

    private TextView nome, istat, provincia, siglaProvincia, regione, areaGeo, popRes, popStr, densDem, supKmq, altezzaCentro, altezzaMin, altezzaMax, zonaAlti, zonaSismica, tipoComune, gradoUrban, indiceMont, zonaClim, classeComune, latitudine, longitudine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(googleServiceAvailable()){
            setContentView(R.layout.activity_dati_interni);
            initMap();
        }else{
            // No google maps layout
        }

        final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        toolbar = findViewById(R.id.toolbar_dati_interni);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        nome = findViewById(R.id.nome_citta_interni);
        istat = findViewById(R.id.istat_interni);
        provincia = findViewById(R.id.provincia_interni);
        siglaProvincia = findViewById(R.id.sigla_provincia_interni);
        regione = findViewById(R.id.regione_interni);
        areaGeo = findViewById(R.id.area_geo_interni);
        popRes = findViewById(R.id.pop_residente_interni);
        popStr = findViewById(R.id.pop_straniera_interni);
        densDem = findViewById(R.id.densita_demo_interni);
        supKmq = findViewById(R.id.sup_kmq_interni);
        altezzaCentro = findViewById(R.id.altezza_centro_interni);
        altezzaMin = findViewById(R.id.altezza_minima_interni);
        altezzaMax = findViewById(R.id.altezza_massima_interni);
        zonaAlti = findViewById(R.id.zona_altimetrica_interni);
        zonaSismica = findViewById(R.id.zona_sismica_interni);
        tipoComune = findViewById(R.id.tipo_comune_interni);
        gradoUrban = findViewById(R.id.grado_urbanizzazione_interni);
        indiceMont = findViewById(R.id.indice_mintanita_interni);
        zonaClim = findViewById(R.id.zona_climatica_interni);
        classeComune = findViewById(R.id.classe_comune_interni);
        latitudine = findViewById(R.id.latitudine_interni);
        longitudine = findViewById(R.id.longitudine_interni);

        back = findViewById(R.id.interni_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        search = findViewById(R.id.interni_search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(citta.getWindowToken(), 0);
                searchCity(citta.getText().toString());
            }
        });

        citta = findViewById(R.id.dati_interni_edittext_citta);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DatiComuni.nomi_citta);
        citta.setAdapter(adapter);
        citta.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    switch(i){
                        case KeyEvent.KEYCODE_ENTER:
                            imm.hideSoftInputFromWindow(citta.getWindowToken(), 0);
                            searchCity(citta.getText().toString());
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
    }

    protected void searchCity(String city){
        /* the variable city is the new town to search in ArrayList in DatiComuni.class */

        String newcity = city.substring(0,1).toUpperCase() + city.substring(1,city.length());
        System.out.println(newcity);

        int position = DatiComuni.nomi_citta.indexOf(newcity);

        citta.setText(newcity);

        /* position = -1 : town not found */
        if(position < 0){
            Toast.makeText(getApplicationContext(), "Town not found", Toast.LENGTH_SHORT).show();
        }
        else {
            DatiComuni.comune_utente = DatiComuni.dettagli_citta.get(position);
            float lat = Float.parseFloat(DatiComuni.comune_utente.getLatitudine().replace(",", "."));
            float lng = Float.parseFloat(DatiComuni.comune_utente.getLongitudine().replace(",", "."));
            goToLocation(lat, lng, 12);
            showData();
        }

    }

    private void initialize(){
        if(DatiComuni.comune_utente != null) {
            citta.setText(DatiComuni.comune_utente.getNome());
            showData();
        }
    }

    private void showData(){
        nome.setText(Html.fromHtml("<font color='#FF5722'><b>" + DatiComuni.comune_utente.getNome() + "</b></font>"));
        istat.setText(Html.fromHtml("<b>Codice ISTAT: </b>" + DatiComuni.comune_utente.getIstat()));
        provincia.setText(Html.fromHtml("<b>Provincia: </b>" + DatiComuni.comune_utente.getProvincia() + " (" + "<b>" + DatiComuni.comune_utente.getSiglaProvincia() + "</b>"+ ")"));
        siglaProvincia.setText(Html.fromHtml("<b>Sigla provincia: </b>" + DatiComuni.comune_utente.getSiglaProvincia()));
        regione.setText(Html.fromHtml("<b>Regione: </b>" + DatiComuni.comune_utente.getRegione()));
        areaGeo.setText(Html.fromHtml("<b>Area geografica: </b>" + DatiComuni.comune_utente.getAreaGeo()));
        popRes.setText(Html.fromHtml("<b>Popolazione residente: </b>" + DatiComuni.comune_utente.getPopolazioneResidente() + " ab."));
        popStr.setText(Html.fromHtml("<b>Popolazione straniera: </b>" + DatiComuni.comune_utente.getPopolazioneStraniera() + " ab."));
        densDem.setText(Html.fromHtml("<b>Densità demografica: </b>" + DatiComuni.comune_utente.getDensitaDemografica() + " ab./km²"));
        supKmq.setText(Html.fromHtml("<b>Superficie: </b>" + DatiComuni.comune_utente.getSuperficieKmq() + " km²"));
        altezzaCentro.setText(Html.fromHtml("<b>Altezza centro: </b>" + DatiComuni.comune_utente.getAltezzaCentro() + " m s.l.m."));
        altezzaMin.setText(Html.fromHtml("<b>Altezza minima: </b>" + DatiComuni.comune_utente.getAltezzaMinima() + " m s.l.m."));
        altezzaMax.setText(Html.fromHtml("<b>Altezza massima: </b>" + DatiComuni.comune_utente.getAltezzaMassima() + " m s.l.m."));
        zonaAlti.setText(Html.fromHtml("<b>Zona altimetrica: </b>" + DatiComuni.comune_utente.getZonaAltimetrica()));
        tipoComune.setText(Html.fromHtml("<b>Tipo comune: </b>" + DatiComuni.comune_utente.getTipoComune()));
        gradoUrban.setText(Html.fromHtml("<b>Grado urbanizzazione: </b>" + DatiComuni.comune_utente.getGradoUrbanizzazione()));
        indiceMont.setText(Html.fromHtml("<b>Indice montanità: </b>" + DatiComuni.comune_utente.getIndiceMontanita()));
        zonaClim.setText(Html.fromHtml("<b>Zona climatica: </b>" + DatiComuni.comune_utente.getZonaClimatica()));
        zonaSismica.setText(Html.fromHtml("<b>Zona sismica: </b>" + DatiComuni.comune_utente.getZonaSismica()));
        classeComune.setText(Html.fromHtml("<b>Classe comune: </b>" + DatiComuni.comune_utente.getClasseComune()));
        float lat = Float.parseFloat(DatiComuni.comune_utente.getLatitudine().replace(",", "."));
        float lng = Float.parseFloat(DatiComuni.comune_utente.getLongitudine().replace(",", "."));
        latitudine.setText(Html.fromHtml("<b>Latitudine: </b>" + String.format(Locale.ITALY,"%.2f", lat)));
        longitudine.setText(Html.fromHtml("<b>Longitudine: </b>" + String.format(Locale.ITALY,"%.2f", lng)));
    }

    public boolean googleServiceAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }
        else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else{
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        this.googleMap = googleMap;
        if(DatiComuni.comune_utente!=null){
            float lat = Float.parseFloat(DatiComuni.comune_utente.getLatitudine().replace(",", "."));
            float lng = Float.parseFloat(DatiComuni.comune_utente.getLongitudine().replace(",", "."));
            goToLocation(lat,lng, 12);
        }

    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(ll).title(DatiComuni.comune_utente.getNome()));
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        googleMap.moveCamera(update);
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(ll).title(DatiComuni.comune_utente.getNome()));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        googleMap.moveCamera(update);
    }
}
