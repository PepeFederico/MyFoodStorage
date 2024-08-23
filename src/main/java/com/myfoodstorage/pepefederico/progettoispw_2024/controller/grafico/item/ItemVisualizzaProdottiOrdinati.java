package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemVisualizzaProdottiOrdinati {

    @FXML
    private Label TipoAnimale;
    @FXML
    private Label nomeProdotto;
    @FXML
    private Label qtaRichiesta;

    public void setInfoProdottiOrdinati(ProdottoOrdineBean prodottoOrdineBean) {
        nomeProdotto.setText(prodottoOrdineBean.getNomeProdotto());
        TipoAnimale.setText(String.valueOf(prodottoOrdineBean.getTipoAnimale()));
        qtaRichiesta.setText(String.valueOf(prodottoOrdineBean.getQtaRichiesta()));
    }

}
