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
import com.myfoodstorage.pepefederico.progettoispw_2024.model.*;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.DispensaDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestioneProdottiA {
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(GestioneProdottiA.class.getName());
    private final SessioneBean sessioneBean;
    private DispensaBean dispensaBean;
    private CategoriaBean categoriaBean;
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
            DispensaDao dispensaDAO = new DispensaDao();
            dispensaDAO.recuperoDispensa(sessioneBean.getUtente().getNomeAttivita());
            ArrayList<Dispensa> disp = dispensaDAO.getDispensa();
            for(Dispensa dispensa : disp){
                DispensaBean dispensabean = new DispensaBean(dispensa.getNomeDispensa());
                dispBean.add(dispensabean);
            }
        }catch (FoodStorageNotFoundException e){
            throw new FoodStorageNotFoundException("Ops, come è vuota la tua dispensa. Prova ad inserire una nuova dispensa!");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public void recuperoInfoCategoria() throws CategoryNotFoundException {
        try {
            setDispensaBean(ClientController.getInstance().getDispensaBean());
            CategoriaDao categoriaDAO = new CategoriaDao();
            categoriaDAO.recuperoCategorie(sessioneBean.getUtente().getNomeAttivita(), dispensaBean.getNomeDispensa());
            ArrayList<Categoria> categorias = categoriaDAO.getCategoria();
            for (Categoria categoria : categorias) {
                CategoriaBean categoriabean = new CategoriaBean(categoria.getNomeCategoria());
                catBean.add(categoriabean);
            }
        }catch (CategoryNotFoundException e){
            throw new CategoryNotFoundException("La dispensa non presenta alcuna Categoria di Prodotti. Aggiungi una categoria");
        } catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public void recuperoInfoProdotti() throws ProductNotFoundException {
        try{
            setDispensaBean(ClientController.getInstance().getDispensaBean());
            setCategoriaBean(ClientController.getInstance().getCategoriaBean());
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
        }catch (Exception e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    public ArrayList<CategoriaBean> getCatBean() {
        return catBean;
    }
    public ArrayList<DispensaBean> getDispBean() {
        return dispBean;
    }
    public ArrayList<ProdottoBean> getProdBean() {
        return prodBean;
    }
    public void setDispensaBean(DispensaBean dispensaBean) {
        this.dispensaBean = dispensaBean;
    }
    public void setCategoriaBean(CategoriaBean categoriaBean){
        this.categoriaBean = categoriaBean;
    }
}
