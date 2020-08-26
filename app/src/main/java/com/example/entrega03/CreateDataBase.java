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
    public static final String Admin = "admin";

    public static final String TABELA_PRODUTOS = "produtos";
    public static final String IDProduto = "_id";
    public static final String NomeProduto = "nome";
    public static final String PrecoProduto = "preco";

    public static final String TABELA_VENDAS = "vendas";
    public static final String IDVenda = "_id";
    public static final String TotalVenda = "total";
    public static final String DataVenda = "data";
    public static final String ClienteId = "cliente";

    public static final int VERSAO = 3;

    public CreateDataBase(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA_USERS+" ("
                + ID + " integer primary key autoincrement,"
                + Nome + " text,"
                + Email + " text,"
                + Senha + " text,"
                + Admin + " integer"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+TABELA_PRODUTOS+" ("
                + IDProduto + " integer primary key autoincrement,"
                + NomeProduto + " text,"
                + PrecoProduto + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+TABELA_VENDAS+" ("
                + IDVenda + " integer primary key autoincrement,"
                + TotalVenda + " float,"
                + DataVenda + " text,"
                + ClienteId + " integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
