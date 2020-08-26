package com.example.entrega03;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NovoProduto extends AppCompatActivity {

    EditText edtNome;
    EditText edtPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        edtNome = findViewById(R.id.edtNome);
        edtPreco = findViewById(R.id.edtPreco);

        setTitle("Novo Produto");
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