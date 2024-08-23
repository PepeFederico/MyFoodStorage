package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.stati;

import com.myfoodstorage.pepefederico.progettoispw_2024.dao.OrdineDAO;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.RecuperoInfoFornitoriDAO;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Stato;

import java.util.ArrayList;

public class CheckingOut implements Stato {
    private Ordine ordine;

    public CheckingOut(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public ArrayList<ProdottoFornito> elaboraOrdine(String nomeAttivita) {
        RecuperoInfoFornitoriDAO infoFornitori = new RecuperoInfoFornitoriDAO();
        infoFornitori.recuperoFornitori(nomeAttivita);
        ordine.setNomeFornitore(infoFornitori.getNomeFornitore());
        return infoFornitori.getProdotti();
    }

    @Override
    public void creaOrdine(ArrayList<Prodotto> prodotti) {

    }

    @Override
    public void inoltraOrdine() {
        OrdineDAO ordineDAO = new OrdineDAO();
        ordineDAO.salvaOrdine(ordine, "Attesa", ordine.getNomeFornitore());
        ordine.setStati(ordine.getAttesa());
    }

    @Override
    public void ordineAccettato() {

    }

    @Override
    public void ordineRifiutato() {

    }
}
