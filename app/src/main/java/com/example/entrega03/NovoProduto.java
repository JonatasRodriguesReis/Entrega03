package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovoProduto extends AppCompatActivity {

    EditText edtNome;
    EditText edtPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        edtNome = findViewById(R.id.edtNome);
        edtPreco = findViewById(R.id.edtPreco);

        getSupportActionBar().setTitle("Novo Produto");
    }

    public void addProduto(View view){
        Intent intent = new Intent();
        String nome = edtNome.getText().toString();
        String preco = edtPreco.getText().toString();

        if(!nome.isEmpty() && !preco.isEmpty()){
            intent.putExtra("nome", nome);
            intent.putExtra("preco", preco);
            setResult(RESULT_OK, intent);
            finish();
        }


    }

    public void voltar(View view){
        finish();
    }
}