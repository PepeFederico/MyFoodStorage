package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.GestioneProdottiA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FoodStorageNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DispensaController {

    private final Logger logger = Logger.getLogger(DispensaController.class.getName());

    public void recuperoInfo(){

        try {
            System.out.println("------Gestione Dispensa --> Queste sono le tue dispense------");
            GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(ClientController.getInstance().getSessioneUtente());
            if(gestioneProdottiA.checkSessione()) {
                gestioneProdottiA.recuperoInfoDispensa();
                List<DispensaBean> dispensa = gestioneProdottiA.getDispBean();

                System.out.println("---Queste sono le tue dispense---");
                for (DispensaBean dispensaBean : dispensa) {
                    System.out.println(dispensaBean.getNomeDispensa());

                    ClientController.getInstance().setDispensaBean(dispensaBean);

                    CategoriaController categoriaController = new CategoriaController();
                    categoriaController.recuperoInfoCategorie();
                }
            }
        } catch (FoodStorageNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

}
