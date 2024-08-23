package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.stati;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Stato;

import java.util.ArrayList;

public class Rifiutato implements Stato {

    private Ordine ordine;
    private String stato = "Rifiutato";

    public Rifiutato(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public ArrayList<ProdottoFornito> elaboraOrdine(String nomeAttivita) {
        return null;
    }

    @Override
    public void creaOrdine(ArrayList<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            ordine.getProdottiSelezionati().add(prodotto);
        }
        ordine.setDataCorrente();
        ordine.setStatoCorrente(this.stato);
    }

    @Override
    public void inoltraOrdine() {

    }

    @Override
    public void ordineAccettato() {

    }

    @Override
    public void ordineRifiutato() {

    }
}
