package com.myfoodstorage.pepefederico.progettoispw_2024.model;

public class ProdottoFornito {
    private String nomeFornitore;
    private String contattoFornitore;
    private String nomeProdotto;
    private TipoAnimale tipoAnimale;
    private int taglia;
    private double costo;

    public ProdottoFornito(String nomeFornitore,
                           String contattoFornitore,
                           String nomeProdotto,
                           TipoAnimale tipoAnimale,
                           int taglia,
                           double costo) {
        this.nomeFornitore = nomeFornitore;
        this.contattoFornitore = contattoFornitore;
        this.nomeProdotto = nomeProdotto;
        this.tipoAnimale = tipoAnimale;
        this.taglia = taglia;
        this.costo = costo;
    }

    public String getNomeFornitore() {
        return nomeFornitore;
    }

    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }

    public String getContattoFornitore() {
        return contattoFornitore;
    }

    public void setContattoFornitore(String contattoFornitore) {
        this.contattoFornitore = contattoFornitore;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public TipoAnimale getTipoAnimale() {
        return tipoAnimale;
    }

    public void setTipoAnimale(TipoAnimale tipoAnimale) {
        this.tipoAnimale = tipoAnimale;
    }

    public int getTaglia() {
        return taglia;
    }

    public void setTaglia(int taglia) {
        this.taglia = taglia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
