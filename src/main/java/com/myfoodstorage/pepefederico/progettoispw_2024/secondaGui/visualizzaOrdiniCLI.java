package com.myfoodstorage.pepefederico.progettoispw_2024.secondaGui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ZeroOrderException;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class visualizzaOrdiniCLI {

    private List<OrdineBean> ordineBean;
    private final Logger logger = Logger.getLogger(visualizzaOrdiniCLI.class.getName());

    public void visualizzaStatoOrdini(){

        try {
            OrdineProdottiControllerA ordineProdottiControllerA = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
            if (ordineProdottiControllerA.checkSessione()) {
                ordineProdottiControllerA.recuperaOrdini();
                ordineBean = ordineProdottiControllerA.getOrdiniEffetuati();
                stampaOrdini();
            }
        }catch (ZeroOrderException | RejectedExecutionException e){
            logger.log(Level.WARNING, e.getMessage());
        }

    }

    private void stampaOrdini(){
        System.out.println("------Stato Ordini------");

        for(OrdineBean ordBean : ordineBean){
            System.out.println("    Stato Ordine : " + ordBean.getStatoOrdine());
            System.out.println("    " + ordBean.getNomeFornitore());

            if (ordBean.getStatoOrdine().equals("Accettato"))
                System.out.println("    Data Consegna Prevista : " + ordBean.getDataConsegna());

            visualizzaProdottiOrdinati(ordBean);
        }
    }

    private void visualizzaProdottiOrdinati(OrdineBean ordBean){
        for (ProdottoOrdineBean prodottoOrdineBean : ordBean.getProdotti()){
            System.out.println("        Prodotto : " + prodottoOrdineBean.getNomeProdotto()
                + " " + prodottoOrdineBean.getTipoAnimale() + " " + prodottoOrdineBean.getQtaRichiesta() + "Kg");
        }
        System.out.println(" ");
    }

}
