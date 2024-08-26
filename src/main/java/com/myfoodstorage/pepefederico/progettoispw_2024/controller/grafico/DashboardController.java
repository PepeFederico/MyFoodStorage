package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.GestioneProdottiA;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item.ItemDispensaController;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FoodStorageNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController implements Initializable {
    @FXML
    private GridPane gridPane;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(DashboardController.class.getName());
    private List<DispensaBean> dispensa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GestioneProdottiA gestioneProdottiA = new GestioneProdottiA(ClientController.getInstance().getSessioneUtente());
        if (gestioneProdottiA.checkSessione()) {
            try {
                gestioneProdottiA.recuperoInfoDispensa();
                dispensa = gestioneProdottiA.getDispBean();
                setGrafica();
            } catch (FoodStorageNotFoundException e) {
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
            for (DispensaBean dispensaBean : dispensa) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/Item/itemDispensa.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemDispensaController itemController = fxmlLoader.getController();
                itemController.setItemDispensa(dispensaBean);

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
}
