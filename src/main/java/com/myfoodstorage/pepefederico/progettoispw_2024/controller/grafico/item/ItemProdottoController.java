package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemProdottoController {
    @FXML
    private ImageView iconaProdotto;
    @FXML
    private Label nomeProdotto;
    @FXML
    private Label scadenza;
    @FXML
    private Label scorte;
    @FXML
    private Label taglia;

    public void setItemProdotto(ProdottoBean prodotto){
        nomeProdotto.setText(prodotto.getNomeProdotto());
        scadenza.setText(String.valueOf(prodotto.getScadenza()));
        scorte.setText(String.valueOf(prodotto.getScorte()));
        taglia.setText(String.valueOf(prodotto.getTaglia()));

        String nomeCategoria = ClientController.getInstance().getCategoriaBean().getNomeCategoria();
        SetIconProdotti setIconProdotti = new SetIconProdotti();
        setIconProdotti.setIconProdotto(iconaProdotto,nomeCategoria,prodotto.getTipoAnimale());
    }
}
