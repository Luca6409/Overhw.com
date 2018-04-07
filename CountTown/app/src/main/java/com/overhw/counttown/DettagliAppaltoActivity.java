package com.overhw.counttown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DettagliAppaltoActivity extends AppCompatActivity {


    private TextView idGara, codiceFiscaleAppaltante, denominazioneStazioneAppaltante, dataPubbBando, tipoBando, tipoSettore, infrStrategica, oggetto, tipoIntervento, luogoEsecuzione;
    private TextView importo, categoria, classe, impLavori, tipoProcedura, astaElettronica, forcella, appaltoRiservato, termineRidotto, fpDurataConc, fpNote, url;

    private Toolbar toolbar;
    private TextView toolbarId;

    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_appalto);

        toolbar = findViewById(R.id.dettagli_appalto_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarId = findViewById(R.id.dettagli_appalto_comune);


        idGara = findViewById(R.id.id_gara);
        codiceFiscaleAppaltante = findViewById(R.id.codice_fiscale_appaltante);
        denominazioneStazioneAppaltante = findViewById(R.id.denominazione_stazione_appaltante);
        dataPubbBando = findViewById(R.id.data_pubb_bando);
        tipoBando = findViewById(R.id.tipo_bando);
        tipoSettore = findViewById(R.id.tipo_settore);
        infrStrategica = findViewById(R.id.infr_strategica);
        oggetto = findViewById(R.id.oggetto);
        tipoIntervento = findViewById(R.id.tipo_intervento);
        luogoEsecuzione = findViewById(R.id.luogo_esecuzione);
        importo = findViewById(R.id.importo);
        categoria = findViewById(R.id.categoria);
        classe = findViewById(R.id.classe);
        impLavori = findViewById(R.id.imp_lavori);
        tipoProcedura = findViewById(R.id.tipo_procedura);
        astaElettronica = findViewById(R.id.asta_elettronica);
        forcella = findViewById(R.id.forcella);
        appaltoRiservato = findViewById(R.id.appalto_riservato);
        termineRidotto = findViewById(R.id.termine_ridotto);
        fpDurataConc = findViewById(R.id.fp_durata_conc);
        fpNote = findViewById(R.id.fp_note);
        url = findViewById(R.id.url);

        Bundle dati = getIntent().getExtras();
        int position = dati.getInt("Appalto");

        caricaAppalto(position);

        back = findViewById(R.id.dettagli_appalto_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void caricaAppalto(int position){
        Appalto dettagliAppalto = DatiComuni.appalti.get(position);
        toolbarId.setText(Html.fromHtml("<b>" + dettagliAppalto.getIdGara() + "</b>"));
        idGara.setText(Html.fromHtml("<b>Id gara</b>: " + dettagliAppalto.getIdGara()));
        codiceFiscaleAppaltante.setText(Html.fromHtml("<b>Codice fiscale appaltante</b>: " + dettagliAppalto.getCodiceFiscaleStazioneAppaltante()));
        denominazioneStazioneAppaltante.setText(Html.fromHtml("<b>Denominazione stazione appaltante</b>: " + dettagliAppalto.getDenominazioneStazioneAppaltante()));
        dataPubbBando.setText(Html.fromHtml("<b>Data pubblicazione bando</b>: " + dettagliAppalto.getDataPubbBandoScp()));
        tipoBando.setText(Html.fromHtml("<b>Tipo bando</b>: " + dettagliAppalto.getTipoBando()));
        tipoSettore.setText(Html.fromHtml("<b>Tipo settore</b>: " + dettagliAppalto.getTipoSettore()));
        infrStrategica.setText(Html.fromHtml("<b>Infrastruttura strategica</b>: " + dettagliAppalto.getInfrStrategica()));
        oggetto.setText(Html.fromHtml("<b>Oggetto</b>: " + dettagliAppalto.getOggetto()));
        tipoIntervento.setText(Html.fromHtml("<b>Tipo intervento</b>: " + dettagliAppalto.getTipoIntervento()));
        luogoEsecuzione.setText(Html.fromHtml("<b>Luogo esecuzione</b>: " + dettagliAppalto.getLuogoEsecuzione()));
        importo.setText(Html.fromHtml("<b>Importo</b>: " + dettagliAppalto.getImporto()));
        categoria.setText(Html.fromHtml("<b>Codice categoria</b>: " + dettagliAppalto.getCategoria()));
        classe.setText(Html.fromHtml("<b>Classe</b>: " + dettagliAppalto.getClasse()));
        impLavori.setText(Html.fromHtml("<b>Importo lavori</b>: " + dettagliAppalto.getImpLavori()));
        tipoProcedura.setText(Html.fromHtml("<b>Tipo procedura</b>: " + dettagliAppalto.getTipoProcedura()));
        astaElettronica.setText(Html.fromHtml("<b>Asta elettronica</b>: " + dettagliAppalto.getAstaElettronica()));
        forcella.setText(Html.fromHtml("<b>Forcella</b>: " + dettagliAppalto.getForcella()));
        appaltoRiservato.setText(Html.fromHtml("<b>Appalto riservato</b>: " + dettagliAppalto.getAppaltoRiservato()));
        termineRidotto.setText(Html.fromHtml("<b>Termine ridotto</b>: " + dettagliAppalto.getTermineRidotto()));
        fpDurataConc.setText(Html.fromHtml("<b>Durata massima concessione</b>: " + dettagliAppalto.getFpDurataConces() + " anni"));
        fpNote.setText(Html.fromHtml("<b>Note</b>: " + dettagliAppalto.getFpNote()));
        url.setText(Html.fromHtml("<b>URL</b>: " + dettagliAppalto.getUrl()));
    }
}
