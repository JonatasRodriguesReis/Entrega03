package com.example.entrega03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDataBase extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA_USERS = "usuarios";
    public static final String ID = "_id";
    public static final String Nome = "nome";
    public static final String Email = "email";
    public static final String Senha = "senha";

    public static final String TABELA_PRODUTOS = "produtos";

    public static final String IDProduto = "_id";
    public static final String NomeProduto = "nome";
    public static final String PrecoProduto = "preco";
    public static final int VERSAO = 2;

    public CreateDataBase(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA_USERS+" ("
                + ID + " integer primary key autoincrement,"
                + Nome + " text,"
                + Email + " text,"
                + Senha + " text"
                +")";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE "+TABELA_PRODUTOS+" ("
                + IDProduto + " integer primary key autoincrement,"
                + NomeProduto + " text,"
                + PrecoProduto + " text"
                +")";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
