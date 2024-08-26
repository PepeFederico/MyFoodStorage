package com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.CategoriaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.CategoriaDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.ProdottoDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.CategoryNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FoodStorageNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.SearchException;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.*;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.DispensaDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestioneProdottiA {
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(GestioneProdottiA.class.getName());
    private final SessioneBean sessioneBean;
    private final ArrayList<DispensaBean> dispBean = new ArrayList<>();
    private final ArrayList<CategoriaBean> catBean = new ArrayList<>();
    private final ArrayList<ProdottoBean> prodBean = new ArrayList<>();

    public GestioneProdottiA(SessioneBean sessioneBean) {
        this.sessioneBean = sessioneBean;
    }

    public boolean checkSessione(){
        for(Sessione sessione: Model.getInstance().getSessioniUtentiRistoratori()){
            if(sessione.getIdSessione() == sessioneBean.getIdSessione()){
                return true;
            }
        }
        return false;
    }

    public void recuperoInfoDispensa() throws FoodStorageNotFoundException {
        try{
            if(DispenseUtente.getInstance().getDispense() == null){
                DispensaDao dispensaDAO = new DispensaDao();
                dispensaDAO.recuperoDispensa(sessioneBean.getUtente().getNomeAttivita());
                ArrayList<Dispensa> disp = dispensaDAO.getDispensa();
                DispenseUtente.getInstance().setDispense(disp);
            }
            infoToDashboardController(DispenseUtente.getInstance().getDispense());
        }catch (FoodStorageNotFoundException e){
            throw new FoodStorageNotFoundException("Ops, come è vuota la tua dispensa. Prova ad inserire una nuova dispensa!");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public void recuperoInfoCategoria() throws CategoryNotFoundException {
        try {
            String dispensaSelezionata = ClientController.getInstance().getDispensaBean().getNomeDispensa();
            for(int i = 0; i < DispenseUtente.getInstance().getDispense().size(); i++){
                if(DispenseUtente.getInstance().getDispense().get(i).getNomeDispensa().equals(dispensaSelezionata)){
                    if(DispenseUtente.getInstance().getDispense().get(i).getCategorie() == null){
                        CategoriaDao categoriaDAO = new CategoriaDao();
                        categoriaDAO.recuperoCategorie(sessioneBean.getUtente().getNomeAttivita(), dispensaSelezionata);
                        ArrayList<Categoria> categorias = categoriaDAO.getCategoria();
                        DispenseUtente.getInstance().getDispense().get(i).setCategorie(categorias);
                    }
                    infoToCategoriaController(DispenseUtente.getInstance().getDispense().get(i).getCategorie());
                }
            }
        }catch (CategoryNotFoundException e){
            throw new CategoryNotFoundException("La dispensa non presenta alcuna Categoria di Prodotti. Aggiungi una categoria");
        } catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public void recuperoInfoProdotti() throws ProductNotFoundException {
        try{
            String dispensaSelezionata = ClientController.getInstance().getDispensaBean().getNomeDispensa();
            String categoriaSelezionata = ClientController.getInstance().getCategoriaBean().getNomeCategoria();

            Dispensa dispensa = searchDispensa(dispensaSelezionata);
            int i = DispenseUtente.getInstance().getDispense().indexOf(dispensa);
            Categoria categoria = searchCategoria(categoriaSelezionata, Objects.requireNonNull(dispensa));
            int j = DispenseUtente.getInstance().getDispense().get(i).getCategorie().indexOf(categoria);

            if(Objects.requireNonNull(categoria).getProdotti() == null){
                ProdottoDao prodottoDAO = new ProdottoDao();
                prodottoDAO.recuperoProdotti(sessioneBean.getUtente().getNomeAttivita(), categoriaSelezionata, dispensaSelezionata);
                ArrayList<Prodotto> prodotti = prodottoDAO.getProdotti();
                DispenseUtente.getInstance().getDispense().get(i).getCategorie().get(j).setProdotti(prodotti);
            }
            infoToProdottiController(DispenseUtente.getInstance().getDispense().get(i).getCategorie().get(j).getProdotti());

        }catch (ProductNotFoundException | SearchException e){
            throw new ProductNotFoundException("Ops, come è vuota la tua dispensa. Inserisci qualche prodotto");
        }catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public void recuperoProdotti(DispensaBean dispensaBean, CategoriaBean categoriaBean) throws ProductNotFoundException, SearchException {
        try{
            ProdottoDao prodottoDAO = new ProdottoDao();
            prodottoDAO.recuperoProdotti(sessioneBean.getUtente().getNomeAttivita(), categoriaBean.getNomeCategoria() ,dispensaBean.getNomeDispensa());
            ArrayList<Prodotto> prodotti = prodottoDAO.getProdotti();
            for (Prodotto prodotto : prodotti) {
                ProdottoBean prodottobean = new ProdottoBean(
                        prodotto.getNomeProdotto(),
                        prodotto.getNumeroLotto(),
                        prodotto.getScadenza(),
                        prodotto.getTaglia(),
                        prodotto.getScorte(),
                        prodotto.getCosto(),
                        prodotto.getTipoAnimale());
                prodBean.add(prodottobean);
            }
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException("Ops, come è vuota la tua dispensa. Inserisci qualche prodotto");
        }
        catch (SearchException e){
            throw new SearchException("Campi non corrispondono alle dispense memorizzate");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public List<CategoriaBean> getCatBean() {
        return catBean;
    }
    public List<DispensaBean> getDispBean() {
        return dispBean;
    }
    public List<ProdottoBean> getProdBean() {
        return prodBean;
    }
    private void infoToDashboardController(ArrayList<Dispensa> d){
        for(Dispensa dispensa : d){
            DispensaBean dispensabean = new DispensaBean(dispensa.getNomeDispensa());
            dispBean.add(dispensabean);
        }
    }
    private void infoToCategoriaController(ArrayList<Categoria> c){
        for (Categoria categoria : c) {
            CategoriaBean categoriabean = new CategoriaBean(categoria.getNomeCategoria());
            catBean.add(categoriabean);
        }
    }
    private void infoToProdottiController(ArrayList<Prodotto> p){
        for (Prodotto prodotto : p) {
            ProdottoBean prodottobean = new ProdottoBean(
                    prodotto.getNomeProdotto(),
                    prodotto.getNumeroLotto(),
                    prodotto.getScadenza(),
                    prodotto.getTaglia(),
                    prodotto.getScorte(),
                    prodotto.getCosto(),
                    prodotto.getTipoAnimale());
            prodBean.add(prodottobean);
        }
    }
    private Dispensa searchDispensa(String dispensaSelezionata) {
        for(int i = 0; i < DispenseUtente.getInstance().getDispense().size(); i++) {
            if (DispenseUtente.getInstance().getDispense().get(i).getNomeDispensa().equals(dispensaSelezionata))
                return DispenseUtente.getInstance().getDispense().get(i);
        }
        return null;
    }
    private Categoria searchCategoria(String categoriaSelezionata, Dispensa dispensa) {
        for(int j = 0; j < dispensa.getCategorie().size(); j++) {
            if (dispensa.getCategorie().get(j).getNomeCategoria().equals(categoriaSelezionata)) {
                return dispensa.getCategorie().get(j);
            }
        }
        return null;
    }

}
