package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemProdottoOrdineController;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class OrdineProdottiController implements Initializable {
    @FXML
    private Label dispensaSelezionata;
    @FXML
    private GridPane gridPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button visualizzaCarrello;
    @FXML
    private Button backToRicercaProdotti;

    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(OrdineProdottiController.class.getName());
    private ArrayList<ProdottoBean> prodotto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visualizzaCarrello.setOnAction(e -> visualizzaCarrello());
        backToRicercaProdotti.setOnAction(e -> backToRicercaProdotti());

        dispensaSelezionata.setText(ClientController.getInstance().getDispensaBean().getNomeDispensa());

        prodotto = ClientController.getInstance().getProdottiBeans();

        setGrafica();
    }

    private void setGrafica(){
        int colonna = 0;
        int riga = 1;
        try {
            for (ProdottoBean prodottoBean : prodotto) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemProdottoOrdine.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProdottoOrdineController itemProdottoOrdineController = fxmlLoader.getController();
                itemProdottoOrdineController.setItemProdotto(prodottoBean);

                if (colonna == 2) {
                    colonna = 0;
                    riga += 1;
                }
                gridPane.add(anchorPane, colonna++, riga);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, ACTION, e);
        }
    }

    private void visualizzaCarrello() {
        ClientController.getInstance().visualizzaOrdineView();
    }

    private void backToRicercaProdotti() {
        Model.getInstance().getViewFactory().setOrdineProdottiView();
        ClientController.getInstance().backToRicercaOrdineProdottiView();
    }
}
