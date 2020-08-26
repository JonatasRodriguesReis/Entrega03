package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nome = findViewById(R.id.edtNome);
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);

        getSupportActionBar().setTitle("Signup");
    }

    public void login(View view){
        finish();
    }

    public void signup(View view){
        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        if(!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()){
            BancoController db = new BancoController(getBaseContext());
            Usuario user = new Usuario(nome, email, senha,false);
            String resultado = db.inserirUsuario(user.nome, user.email, user.senha,user.admin);
            login();
        }
    }

    private void login(){
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();
        BancoController db = new BancoController(getBaseContext());
            Cursor cursor = db.login(email,senha);

            if(cursor != null)
                cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                Intent intent;
                int user = cursor.getInt(cursor.getColumnIndex("_id"));

                intent = new Intent(this,ProdutosLoja.class);
                intent.putExtra("user",user);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Bem-vindo", Toast.LENGTH_LONG).show();
            }

    }
}
