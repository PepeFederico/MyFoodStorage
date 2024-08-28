package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.factory.OrdineFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OrdineDaoTest {
    /**
     *
     * Viene verificato che, nel caso in cui, l'inoltro dell'email fallisce, la gestione dell'eccezione prevede
     * che venga eliminato l'ordine non inoltrato e venga fatto un retry.
     * In questo caso testiamo che il codice correttamente elimini il file, il quale inoltro ha generato l'eccezione FailSendMail
     *
     */
    @Test
    void eliminaUltimoOrdineTest() {
        OrdineDao testOrdineDao = new OrdineDao();

        Ordine testOrdine = OrdineFactory.getInstance().getOrdine();
        ArrayList<Prodotto> testProdotto = new ArrayList<>();
        testProdotto.add(ProdottoFactory.getInstance().getProdotto(
                "Fettine", 5, TipoAnimale.VITELLA
        ));

        testOrdine.creaOrdine(testProdotto);
        testOrdine.setNomeFornitore("Test Fornitore");

        testOrdineDao.salvaOrdine(testOrdine, "Attesa", testOrdine.getNomeFornitore());

        assertTrue(testOrdineDao.eliminaUltimoOrdine());
    }
}