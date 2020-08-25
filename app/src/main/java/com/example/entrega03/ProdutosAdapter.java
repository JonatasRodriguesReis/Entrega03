package com.example.entrega03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProdutosAdapter extends RecyclerView.Adapter {
    public ArrayList<Produto> lista;
    Context context;
    public ProdutosAdapter(ArrayList<Produto> lista, Context context){
        this.lista = lista;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_produto_lista, parent, false);
        ProdutosViewHolder holder = new ProdutosViewHolder(context,view, (ArrayList<Produto>) lista);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ProdutosViewHolder holder = (ProdutosViewHolder) viewHolder;
        Produto item  = lista.get(position) ;
        holder.nome.setText(item.nome);
        holder.preco.setText(item.preco);

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

    public void updateList(Produto produto){
        lista.add(produto);
        notifyItemInserted(getItemCount());
    }

    private void atualizarAdapter(int size){
        notifyItemRangeInserted(getItemCount()-1,size-1);
    }

    // Método responsável por remover um usuário da lista.
    public void removerItem(int position) {
        BancoController db = new BancoController(context);
        db.deletaProduto(lista.get(position).nome);
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }
}
