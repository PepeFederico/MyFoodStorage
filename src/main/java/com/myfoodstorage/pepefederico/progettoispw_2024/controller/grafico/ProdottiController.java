package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.GestioneProdottiA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemProdottoController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdottiController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button nuovoProdotto;
    @FXML
    private Button backView;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(ProdottiController.class.getName());
    private List<ProdottoBean> prodotto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backView.setOnAction(actionEvent -> onBackView());

        GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(ClientController.getInstance().getSessioneUtente());
        if(gestioneProdottiA.checkSessione()){
            try{
                gestioneProdottiA.recuperoInfoProdotti();
                prodotto = gestioneProdottiA.getProdBean();
                setGrafica();
            } catch (ProductNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attenzione!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void setGrafica(){
        int colonna = 0;
        int riga = 1;
        try {
            for (ProdottoBean prodottoBean : prodotto) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemProdotto.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProdottoController itemProdottoController = fxmlLoader.getController();
                itemProdottoController.setItemProdotto(prodottoBean);

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

    private void onBackView() {
        ClientController.getInstance().backToCategoriaView();
        Model.getInstance().getViewFactory().setProdottiView();
    }
}
