package com.example.entrega03;

public class Venda {
    public int id;
    public float total;
    public String data;
    public int clienteId;

    public Venda(int id, float total, String data, int clienteId){
        this.id = id;
        this.total = total;
        this.data = data;
        this.clienteId = clienteId;
    }
}
