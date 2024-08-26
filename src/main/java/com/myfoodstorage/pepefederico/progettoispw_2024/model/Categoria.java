package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.List;

public class Categoria {
    private String nomeCategoria;
    private List<Prodotto> prodotti;

    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.prodotti = null;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
