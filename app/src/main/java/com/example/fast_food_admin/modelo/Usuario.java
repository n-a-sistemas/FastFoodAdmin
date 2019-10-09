package com.example.fast_food_admin.modelo;

import java.util.List;

public class Usuario {
    private String uid;
    private String nome;
    private String email;
    private boolean valido;
    private Integer vida;
    private Integer pontos;
    private boolean admin;
    private List<Cupom> cupons;

    public Usuario(){

    }

    public Usuario(String uid, String nome, String email, boolean valido, Integer vida, Integer pontos, boolean admin, List<Cupom> cupons) {
        this.uid = uid;
        this.nome = nome;
        this.email = email;
        this.valido = valido;
        this.vida = vida;
        this.pontos = pontos;
        this.admin = admin;
        this.cupons = cupons;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", valido=" + valido +
                ", vida=" + vida +
                ", pontos=" + pontos +
                ", admin=" + admin +
                ", cupons=" + cupons +
                '}';
    }
}
