package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import java.util.ArrayList;

public interface Stato {
    ArrayList<ProdottoFornito> elaboraOrdine(String nomeAttivita);
    void creaOrdine(ArrayList<Prodotto> prodotti);
    void inoltraOrdine();

}
