package com.myfoodstorage.pepefederico.progettoispw_2024.boundary;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import org.junit.jupiter.api.Test;
import javax.mail.MessagingException;
import static com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale.VITELLA;
import static org.junit.jupiter.api.Assertions.*;

class SimpleMailTest {

    /**
     * In questo test viene testato il corretto lancio dell'eccezione
     * MessagingException, in quanto il suo riscontro viene usato dal controller applicativo
     * OrdineProdottiControllerA per effettuare un retry, prima di "fallire", cercando di
     * gestire il caso in cui, per esempio, non si riesce al primo tentativo ad inoltrare l'email al fornitore.
     * Per un corretto funzionamento, eseguire il Test senza Internet, per effettivamente verificare che viene lanciata correttamente l'eccezione.
     */

    @Test
    void testSendMail() {
        SimpleMail testMail = new SimpleMail();
        OrdineBean testOrdine = new OrdineBean();
        ProdottoOrdineBean testProdottoOrdineBean = new ProdottoOrdineBean("Fettine", VITELLA, 5);

        testOrdine.setProdotti(testProdottoOrdineBean);
        testOrdine.setContattoFornitore("myfoodstorage014@gmail.com");

        assertThrows(MessagingException.class, () -> testMail.sendMail(testOrdine), "Inoltro dell'email, funziona correttamente");
    }


}