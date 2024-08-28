package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.GestioneProdottiA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdottiController {

    private final Logger logger = Logger.getLogger(ProdottiController.class.getName());

    public void recuperoInfoProdotti(){
        try {
            GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(ClientController.getInstance().getSessioneUtente());
            if(gestioneProdottiA.checkSessione()) {
                gestioneProdottiA.recuperoInfoProdotti();
                List<ProdottoBean> prodotto = gestioneProdottiA.getProdBean();

                System.out.println("            Prodotti:");
                for (ProdottoBean prodottoBean : prodotto) {
                    System.out.println("                " + "Nome Prodotto              :" + prodottoBean.getNomeProdotto());
                    System.out.println("                " + "Tipo Animale               :" + prodottoBean.getTipoAnimale());
                    System.out.println("                " + "Data di Scadenza           :" + prodottoBean.getScadenza());
                    System.out.println("                " + "Numero Lotto               :" + prodottoBean.getNumeroLotto());
                    System.out.println("                " + "Taglia scorte prodotto     :" + prodottoBean.getTaglia() + "g");
                    System.out.println("                " + "Scorte Disponibili         :" + prodottoBean.getScorte());
                    System.out.println(" ");
                }
            }
        } catch (ProductNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

}
