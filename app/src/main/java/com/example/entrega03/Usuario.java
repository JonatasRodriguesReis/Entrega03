package com.example.entrega03;

public class Usuario {

    public String id;
    public String nome;
    public String email;
    public String senha;

    public Usuario(String id,String nome,String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome,String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
