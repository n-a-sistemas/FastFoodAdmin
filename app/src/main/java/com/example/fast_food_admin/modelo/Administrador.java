package com.example.fast_food_admin.modelo;

public class Administrador {
    private String uid;
    private String nome;
    private String email;
    private boolean valido;
    private Integer vida;
    private Integer pontos;

    public Administrador(){

    }

    public Administrador(String uid, String nome, String email, boolean valido, Integer vida, Integer pontos) {
        this.uid = uid;
        this.nome = nome;
        this.email = email;
        this.valido = valido;
        this.vida = vida;
        this.pontos = pontos;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}
