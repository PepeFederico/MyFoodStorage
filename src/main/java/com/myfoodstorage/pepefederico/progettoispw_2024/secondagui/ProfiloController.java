package com.myfoodstorage.pepefederico.progettoispw_2024.secondagui;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.ProfiloControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;

public class ProfiloController {

    public void recuperoInfoProfilo() {
        ProfiloControllerA profiloControllerA = new ProfiloControllerA(ClientController.getInstance().getSessioneUtente());
        if(profiloControllerA.checkSessione()){
            SessioneBean sessioneBean = ClientController.getInstance().getSessioneUtente();
            setInfoGrafica(sessioneBean);
        }
    }

    private void setInfoGrafica(SessioneBean sessioneBean) {
        System.out.println("------Profilo View------");
        System.out.println("    Tipo Utente     : " + sessioneBean.getUtente().getTipoUtente());
        System.out.println("    Nome            : " + sessioneBean.getUtente().getNome());
        System.out.println("    Cognome         : " + sessioneBean.getUtente().getCognome());
        System.out.println("    Email           : " + sessioneBean.getUtente().getEmail());
        System.out.println("    Password        : " + sessioneBean.getUtente().getPassword());
        System.out.println("    ------Campi Attività------");
        System.out.println("    Partita IVA     : " + sessioneBean.getUtente().getPartitaIva());
        System.out.println("    Nome Attività   : " + sessioneBean.getUtente().getNomeAttivita());
        System.out.println("    Via             : " + sessioneBean.getUtente().getViaAttivita());
        System.out.println("    Numero Civico   : " + sessioneBean.getUtente().getNumeroCivico());
        System.out.println("    CAP             : " + sessioneBean.getUtente().getCap());
        System.out.println("    Città           : " + sessioneBean.getUtente().getCitta());
        System.out.println("    Recapito        : " + sessioneBean.getUtente().getNumeroTelefono());

    }
}
