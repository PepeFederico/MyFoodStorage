package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.Date;

public class Prodotto {
    private String nomeProdotto;
    private String numeroLotto;
    private Date scadenza;
    private int taglia;
    private int scorte;
    private double costo;
    private TipoAnimale tipoAnimale;

    public Prodotto(String nomeProdotto,
                    String numeroLotto,
                    Date scadenza,
                    int taglia,
                    int scorte,
                    double costo,
                    TipoAnimale tipoAnimale) {
        this.nomeProdotto = nomeProdotto;
        this.numeroLotto = numeroLotto;
        this.scadenza = scadenza;
        this.taglia = taglia;
        this.scorte = scorte;
        this.costo = costo;
        this.tipoAnimale = tipoAnimale;
    }

    public Prodotto(String nomeProdotto, int scorte, TipoAnimale tipoAnimale) {
        this.nomeProdotto = nomeProdotto;
        this.scorte = scorte;
        this.tipoAnimale = tipoAnimale;
    }

    public Prodotto(String nomeProdotto, TipoAnimale tipoAnimale) {
        this.nomeProdotto = nomeProdotto;
        this.tipoAnimale = tipoAnimale;
    }

    public Prodotto(){}

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getNumeroLotto() {
        return numeroLotto;
    }

    public void setNumeroLotto(String numeroLotto) {
        this.numeroLotto = numeroLotto;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public int getTaglia() {
        return taglia;
    }

    public void setTaglia(int taglia) {
        this.taglia = taglia;
    }

    public int getScorte() {
        return scorte;
    }

    public void setScorte(int scorte) {
        this.scorte = scorte;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public TipoAnimale getTipoAnimale() {
        return tipoAnimale;
    }

    public void setTipoAnimale(TipoAnimale tipoAnimale) {
        this.tipoAnimale = tipoAnimale;
    }
}
