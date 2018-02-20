package com.overhw.counttown;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LF on 20/02/2018.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView idGara;
    public TextView oggetto;
    public TextView luogo;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        idGara = itemView.findViewById(R.id.item_appalto_id_gara);
        oggetto = itemView.findViewById(R.id.item_appalto_oggetto);
        luogo = itemView.findViewById(R.id.item_appalto_luogo);
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<Appalto> listAppalti = new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<Appalto> listAppalti) {
        this.listAppalti = listAppalti;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_appalto, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.idGara.setText(listAppalti.get(position).getIdGara());
        holder.oggetto.setText(listAppalti.get(position).getOggetto());
        holder.luogo.setText(listAppalti.get(position).getLuogoEsecuzione());

    }

    @Override
    public int getItemCount() {
        return listAppalti.size();
    }
}
