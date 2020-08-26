package com.example.entrega03;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProdutosLojaViewHolder extends RecyclerView.ViewHolder {

    ArrayList<Produto> lista;
    TextView nome;
    TextView preco;
    CheckBox checkProduto;
    //ImageButton buttonRemover;
    Context contexto;
    public ProdutosLojaViewHolder(Context context, final View itemView, final ArrayList<Produto> lista) {
        super(itemView);
        this.lista = lista;
        contexto = context;
        nome = (TextView) itemView.findViewById(R.id.txtNome);
        preco = itemView.findViewById(R.id.txtPreco);
        checkProduto = itemView.findViewById(R.id.checkAdicionarCarrinho);
        //buttonRemover = itemView.findViewById(R.id.btnRemover);

    }
}

