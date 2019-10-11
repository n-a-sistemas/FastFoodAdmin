package com.example.fast_food_admin.modelo;

public class Cupom {

    private String nome;
    private String preco;
    private String uuid;
    private String token = "falso";

    public Cupom(){

    }

    public Cupom(String nome, String preco, String uuid, String token) {
        this.nome = nome;
        this.preco = preco;
        this.uuid = uuid;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", uuid='" + uuid + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
