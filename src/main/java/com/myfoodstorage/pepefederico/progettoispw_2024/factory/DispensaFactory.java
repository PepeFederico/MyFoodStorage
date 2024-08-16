package com.myfoodstorage.pepefederico.progettoispw_2024.factory;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Dispensa;

public class DispensaFactory {
    private static DispensaFactory dispensaFactory = null;

    private DispensaFactory() {
    }

    public static synchronized DispensaFactory getInstance() {
        if (dispensaFactory == null) {
            dispensaFactory = new DispensaFactory();
        }
        return dispensaFactory;
    }

    public Dispensa getDispensa(String nomeDispensa, int numeroCategorie) {
        return new Dispensa(nomeDispensa, numeroCategorie);
    }
}
