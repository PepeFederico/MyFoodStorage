package com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.*;
import com.myfoodstorage.pepefederico.progettoispw_2024.boundary.SimpleMail;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.OrdineDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FailSendMail;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.SearchException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ZeroOrderException;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.OrdineFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.*;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class OrdineProdottiControllerA {
    private final SessioneBean sessioneBean;
    private List<ProdottoBean> prodBean = new ArrayList<>();
    private OrdineBean ordineBean;
    private final List<OrdineBean> ordiniEffetuati;

    public OrdineProdottiControllerA(SessioneBean sessioneBean) {
        this.sessioneBean = sessioneBean;
        this.ordiniEffetuati = new ArrayList<>();
    }

    public boolean checkSessione(){
        for(Sessione sessione: Model.getInstance().getSessioniUtentiRistoratori()){
            if(sessione.getIdSessione() == sessioneBean.getIdSessione()){
                return true;
            }
        }
        return false;
    }

    public void ricercaProdotti(DispensaBean dispensaBean, CategoriaBean categoriaBean) throws ProductNotFoundException, SearchException {
        try {

            GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(sessioneBean);
            gestioneProdottiA.recuperoProdotti(dispensaBean, categoriaBean);
            setProdBean(gestioneProdottiA.getProdBean());

        }
        catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Ops, La dispensa è vuota. Inserisci prima i prodotti");
        }
        catch (SearchException e){
            throw new SearchException("Campi non corrispondono alle dispense memorizzate");
        }

        ordineBean = new OrdineBean();

        ClientController.getInstance().setOrdineBean(ordineBean);
        ClientController.getInstance().setDispensaBean(dispensaBean);
        ClientController.getInstance().setCategoriaBean(categoriaBean);
        ClientController.getInstance().setProdottiBeans(prodBean);
        ClientController.getInstance().setProdottiOrdine();

    }

    public void procediOrdine(OrdineBean ordineBean) throws FailSendMail {
       try {
            setOrdineBean(ordineBean);
            String nomeAttivita = ClientController.getInstance().getSessioneUtente().getUtente().getNomeAttivita();
            Ordine ordineProdotti = OrdineFactory.getInstance().getOrdine();

            ArrayList<Prodotto> prodotti = new ArrayList<>();
            for(int i = 0; i < ordineBean.getProdotti().size(); i++){
                prodotti.add(ProdottoFactory.getInstance().getProdotto(
                        ordineBean.getProdotti().get(i).getNomeProdotto(),
                        ordineBean.getProdotti().get(i).getQtaRichiesta(),
                        ordineBean.getProdotti().get(i).getTipoAnimale()));
            }
            ordineProdotti.creaOrdine(prodotti);

           List<ProdottoFornito> prodottiForniti = ordineProdotti.elaboraOrdine(nomeAttivita);
            for(int i = 0; i < ordineBean.getProdotti().size(); i++){
                for (ProdottoFornito prodottoFornito : prodottiForniti) {
                    if (ordineBean.getProdotti().get(i).getNomeProdotto().equals(prodottoFornito.getNomeProdotto())) {
                        ordineProdotti.setContattoFornitore(prodottoFornito.getContattoFornitore());
                    }
                }
            }
            ordineProdotti.inoltraOrdine();

            ordineBean.setContattoFornitore(ordineProdotti.getContattoFornitore());
            ordineBean.setNomeFornitore(ordineProdotti.getNomeFornitore());

            SimpleMail simpleMail = new SimpleMail();
            simpleMail.sendMail(ordineBean);

       }
       catch (MessagingException e) {
           throw new FailSendMail("Qualcosa e' andato storto. Riprova più tardi !!");
       }
    }

    public void recuperaOrdini() throws ZeroOrderException {
        try {
            OrdineDao ordineDAO = new OrdineDao();
            ordineDAO.recuperaInfoOrdini();

            List<Ordine> ordini = ordineDAO.getOrdine();

            for (Ordine ordine : ordini) {
                OrdineBean ordBean = new OrdineBean();

                for (int j = 0; j < ordine.getProdottiSelezionati().size(); j++) {

                    ProdottoOrdineBean prodottoOrdineBean = getProdottoOrdineBean(ordine, j);
                    ordBean.setProdotti(prodottoOrdineBean);

                }

                ordBean.setStatoOrdine(ordine.getStatoCorrente());
                ordBean.setNomeFornitore(ordine.getNomeFornitore());
                ordBean.setDataConsegna(String.valueOf(ordine.getDataConsegna()));
                ordiniEffetuati.add(ordBean);
            }
        } catch (ZeroOrderException e){
            throw new ZeroOrderException(e.getMessage());
        }
    }

    private ProdottoOrdineBean getProdottoOrdineBean(Ordine ordine, int j) {

        String nomeProdotto = ordine.getProdottiSelezionati().get(j).getNomeProdotto();
        TipoAnimale tipoAnimale = ordine.getProdottiSelezionati().get(j).getTipoAnimale();
        int qtaRichiesta = ordine.getProdottiSelezionati().get(j).getScorte();

        return new ProdottoOrdineBean(
                nomeProdotto,
                tipoAnimale,
                qtaRichiesta
        );
    }

    public void setProdBean(ArrayList<ProdottoBean> prodBean) {
        this.prodBean = prodBean;
    }

    public void setOrdineBean(OrdineBean ordineBean) {
        this.ordineBean = ordineBean;
    }
    public List<OrdineBean> getOrdiniEffetuati() {
        return ordiniEffetuati;
    }
}
