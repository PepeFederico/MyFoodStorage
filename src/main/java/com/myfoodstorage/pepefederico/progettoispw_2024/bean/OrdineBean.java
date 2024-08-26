package com.myfoodstorage.pepefederico.progettoispw_2024.bean;

import java.util.ArrayList;
import java.util.List;

public class OrdineBean {
    private final List<ProdottoOrdineBean> prodotti;
    private String contattoFornitore;
    private String nomeFornitore;
    private String statoOrdine;
    private String dataConsegna;

    public OrdineBean() {
        this.prodotti = new ArrayList<>();
    }

    public List<ProdottoOrdineBean> getProdotti() {
        return prodotti;
    }
    public void setProdotti(ProdottoOrdineBean prodotti) {
        this.prodotti.add(prodotti);
    }
    public String getStatoOrdine() {
        return statoOrdine;
    }
    public void setStatoOrdine(String statoOrdine) {
        this.statoOrdine = statoOrdine;
    }
    public String getContattoFornitore() {
        return contattoFornitore;
    }
    public void setContattoFornitore(String contattoFornitore) {
        this.contattoFornitore = contattoFornitore;
    }
    public String getNomeFornitore() {
        return nomeFornitore;
    }
    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }
    public String getDataConsegna() {
        return dataConsegna;
    }
    public void setDataConsegna(String dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

}
