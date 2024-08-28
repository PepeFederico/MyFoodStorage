package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FailSendMail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizzaCarrello {
    private boolean inoltroOrdine = false;
    private OrdineBean ordineBean;
    private final Logger logger = Logger.getLogger(VisualizzaCarrello.class.getName());

    public void visualizzaCarrelloOrdine(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean nuovoInput = false;
        boolean errore = false;

        ordineBean = ClientController.getInstance().getOrdineBean();
        List<ProdottoOrdineBean> prodotti = ordineBean.getProdotti();

        do {
            try {

                System.out.println("    Carrello    ");
                for (ProdottoOrdineBean prodottoOrdineBean : prodotti) {
                    System.out.println("    " + prodottoOrdineBean.getNomeProdotto() + " " + prodottoOrdineBean.getTipoAnimale() + " " + prodottoOrdineBean.getQtaRichiesta() + "Kg");
                }

                System.out.println(" ");

                System.out.println("1)  Modifica Quantità di un Prodotto");
                System.out.println("2)  Elimina Prodotto");
                System.out.println("3)  Procedi All'Ordine");
                System.out.println("4)  Indietro");
                int scelta = Integer.parseInt(reader.readLine());

                switch (scelta) {
                    case 1:
                        modificaQuantitaProdotto();

                        System.out.println("    Quantità modificata con successo !");
                        nuovoInput = true;
                        break;

                    case 2:
                        rimuoviProdotto();

                        System.out.println("    Rimosso prodotto dal carrello !");
                        nuovoInput = true;
                        break;

                    case 3:
                        procediOrdine();

                        nuovoInput = false;
                        break;

                    case 4:
                        nuovoInput = false;
                        break;

                    default:
                        logger.log(Level.WARNING, "Ops. Hai digitato un'opzione non valida, riprova ");
                        errore = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while(nuovoInput | errore);
    }

    private void modificaQuantitaProdotto(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nomeProdotto;
        int nuovaQta;

        System.out.println("    Modifica Quantità Prodotto  ");
        try{
            System.out.println("    Quale prodotto vuoi modificare ? Compila il Form");

            System.out.println("    Nome Prodotto :");
            nomeProdotto = reader.readLine();
            System.out.println("    nuova Quantità :");
            nuovaQta = Integer.parseInt(reader.readLine());

            for(int i = 0; i < ordineBean.getProdotti().size(); i++){
                if(nomeProdotto.equals(ordineBean.getProdotti().get(i).getNomeProdotto())){
                    ordineBean.getProdotti().get(i).setQtaRichiesta(nuovaQta);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void rimuoviProdotto(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nomeProdotto;

        System.out.println("    Rimuovi Prodotto  ");
        try{
            System.out.println("    Quale prodotto vuoi rimuovere ? Compila il Form");

            System.out.println("    Nome Prodotto :");
            nomeProdotto = reader.readLine();

            ordineBean.getProdotti().removeIf(prodottoOrdineBean -> nomeProdotto.equals(prodottoOrdineBean.getNomeProdotto()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void procediOrdine(){
        try {
            if (!ordineBean.getProdotti().isEmpty()) {
                OrdineProdottiControllerA ordineProdotti = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
                if (ordineProdotti.checkSessione()) {
                    ordineProdotti.procediOrdine(ordineBean);
                    System.out.println("    Ordine inoltrato con successo !!");
                    setInoltroOrdine(true);
                }
            } else {
                logger.log(Level.WARNING, "L'ordine è vuoto !! Inserisci almeno un prodotto");
            }
        }catch (FailSendMail e){
            logger.log(Level.WARNING, e.getMessage());
            setInoltroOrdine(false);
        }
    }

    public boolean isInoltroOrdine() {
        return inoltroOrdine;
    }

    private void setInoltroOrdine(boolean inoltroOrdine) {
        this.inoltroOrdine = inoltroOrdine;
    }
}
