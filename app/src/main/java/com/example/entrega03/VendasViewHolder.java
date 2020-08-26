package com.example.entrega03;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VendasViewHolder extends RecyclerView.ViewHolder {

    ArrayList<Venda> lista;
    TextView total;
    TextView data;
    Context contexto;
    public VendasViewHolder(Context context, final View itemView, final ArrayList<Venda> lista) {
        super(itemView);
        this.lista = lista;
        contexto = context;
        total = (TextView) itemView.findViewById(R.id.txtTotalValor);
        data = itemView.findViewById(R.id.txtDataValor);
    }
}

