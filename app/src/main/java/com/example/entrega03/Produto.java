package com.example.entrega03;

public class Produto {
    public String id;
    public String nome;
    public String preco;

    public Produto(String nome, String preco){
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String id, String nome, String preco){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}
