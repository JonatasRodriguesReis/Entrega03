package com.example.entrega03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProdutosLojaAdapter extends RecyclerView.Adapter {
    public ArrayList<Produto> lista;
    public ArrayList<Produto> listaCarrinho;
    Context context;
    public ProdutosLojaAdapter(ArrayList<Produto> lista, Context context){
        this.lista = lista;
        this.context = context;
        this.listaCarrinho = new ArrayList<Produto>();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_produto_loja, parent, false);
        ProdutosLojaViewHolder holder = new ProdutosLojaViewHolder(context,view, (ArrayList<Produto>) lista);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ProdutosLojaViewHolder holder = (ProdutosLojaViewHolder) viewHolder;
        final Produto item  = lista.get(position) ;
        holder.nome.setText(item.nome);
        holder.preco.setText("R$ " + String.format("%.2f", Float.parseFloat(item.preco)));

        holder.checkProduto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(holder.checkProduto.isChecked()){
                    listaCarrinho.add(item);
                }else{
                    listaCarrinho.remove(item);
                }
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


    public void removerItem(int position) {
        BancoController db = new BancoController(context);
        db.deletaProduto(lista.get(position).nome);
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }
}
