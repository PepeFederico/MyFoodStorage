package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ViewFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ViewFactoryCLI;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ViewFactory viewFactory;
    private final ViewFactoryCLI viewFactoryCLI;
    private static Model model;
    private final List<Sessione> sessioniUtentiRistoratori = new ArrayList<>();
    private final List<Sessione> sessioniUtentiFornitori = new ArrayList<>();

    private Model(){
        this.viewFactory = new ViewFactory();
        this.viewFactoryCLI = new ViewFactoryCLI();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public ViewFactoryCLI getViewFactoryCLI(){
        return viewFactoryCLI;
    }

    /*
    ***Sessione utenti tipo Ristoratore***
     */
    public void addSessioneUtenteRistoratore(Sessione sessione) {
        this.sessioniUtentiRistoratori.add(sessione);
    }
    public void removeSessioneUtenteRistoratore(int sessionID){
        sessioniUtentiRistoratori.removeIf(sessione -> sessione.getIdSessione() == sessionID);
    }
    public List<Sessione> getSessioniUtentiRistoratori() {
        return sessioniUtentiRistoratori;
    }

    /*
     ***Sessione utenti tipo Fornitore***
     */
    public void addSessioneUtenteFornitore(Sessione sessione) {
        this.sessioniUtentiFornitori.add(sessione);
    }
    public List<Sessione> getSessioniUtentiFornitori() {
        return sessioniUtentiFornitori;
    }
}
