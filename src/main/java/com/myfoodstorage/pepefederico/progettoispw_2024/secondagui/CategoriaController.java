package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.CategoriaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.GestioneProdottiA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.CategoryNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaController {

    private final Logger logger = Logger.getLogger(CategoriaController.class.getName());

    public void recuperoInfoCategorie(){
        try {
            GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(ClientController.getInstance().getSessioneUtente());
            if(gestioneProdottiA.checkSessione()) {
                gestioneProdottiA.recuperoInfoCategoria();
                List<CategoriaBean> categoria = gestioneProdottiA.getCatBean();

                System.out.println("    Categorie:");
                for (CategoriaBean categoriaBean : categoria) {
                    System.out.println("        " + categoriaBean.getNomeCategoria());

                    ClientController.getInstance().setCategoriaBean(categoriaBean);
                    ProdottiController prodotti = new ProdottiController();
                    prodotti.recuperoInfoProdotti();
                }
            }
        } catch (CategoryNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}

