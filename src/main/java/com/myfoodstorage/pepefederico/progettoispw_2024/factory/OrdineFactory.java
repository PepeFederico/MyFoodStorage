package com.myfoodstorage.pepefederico.progettoispw_2024.factory;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;

public class OrdineFactory {
    private static OrdineFactory ordineFactory = null;

    private OrdineFactory(){}

    public static synchronized OrdineFactory getInstance(){
        if(ordineFactory == null){
            ordineFactory = new OrdineFactory();
        }
        return ordineFactory;
    }

    public Ordine getOrdine(){
        return new Ordine();
    }
}
