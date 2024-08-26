package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemCarrelloOrdineController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FailSendMail;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizzaOrdineController implements Initializable {
    @FXML
    private Button backToOrdineProdotti;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button procediOrdine;
    @FXML
    private Button aggiornaGrafica;
    OrdineBean ordineBean;
    OrdineProdottiControllerA ordineProdotti;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(VisualizzaOrdineController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToOrdineProdotti.setOnAction(actionEvent -> onBackView());
        procediOrdine.setOnAction(actionEvent -> procediOrdine());

        aggiornaGrafica.setOnAction(actionEvent -> aggiornaView());
        ordineBean = ClientController.getInstance().getOrdineBean();
        setGrafica();
    }

    private void procediOrdine(){
        try {
            if (!ordineBean.getProdotti().isEmpty()) {
                ordineProdotti = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
                if (ordineProdotti.checkSessione()) {
                    ordineProdotti.procediOrdine(ordineBean);
                    alertMessage("Ordine inoltrato al Fornitore con successo !!");
                    backToRicercaProdotti();
                }
            } else {
                alertMessage("L'ordine è vuoto !! Inserisci almeno un prodotto");
                onBackView();
            }

        }catch (FailSendMail e){
            alertErrorMessage(e.getMessage());
            backToRicercaProdotti();
        }
    }

    private void aggiornaView(){
        ordineBean = ClientController.getInstance().getOrdineBean();
        gridPane.getChildren().clear();
        setGrafica();
    }

    private void onBackView() {
        ClientController.getInstance().backToOrdineView();
        Model.getInstance().getViewFactory().setVisualizzaProdottiView();
    }

    private void backToRicercaProdotti() {
        Model.getInstance().getViewFactory().setOrdineProdottiView();
        ClientController.getInstance().backToRicercaOrdineProdottiView();
    }

    private void setGrafica(){
        int colonna = 0;
        int riga = 1;
        try {
            for (ProdottoOrdineBean prodottoOrdineBean : ordineBean.getProdotti()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemCarrelloOrdine.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCarrelloOrdineController itemCarrelloOrdineController = fxmlLoader.getController();
                itemCarrelloOrdineController.setItemProdotto(prodottoOrdineBean);

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

    private void alertMessage(String string){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ordine Prodotti");
        alert.setContentText(string);
        alert.showAndWait();
    }

    private void alertErrorMessage(String string){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ordine Prodotti");
        alert.setContentText(string);
        alert.showAndWait();
    }

}
