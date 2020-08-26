package com.example.entrega03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BancoController db;
    EditText email;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new BancoController(getBaseContext());
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
    }

    public void login(View view){
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        if(!email.isEmpty()){
            Cursor cursor = db.login(email,senha);

            if(cursor != null)
                cursor.moveToFirst();


            if (cursor.getCount() > 0 || (email.equals("admin"))) {
                Intent intent;
                boolean isAdmin = true;
                int user = 0;
                if(cursor.getCount() > 0){
                    isAdmin = cursor.getInt(cursor.getColumnIndex("admin")) == 1 ? true : false;
                    cursor.getInt(cursor.getColumnIndex("_id"));
                }

                if(isAdmin)
                    intent = new Intent(this, ListaProdutos.class);
                else
                    intent = new Intent(this,ProdutosLoja.class);
                intent.putExtra("user",user);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Bem-vindo", Toast.LENGTH_LONG).show();
                this.email.setText("");
                this.senha.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Email ou senha incorretos!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void signup(View view){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
