package com.example.entrega03;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MeuCarrinho extends AppCompatActivity {

    ArrayList<Produto> meuCarrinho;
    int clienteId;
    RecyclerView recyclerView;
    private MeuCarrinhoAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Produto> produtos;

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_carrinho);
        setTitle("Meu Carrinho");

        this.meuCarrinho = (ArrayList<Produto>) getIntent().getSerializableExtra("listaCarrinho");
        this.clienteId = getIntent().getIntExtra("user",0);

        //Toast.makeText(getApplicationContext(), "Lista " + Integer.toString(this.meuCarrinho.size()), Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.rcvProdutos);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaProdutos();

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MeuCarrinhoAdapter(meuCarrinho,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public void finalizarCompra(View view){
        float sumValor = 0;
        final float total;
        for (int i = 0; i < this.meuCarrinho.size(); i++){
            sumValor += Float.parseFloat(meuCarrinho.get(i).preco);
        }
        total = sumValor;

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Você deseja finalizar a compra?")
                .setMessage("O valor total é R$ " + Float.toString(total))
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        BancoController db = new BancoController(getBaseContext());
                        String resultado = db.inserirVenda(total,clienteId);
                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
                        finish();

                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
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
