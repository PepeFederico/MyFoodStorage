package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.CategoriaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.SearchException;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdineProdottiController {
    private DispensaBean dispensaBean;
    private CategoriaBean categoriaBean;
    private final Logger logger = Logger.getLogger(OrdineProdottiController.class.getName());
    private boolean nuovoInput = false;

    public void recuperoProdotti(){
        OrdineProdottiControllerA ordineProdottiControllerA = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
        if(ordineProdottiControllerA.checkSessione()){
            try {
                setInputOrdine();
                ordineProdottiControllerA.ricercaProdotti(dispensaBean, categoriaBean);

                setGrafica();

            } catch (SearchException | ProductNotFoundException e) {
                logger.log(Level.WARNING, "Ops. Hai digitato un'opzione non valida, riprova ");
            }
        }
    }

    private void setInputOrdine(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("    Dove vuoi effettuare l'ordine ?");
            System.out.println("    Inserisci una dispensa ed una categoria associata, così da vedere tutti i prodotti");
            System.out.println("    Inserisci Nome Dispensa :");
            String nomeDispensa = reader.readLine();
            System.out.println("    Inserisci Nome Categoria :");
            String nomeCategoria = reader.readLine();

            dispensaBean = new DispensaBean(nomeDispensa);
            categoriaBean = new CategoriaBean(nomeCategoria);

        } catch (IOException e) {
            throw new RejectedExecutionException(e);

        }
    }

    private void setGrafica(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<ProdottoBean> prodotto = ClientController.getInstance().getProdottiBeans();

        boolean errore = false;

        do {
            try {
                System.out.println("        Dispensa --> " + dispensaBean.getNomeDispensa());

                for (ProdottoBean prodottoBean : prodotto) {
                    System.out.println("    Nome Prodotto   : " + prodottoBean.getNomeProdotto());
                    System.out.println("    Tipo Animale    : " + prodottoBean.getTipoAnimale());
                    System.out.println("    Scorte          : " + prodottoBean.getScorte() + "Kg");
                    System.out.println(" ");
                }

                System.out.println("1)  Inserisci Prodotto all'Ordine");
                System.out.println("2)  Visualizza Carrello");
                System.out.println("3)  Indietro");
                int scelta = Integer.parseInt(reader.readLine());
                switch (scelta) {
                    case 1:
                        inserisciProdotti();
                        nuovoInput = true;
                        break;

                    case 2:
                        visualizzaCarrello();

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

    private void inserisciProdotti(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nomeProdotto;
        TipoAnimale tipoAnimale;
        int qtaRichiesta;

        try {
            System.out.println("    Inserisci Nome Prodotto : ");
            nomeProdotto = reader.readLine();
            System.out.println("    Inserisci Tipo Animale (tutto maiuscolo, se non è animale, digitare NONANIMALE) : ");
            tipoAnimale = TipoAnimale.valueOf(reader.readLine());
            System.out.println("    Quantità Richiesta : ");
            qtaRichiesta = Integer.parseInt(reader.readLine());

            ProdottoOrdineBean prodottoOrdineBean = new ProdottoOrdineBean(nomeProdotto, tipoAnimale, qtaRichiesta);
            ClientController.getInstance().addOrdineBean(prodottoOrdineBean);

            System.out.println("    Prodotto aggiunto all'ordine !");

        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }
    }

    private void visualizzaCarrello(){
        VisualizzaCarrello visualizzaCarrello = new VisualizzaCarrello();
        visualizzaCarrello.visualizzaCarrelloOrdine();

        nuovoInput = !visualizzaCarrello.isInoltroOrdine();

    }
}
