package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import java.util.List;

public interface Stato {
    List<ProdottoFornito> elaboraOrdine(String nomeAttivita);
    void creaOrdine(List<Prodotto> prodotti);
    void inoltraOrdine();

}
