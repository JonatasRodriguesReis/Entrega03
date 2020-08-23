package com.example.entrega03;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsuariosViewHolder extends RecyclerView.ViewHolder {

        ArrayList<Usuario> lista;
        TextView nome;
        TextView email;
        ImageButton buttonRemover;
        Context contexto;
        public UsuariosViewHolder(Context context, final View itemView, final ArrayList<Usuario> lista) {
            super(itemView);
            this.lista = lista;
            contexto = context;
            nome = (TextView) itemView.findViewById(R.id.txtNome);
            email = itemView.findViewById(R.id.txtEmail);
            buttonRemover = itemView.findViewById(R.id.btnRemover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*int pos = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(),ListaCidadesActivity.class);
                    intent.putExtra("dataApp", dataApp);
                    intent.putExtra("produto", nomeProduto);
                    intent.putExtra("estado", nomeEstado);
                    intent.putExtra("nome",  lista.get(pos).getNome());
                    contexto.startActivity(intent);*/
                }
            });
        }
    }

