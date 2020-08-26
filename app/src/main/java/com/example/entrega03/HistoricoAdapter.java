package com.example.entrega03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoricoAdapter extends RecyclerView.Adapter {
    public ArrayList<Venda> lista;
    Context context;
    public HistoricoAdapter(ArrayList<Venda> lista, Context context){
        this.lista = lista;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_venda_lista, parent, false);
        VendasViewHolder holder = new VendasViewHolder(context,view, (ArrayList<Venda>) lista);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        VendasViewHolder holder = (VendasViewHolder) viewHolder;
        Venda item  = lista.get(position) ;
        holder.total.setText("R$ " + item.total);
        holder.data.setText(item.data);
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void updateList(Venda venda){
        lista.add(venda);
        notifyItemInserted(getItemCount());
    }

    private void atualizarAdapter(int size){
        notifyItemRangeInserted(getItemCount()-1,size-1);
    }
}
