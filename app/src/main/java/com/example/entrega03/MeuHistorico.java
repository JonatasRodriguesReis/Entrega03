package com.example.entrega03;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MeuHistorico extends AppCompatActivity {

    RecyclerView recyclerView;
    private HistoricoAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Venda> vendas;
    int clienteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_historico);
        setTitle("Meu Histórico");
        clienteId = getIntent().getIntExtra("user",0);

        recyclerView = (RecyclerView) findViewById(R.id.rcvHistorico);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.vendasCliente(clienteId);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        /*produtos = new ArrayList<Produto>();
        produtos.add(new Produto("Pneu","120"));
        produtos.add(new Produto("Arroz","130"));
        produtos.add(new Produto("Feijão","150"));*/

        mAdapter = new HistoricoAdapter(loadVendasFromCursor(cursor),this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private ArrayList<Venda> loadVendasFromCursor(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Venda> lista = new ArrayList<Venda>();
        if (cursor != null && cursor.getCount() > 0) {
            do {
                lista.add(new Venda(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getFloat(cursor.getColumnIndex("total")),
                        cursor.getString(cursor.getColumnIndex("data")),cursor.getInt(cursor.getColumnIndex("cliente"))));
            }while (cursor.moveToNext());
        }

        return lista;
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
