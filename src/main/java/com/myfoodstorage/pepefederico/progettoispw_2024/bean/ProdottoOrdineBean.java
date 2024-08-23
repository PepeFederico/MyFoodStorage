package com.myfoodstorage.pepefederico.progettoispw_2024.bean;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;

public class ProdottoOrdineBean {
    private String nomeProdotto;
    private TipoAnimale tipoAnimale;
    private int qtaRichiesta;

    public ProdottoOrdineBean(String nomeProdotto, TipoAnimale tipoAnimale, int qtaRichiesta) {
        this.nomeProdotto = nomeProdotto;
        this.tipoAnimale = tipoAnimale;
        this.qtaRichiesta = qtaRichiesta;
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
    public int getQtaRichiesta() {
        return qtaRichiesta;
    }
    public void setQtaRichiesta(int qtaRichiesta) {
        this.qtaRichiesta = qtaRichiesta;
    }
}
