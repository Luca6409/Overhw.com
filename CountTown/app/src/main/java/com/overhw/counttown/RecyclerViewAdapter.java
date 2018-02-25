package com.overhw.counttown;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by LF on 20/02/2018.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{

    public TextView tipoIntervento;
    public TextView idGara;
    public TextView dataPubblicazione;
    public TextView luogo;
    public TextView denomStazApp;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        tipoIntervento = itemView.findViewById(R.id.item_appalto_tipo_intervento);
        idGara = itemView.findViewById(R.id.item_appalto_id_gara);
        dataPubblicazione = itemView.findViewById(R.id.item_appalto_data_pubb);
        luogo = itemView.findViewById(R.id.item_appalto_luogo);
        denomStazApp = itemView.findViewById(R.id.item_appalto_denom_staz_appaltante);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<Appalto> listAppalti = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<Appalto> listAppalti, Context context) {
        this.listAppalti = listAppalti;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_appalto, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tipoIntervento.setText(listAppalti.get(position).getTipoIntervento());
        holder.idGara.setText(listAppalti.get(position).getIdGara());
        holder.dataPubblicazione.setText(listAppalti.get(position).getDataPubbBandoScp());
        holder.luogo.setText(listAppalti.get(position).getLuogoEsecuzione());
        holder.denomStazApp.setText(listAppalti.get(position).getDenominazioneStazioneAppaltante());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, "Long Click " + listAppalti.get(position).getIdGara(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Click " + listAppalti.get(position).getIdGara(), Toast.LENGTH_SHORT).show();

                    // passaggio alla scheda dettagli appalto
                    Intent dettagliAppalto = new Intent(context.getApplicationContext(), DettagliAppaltoActivity.class);
                    dettagliAppalto.putExtra("Appalto", position);
                    context.startActivity(dettagliAppalto);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAppalti.size();
    }
}
