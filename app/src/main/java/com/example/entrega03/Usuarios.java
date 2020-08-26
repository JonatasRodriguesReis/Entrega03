package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuariosAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Usuario> usuarios;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        recyclerView = (RecyclerView) findViewById(R.id.rcvUsuarios);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaUsuarios();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        /*usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("Jonatas Rodrigues Reis","jonatas@gmail.com","123123"));
        usuarios.add(new Usuario("José de Maria Cardoso","jose@gmail.com","123123"));
        usuarios.add(new Usuario("Maria Reis da Silva","maria@gmail.com","123123"));*/

        mAdapter = new UsuariosAdapter(loadUsuariosFromCursor(cursor),this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setTitle("Lista de Usuários");
    }

    private ArrayList<Usuario> loadUsuariosFromCursor(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        if (cursor != null && cursor.getCount() > 0) {
            do {
                boolean admin = cursor.getInt(cursor.getColumnIndex("admin")) == 1 ? true : false;
                lista.add(new Usuario(cursor.getString(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("nome")),
                        cursor.getString(cursor.getColumnIndex("email")),cursor.getString(cursor.getColumnIndex("senha")),admin));
            }while (cursor.moveToNext());
        }

        return lista;
    }

    public void addUsuario(View view) {

        Intent intent = new Intent(this, NovoUsuario.class);
        startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnNome = data.getStringExtra("nome");
                String returnEmail = data.getStringExtra("email");
                String returnSenha = data.getStringExtra("senha");

                BancoController db = new BancoController(getBaseContext());
                Usuario user = new Usuario(returnNome, returnEmail, returnSenha, true);
                String resultado = db.inserirUsuario(user.nome, user.email, user.senha, user.admin);
                mAdapter.updateList(user);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        }
    }

}
