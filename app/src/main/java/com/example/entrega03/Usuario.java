package com.example.entrega03;

public class Usuario {

    public String id;
    public String nome;
    public String email;
    public String senha;
    public boolean admin;

    public Usuario(String id,String nome,String email, String senha, boolean admin){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
    }

    public Usuario(String nome,String email, String senha, boolean admin){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
    }
}
