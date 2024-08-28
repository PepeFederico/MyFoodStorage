package com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo;

import com.myfoodstorage.pepefederico.progettoispw_2024.dao.UtenteDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.UserNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteLoginBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.*;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteBean;

public class LoginControllerA {
    private UtenteLoginBean utente;
    private UtenteDao utenteDAO;
    private UtenteBean utenteBean;
    private Utente utenteLoggato;
    private Sessione sessioneUtente;
    private SessioneBean sessioneBean;

    public LoginControllerA(UtenteLoginBean utente) {
        this.utente = utente;
        this.utenteDAO = new UtenteDao();
        this.utenteBean = new UtenteBean();
    }

    public LoginControllerA() {}

    public void autenticazioneUtente() throws UserNotFoundException {
        try {
            ricercaUtente();

            if(utenteLoggato.getTipoUtente().equals("Ristoratore")){
                Model.getInstance().addSessioneUtenteRistoratore(sessioneUtente);
                Model.getInstance().getViewFactory().showRistoratoreWindow(sessioneBean);
            }

        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException("Errore: Credenziali non valide");
        }
    }

    public void autenticazioneUtenteCLI() throws UserNotFoundException {
        try {
            ricercaUtente();

            if(utenteLoggato.getTipoUtente().equals("Ristoratore")){
                Model.getInstance().addSessioneUtenteRistoratore(sessioneUtente);
                Model.getInstance().getViewFactoryCLI().showRistoratoreWindowCLI(sessioneBean);
            }

        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException("Errore: Credenziali non valide");
        }
    }

    public void logout(SessioneBean sessione){
        Model.getInstance().removeSessioneUtenteRistoratore(sessione.getIdSessione());
        DispenseUtente.getInstance().setLogoutDispenseUtente();
    }

    private void ricercaUtente() throws UserNotFoundException {
        utenteDAO.verificaCredenziali(utente.getEmail(), utente.getPassword());
        utenteLoggato = utenteDAO.getUtenteLoggato();
        sessioneUtente = com.myfoodstorage.pepefederico.progettoispw_2024.factory.SessionFactory.getInstance().getSessione(utenteLoggato);

        fillUtenteBean(utenteLoggato);
        sessioneBean = new SessioneBean(sessioneUtente.getIdSessione(), sessioneUtente.getData(), utenteBean, sessioneUtente.isStatusSessione());
    }

    private void fillUtenteBean(Utente utente){
        utenteBean.setNome(utente.getNome());
        utenteBean.setCognome(utente.getCognome());
        utenteBean.setEmail(utente.getEmail());
        utenteBean.setPassword(utente.getPassword());
        utenteBean.setTipoUtente(utente.getTipoUtente());

        utenteBean.setNomeAttivita(utente.getNomeAttivita());
        utenteBean.setViaAttivita(utente.getViaAttivita());
        utenteBean.setCap(utente.getCap());
        utenteBean.setCitta(utente.getCitta());
        utenteBean.setNumeroCivico(utente.getNumeroCivico());
        utenteBean.setPartitaIva(utente.getPartitaIva());
        utenteBean.setNumeroTelefono(utente.getNumeroTelefono());
    }

}
