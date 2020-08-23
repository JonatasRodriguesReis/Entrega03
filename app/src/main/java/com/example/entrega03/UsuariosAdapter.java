package com.example.entrega03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsuariosAdapter extends RecyclerView.Adapter {
    public ArrayList<Usuario> lista;
    Context context;
    public UsuariosAdapter(ArrayList<Usuario> lista, Context context){
        this.lista = lista;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_usuario_lista, parent, false);
        UsuariosViewHolder holder = new UsuariosViewHolder(context,view, (ArrayList<Usuario>) lista);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        UsuariosViewHolder holder = (UsuariosViewHolder) viewHolder;
        Usuario item  = lista.get(position) ;
        holder.nome.setText(item.nome);
        holder.email.setText(item.email);

        holder.buttonRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removerItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void updateList(Usuario usuario){
        lista.add(usuario);
        notifyItemInserted(getItemCount());
    }

    private void atualizarAdapter(int size){
        notifyItemRangeInserted(getItemCount()-1,size-1);
    }

    // Método responsável por remover um usuário da lista.
    public void removerItem(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }
}

