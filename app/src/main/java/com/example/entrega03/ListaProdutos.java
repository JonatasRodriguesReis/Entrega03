package com.example.entrega03;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaProdutos extends AppCompatActivity {

    RecyclerView recyclerView;
    private ProdutosAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Produto> produtos;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        setTitle("Lista de Produtos");

        recyclerView = (RecyclerView) findViewById(R.id.rcvProdutos);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaProdutos();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        produtos = new ArrayList<Produto>();
        produtos.add(new Produto("Pneu","120"));
        produtos.add(new Produto("Arroz","130"));
        produtos.add(new Produto("Feijão","150"));

        mAdapter = new ProdutosAdapter(loadProdutosFromCursor(cursor),this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private ArrayList<Produto> loadProdutosFromCursor(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Produto> lista = new ArrayList<Produto>();
        if (cursor != null && cursor.getCount() > 0) {
            do {
                lista.add(new Produto(cursor.getString(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("nome")),
                        cursor.getString(cursor.getColumnIndex("preco"))));
            }while (cursor.moveToNext());
        }

        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.usuarios) {
            Intent intent = new Intent(this, Usuarios.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.sair) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void novoProduto(View view) {

        Intent intent = new Intent(this, NovoProduto.class);
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
                String returnPreco = data.getStringExtra("preco");

                BancoController db = new BancoController(getBaseContext());
                Produto produto = new Produto(returnNome, returnPreco);
                String resultado = db.inserirProduto(produto.nome, produto.preco);
                mAdapter.updateList(produto);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        }
    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }
}
