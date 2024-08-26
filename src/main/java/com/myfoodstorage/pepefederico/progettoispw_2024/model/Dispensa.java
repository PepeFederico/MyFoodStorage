package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.List;

public class Dispensa {
    private String nomeDispensa;
    private int numeroCategorie;
    private List<Categoria> categorie;

    public Dispensa(String nomeDispensa, int numeroCategorie) {
        this.nomeDispensa = nomeDispensa;
        this.numeroCategorie = numeroCategorie;
        this.categorie = null;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }
    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }
    public String getNomeDispensa() {
        return nomeDispensa;
    }
    public void setNomeDispensa(String nomeDispensa) {
        this.nomeDispensa = nomeDispensa;
    }
    public int getNumeroCategorie() {
        return numeroCategorie;
    }
    public void setNumeroCategorie(int numeroCategorie) {
        this.numeroCategorie = numeroCategorie;
    }
}
