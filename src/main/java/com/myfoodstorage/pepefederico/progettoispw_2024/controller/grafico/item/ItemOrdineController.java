package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemOrdineController {
    @FXML
    private Label dataConsegna;
    @FXML
    private ImageView iconaStatoOrdine;
    @FXML
    private Label labelConsegna;
    @FXML
    private Label statoOrdine;
    @FXML
    private Button visualizzaProdotti;
    @FXML
    private Label versoFornitore;
    private OrdineBean ordine;

    public void setItemOrdine(OrdineBean ordineBean) {
        statoOrdine.setText(ordineBean.getStatoOrdine());
        versoFornitore.setText(ordineBean.getNomeFornitore());
        visualizzaProdotti.setOnAction(event -> onVisualizzaProdotti());
        setOrdine(ordineBean);

        switch (ordineBean.getStatoOrdine()) {
            case "Attesa":
                iconaStatoOrdine.setImage(new Image(String.valueOf(getClass().getResource("/image/imageOrdine/clock.png"))));
                labelConsegna.setVisible(false);
                dataConsegna.setVisible(false);
                break;

            case "Accettato":
                iconaStatoOrdine.setImage(new Image(String.valueOf(getClass().getResource("/image/imageOrdine/shopping-bag.png"))));
                dataConsegna.setText(ordineBean.getDataConsegna());
                break;

            default:
                iconaStatoOrdine.setImage(new Image(String.valueOf(getClass().getResource("/image/imageOrdine/transport.png"))));
                labelConsegna.setVisible(false);
                dataConsegna.setVisible(false);
        }
    }

    private void onVisualizzaProdotti() {
        ClientController.getInstance().setOrdineBean(ordine);
        ClientController.getInstance().setClientParentProdottiOrdinati();
    }

    private void setOrdine(OrdineBean ordine) {
        this.ordine = ordine;
    }
}
