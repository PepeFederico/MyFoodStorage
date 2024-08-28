package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdineRicercaController {
    private final Logger logger = Logger.getLogger(OrdineRicercaController.class.getName());

    public void startOrdine(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean errore = false;
        boolean nuovoInput = false;

        do{
            try {
                setGraficaMenu();
                int scelta = Integer.parseInt(reader.readLine());
                switch (scelta){
                    case 1:
                        OrdineProdottiController prodottiController = new OrdineProdottiController();
                        prodottiController.recuperoProdotti();

                        nuovoInput = true;
                        break;

                    case 2:
                        VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini();
                        visualizzaOrdini.visualizzaStatoOrdini();

                        nuovoInput = true;
                        break;

                    case 3:
                        nuovoInput = false;
                        break;
                    default:
                        logger.log(Level.WARNING, "Ops. Hai digitato un'opzione non valida, riprova ");
                        errore = true;
                }
            } catch (IOException e) {
                throw new RejectedExecutionException(e);
            }
        }while(nuovoInput || errore);
    }

    private void setGraficaMenu(){
        System.out.println("------Ordine Prodotti------");
        System.out.println("------Quale operazione vuoi eseguire ?------");
        System.out.println(" 1)     Proponi nuovo ordine");
        System.out.println(" 2)     Visualizza Ordini effettuati");
        System.out.println(" 3)     Indietro");
    }

}

