package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemOrdineController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ZeroOrderException;
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
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizzaStatoOrdineController implements Initializable {
    @FXML
    private Button backToRicercaProdotti;
    @FXML
    private Button aggiornaStatoOrdini;
    @FXML
    private GridPane gridPane;
    private List<OrdineBean> ordineBean;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(VisualizzaStatoOrdineController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToRicercaProdotti.setOnAction(e -> onBackView());
        aggiornaStatoOrdini.setOnAction(e -> onAggiornaOrdini());
        recuperaOrdini();
    }

    private void recuperaOrdini(){
        try {
            OrdineProdottiControllerA ordineProdottiControllerA = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
            if (ordineProdottiControllerA.checkSessione()) {
                ordineProdottiControllerA.recuperaOrdini();
                ordineBean = ordineProdottiControllerA.getOrdiniEffetuati();
                setGrafica();
            }
        }catch (ZeroOrderException | RejectedExecutionException e){
            alertErrorMessage(e.getMessage());
        }
    }

    private void onBackView(){
        Model.getInstance().getViewFactory().setOrdineProdottiView();
        ClientController.getInstance().backToRicercaOrdineProdottiView();
    }

    private void onAggiornaOrdini(){
        recuperaOrdini();
    }

    private void setGrafica(){
        int colonna = 0;
        int riga = 1;
        try {
            for (OrdineBean ordBean : ordineBean) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemOrdine.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemOrdineController itemOrdineController = fxmlLoader.getController();
                itemOrdineController.setItemOrdine(ordBean);

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

    private void alertErrorMessage(String string){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ordine Prodotti");
        alert.setContentText(string);
        alert.showAndWait();
    }

}
