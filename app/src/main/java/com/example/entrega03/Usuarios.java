package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuariosAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        recyclerView = (RecyclerView) findViewById(R.id.rcvUsuarios);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("Jonatas Rodrigues Reis","jonatas@gmail.com","123123"));
        usuarios.add(new Usuario("José de Maria Cardoso","jose@gmail.com","123123"));
        usuarios.add(new Usuario("Maria Reis da Silva","maria@gmail.com","123123"));
        mAdapter = new UsuariosAdapter(usuarios,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setTitle("Lista de Usuários");
    }

    public void addUsuario(View view){
      mAdapter.updateList(new Usuario("Usuário Teste"+Integer.toString(usuarios.size() + 1),"usuario@gmail.com","123123"));
    }
}
