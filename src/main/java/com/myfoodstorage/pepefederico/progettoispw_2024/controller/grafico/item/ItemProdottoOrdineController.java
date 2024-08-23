package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ItemProdottoOrdineController {
    @FXML
    private Button addProdotto;
    @FXML
    private ImageView iconaProdotto;
    @FXML
    private Label nomeProdotto;
    @FXML
    private Label qtaDisponibile;
    @FXML
    private TextField qtaRichiesta;
    private ProdottoBean prodotto;

    public void setItemProdotto(ProdottoBean prodotto){
        addProdotto.setOnAction(actionEvent -> addProdotto());

        nomeProdotto.setText(prodotto.getNomeProdotto());
        int qtaDisp = (prodotto.getScorte() * prodotto.getTaglia())/1000;
        qtaDisponibile.setText(String.valueOf(qtaDisp));
        setProdotto(prodotto);
        String nomeCategoria = ClientController.getInstance().getCategoriaBean().getNomeCategoria();

        SetIconProdotti setIconProdotti = new SetIconProdotti();
        setIconProdotti.setIconProdotto(iconaProdotto,nomeCategoria,prodotto.getTipoAnimale());
    }

    private void addProdotto(){
        ProdottoOrdineBean prodottoOrdineBean = new ProdottoOrdineBean(
                prodotto.getNomeProdotto(),
                prodotto.getTipoAnimale(),
                Integer.parseInt(qtaRichiesta.getText())
                );
        ClientController.getInstance().addOrdineBean(prodottoOrdineBean);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Prodotto aggiunto all'ordine !!");
        alert.setTitle("Ordine Prodotti");
        alert.showAndWait();

        qtaRichiesta.clear();
    }

    private void setProdotto(ProdottoBean prodotto) {
        this.prodotto = prodotto;
    }
}
