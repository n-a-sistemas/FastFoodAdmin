package com.example.fast_food_admin.modelo;

public class Cupom {

    private String nome;
    private String preco;
    private String uuid;

    public Cupom(){

    }

    public Cupom(String nome, String preco, String uuid) {
        this.nome = nome;
        this.preco = preco;
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
