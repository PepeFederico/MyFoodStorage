package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.stati;

import com.myfoodstorage.pepefederico.progettoispw_2024.dao.OrdineDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.RecuperoInfoFornitoriDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Stato;

import java.util.ArrayList;

public class CheckingOut implements Stato {
    private final Ordine ordine;

    public CheckingOut(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public ArrayList<ProdottoFornito> elaboraOrdine(String nomeAttivita) {
        RecuperoInfoFornitoriDao infoFornitori = new RecuperoInfoFornitoriDao();
        infoFornitori.recuperoFornitori(nomeAttivita);
        ordine.setNomeFornitore(infoFornitori.getNomeFornitore());
        return infoFornitori.getProdotti();
    }

    @Override
    public void creaOrdine(ArrayList<Prodotto> prodotti) {
        /*
            Metodo è vuoto poichè, non è responsabilità di questo stato
            per la classe in questione
         */
    }

    @Override
    public void inoltraOrdine() {
        OrdineDao ordineDAO = new OrdineDao();
        ordineDAO.salvaOrdine(ordine, "Attesa", ordine.getNomeFornitore());
        ordine.setStati(ordine.getAttesa());
    }

}
