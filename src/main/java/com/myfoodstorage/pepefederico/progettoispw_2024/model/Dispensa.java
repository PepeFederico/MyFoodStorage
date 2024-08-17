package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.ArrayList;

public class Dispensa {
    private String nomeDispensa;
    private int numeroCategorie;
    private ArrayList<Categoria> categorie;

    public Dispensa(String nomeDispensa, int numeroCategorie) {
        this.nomeDispensa = nomeDispensa;
        this.numeroCategorie = numeroCategorie;
        this.categorie = null;
    }

    public ArrayList<Categoria> getCategorie() {
        return categorie;
    }
    public void setCategorie(ArrayList<Categoria> categorie) {
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
