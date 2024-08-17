package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.ArrayList;

public class Categoria {
    private String nomeCategoria;
    private ArrayList<Prodotto> prodotti;

    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.prodotti = null;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
