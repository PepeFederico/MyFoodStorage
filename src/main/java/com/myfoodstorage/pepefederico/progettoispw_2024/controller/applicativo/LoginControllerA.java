package com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.myfoodstorage.pepefederico.progettoispw_2024.dao.UtenteDao;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.UserNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteLoginBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Sessione;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Utente;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteBean;

public class LoginControllerA {
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(LoginControllerA.class.getName());
    private UtenteLoginBean utente;
    private UtenteDao utenteDAO;
    private UtenteBean utenteBean;

    public LoginControllerA(UtenteLoginBean utente) {
        this.utente = utente;
        this.utenteDAO = new UtenteDao();
        this.utenteBean = new UtenteBean();
    }

    public LoginControllerA() {}

    public void autenticazioneUtente() throws Exception {
        try {
            utenteDAO.verificaCredenziali(utente.getEmail(), utente.getPassword());
            Utente utenteLoggato = utenteDAO.getUtenteLoggato();
            Sessione sessioneUtente = com.myfoodstorage.pepefederico.progettoispw_2024.factory.SessionFactory.getInstance().getSessione(utenteLoggato);

            fillUtenteBean(utenteLoggato);
            SessioneBean sessioneBean = new SessioneBean(sessioneUtente.getIdSessione(), sessioneUtente.getData(), utenteBean, sessioneUtente.isStatusSessione());

            if(utenteLoggato.getTipoUtente().equals("Ristoratore")){
                Model.getInstance().addSessioneUtenteRistoratore(sessioneUtente);
                Model.getInstance().getViewFactory().showRistoratoreWindow(sessioneBean);
            }else{
                //Fornitore
            }

        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException("Errore: Credenziali non valide");
        } catch (IOException e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }
    public void logout(SessioneBean sessione){
        Model.getInstance().removeSessioneUtenteRistoratore(sessione.getIdSessione());
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
