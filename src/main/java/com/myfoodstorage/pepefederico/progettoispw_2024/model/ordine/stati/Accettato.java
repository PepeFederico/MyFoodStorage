package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.stati;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Stato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accettato implements Stato {

    private final Ordine ordine;

    public Accettato(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public List<ProdottoFornito> elaboraOrdine(String nomeAttivita) {
        return Collections.emptyList();
    }

    @Override
    public void creaOrdine(ArrayList<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            ordine.getProdottiSelezionati().add(prodotto);
        }
        ordine.setDataCorrente();
        ordine.setDataConsegna();
        ordine.setStatoCorrente("Accettato");
    }

    @Override
    public void inoltraOrdine() {
        /*
            Metodo è vuoto poichè non ha senso per lo stato in questione
         */
    }
}
