package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemVisualizzaProdottiOrdinati;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizzaOrdineProdottiController implements Initializable {
    @FXML
    private Button backToStatoOrdine;
    @FXML
    private GridPane gridPane;
    @FXML
    private ScrollPane scrollPane;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(VisualizzaOrdineProdottiController.class.getName());
    private ArrayList<ProdottoOrdineBean> prodotti;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToStatoOrdine.setOnAction(e -> onBackView());
        OrdineBean ordineBean = ClientController.getInstance().getOrdineBean();
        prodotti = ordineBean.getProdotti();
        setGrafica();
    }

    private void onBackView(){
        ClientController.getInstance().backToStatoOrdiniView();
        Model.getInstance().getViewFactory().setVisualizzaOrdiniView();
    }

    private void setGrafica(){
        int colonna = 0;
        int riga = 1;
        try {
            for (ProdottoOrdineBean prodottoOrdineBean : prodotti) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemProdottoOrdineVisualizza.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemVisualizzaProdottiOrdinati itemVisualizzaProdottiOrdinati = fxmlLoader.getController();
                itemVisualizzaProdottiOrdinati.setInfoProdottiOrdinati(prodottoOrdineBean);

                if (colonna == 1) {
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


}
