package com.example.entrega03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private SQLiteDatabase db;
    private CreateDataBase banco;

    public BancoController(Context context){
        banco = new CreateDataBase(context);
    }

    public String inserirUsuario(String nome, String email, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";

         return "Usuário inserido com Sucesso";

    }

    public Cursor carregaUsuarios(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.Nome,banco.Email,banco.Senha};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_USERS, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void deletaUsuario(String nome){
        String where = CreateDataBase.Nome + "='" + nome+"'";
        db = banco.getReadableDatabase();
        db.delete(CreateDataBase.TABELA_USERS,where,null);
        //db.close();
    }


    public Cursor login(String email,String senha){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? and senha=?", new String[] {email,senha});
        return  cursor;
    }

}
