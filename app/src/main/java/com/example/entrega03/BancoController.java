package com.example.entrega03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BancoController {

    private SQLiteDatabase db;
    private CreateDataBase banco;

    public BancoController(Context context){
        banco = new CreateDataBase(context);
    }

    public Cursor vendasCliente(int cliente){
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM vendas WHERE cliente=" + Integer.toString(cliente),null);
        return cursor;
    }

    public String inserirVenda(float total, int cliente){
        ContentValues valores;
        long resultado;
        String dateNow = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CreateDataBase.TotalVenda, total);
        valores.put(CreateDataBase.ClienteId, cliente);
        valores.put(CreateDataBase.DataVenda,dateNow);

        resultado = db.insert(banco.TABELA_VENDAS, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir venda";

        return "Venda inserido com Sucesso";
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

    public String inserirUsuario(String nome, String email, String senha, boolean admin){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);
        valores.put("admin",admin ? 1 : 0);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";

        return "Usuário inserido com Sucesso";

    }

    public Cursor carregaUsuarios(){
        Cursor cursor;
        /*String[] campos =  {banco.ID,banco.Nome,banco.Email,banco.Senha, banco.Admin};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_USERS, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }*/

        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM usuarios WHERE admin=1",null);
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
