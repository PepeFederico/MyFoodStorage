package com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.SessionFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.UserFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Sessione;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Utente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class OrdineProdottiControllerATest {
    /**
     *
     * In questo caso di test, vengono ricreate le condizioni per controllare, l'effettiva validità della sessione utente
     * attraverso il metodo checkSessione(), il quale verifica se la sessione utente corrente è effettivamente valida, e quindi si è
     * autorizzati a procedere con il preseguimento del caso d'uso (in questo caso: Proponi Ordine)
     *
     */
    @Test
    void checkSessione() {
        Utente testUtente = UserFactory.getInstance().getUtente(
                "Ristoratore",
                "lorem Ipsum",
                "lorem Ipsum",
                "lorem Ipsum",
                "lorem Ipsum",
                "lorem Ipsum",
                "lorem Ipsum",
                123,
                "lorem Ipsum",
                "00123",
                "lorem Ipsum",
                "0000000000");
        Sessione testSession = SessionFactory.getInstance().getSessione(testUtente);
        Model.getInstance().addSessioneUtenteRistoratore(testSession);

        SessioneBean testSessioneBean = getSessioneBean(testUtente, testSession);
        OrdineProdottiControllerA testOrdine = new OrdineProdottiControllerA(testSessioneBean);

        assertTrue(testOrdine.checkSessione());
    }

    private SessioneBean getSessioneBean(Utente testUtente, Sessione testSession) {
        UtenteBean testUtenteBean = new UtenteBean();

        testUtenteBean.setNome(testUtente.getNome());
        testUtenteBean.setCognome(testUtente.getCognome());
        testUtenteBean.setEmail(testUtente.getEmail());
        testUtenteBean.setPassword(testUtente.getPassword());
        testUtenteBean.setTipoUtente(testUtente.getTipoUtente());
        testUtenteBean.setNomeAttivita(testUtente.getNomeAttivita());
        testUtenteBean.setViaAttivita(testUtente.getViaAttivita());
        testUtenteBean.setCap(testUtente.getCap());
        testUtenteBean.setCitta(testUtente.getCitta());
        testUtenteBean.setNumeroCivico(testUtente.getNumeroCivico());
        testUtenteBean.setPartitaIva(testUtente.getPartitaIva());
        testUtenteBean.setNumeroTelefono(testUtente.getNumeroTelefono());

        return new SessioneBean(testSession.getIdSessione(), testSession.getData(), testUtenteBean, testSession.isStatusSessione());
    }

}