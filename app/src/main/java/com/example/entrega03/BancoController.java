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

    public Cursor carregaProdutos(){
        Cursor cursor;
        String[] campos =  {banco.IDProduto,banco.NomeProduto,banco.PrecoProduto};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_PRODUTOS, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void deletaProduto(String nome){
        String where = CreateDataBase.NomeProduto + "='" + nome+"'";
        db = banco.getReadableDatabase();
        db.delete(CreateDataBase.TABELA_PRODUTOS,where,null);
        //db.close();
    }

    public String inserirProduto(String nome, String preco){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("preco", preco);

        resultado = db.insert(banco.TABELA_PRODUTOS, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir produto";

        return "Produto inserido com Sucesso";
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
