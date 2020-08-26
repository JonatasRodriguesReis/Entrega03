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

import java.util.ArrayList;

public class ProdutosLoja extends AppCompatActivity {

    RecyclerView recyclerView;
    private ProdutosLojaAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Produto> produtos;
    int user;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_loja);
        setTitle("Produtos da Loja");
        user = getIntent().getIntExtra("user",0);

        recyclerView = (RecyclerView) findViewById(R.id.rcvProdutos);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        this.inicializeAdapter();

        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void inicializeAdapter(){
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaProdutos();
        mAdapter = new ProdutosLojaAdapter(loadProdutosFromCursor(cursor),this);
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

    public void abrirCarrinho(View view){
        if(mAdapter.listaCarrinho.size() > 0){
            Intent intent = new Intent(this, MeuCarrinho.class);
            intent.putExtra("listaCarrinho",mAdapter.listaCarrinho);
            intent.putExtra("user",this.user);
            startActivity(intent);
            this.inicializeAdapter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.historico) {
            Intent intent = new Intent(this, MeuHistorico.class);
            intent.putExtra("user",user);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.sair) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTitle(String title){
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
