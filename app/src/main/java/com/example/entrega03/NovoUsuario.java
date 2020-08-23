package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovoUsuario extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);

        getSupportActionBar().setTitle("Novo Usuário");
    }

    public void novoUsuario(View view){
        Intent intent = new Intent();
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if(!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()){
            intent.putExtra("nome", nome);
            intent.putExtra("email", email);
            intent.putExtra("senha", senha);
            setResult(RESULT_OK, intent);
            finish();
        }


    }

    public void voltar(View view){
        finish();
    }
}