package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button contrattiButton;
    @FXML
    private Button gestioneDispensaButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button impostazioniButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button ordineButton;
    @FXML
    private Button profiloButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addListeners();
    }

    private void addListeners(){
        homeButton.setOnAction(actionEvent -> onDashboard());
        profiloButton.setOnAction(actionEvent -> onProfilo());
        logoutButton.setOnAction(actionEvent -> onLogout());
        contrattiButton.setOnAction(actionEvent -> onContratti());
        ordineButton.setOnAction(actionEvent -> onOrdine());
    }

    private void onContratti() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("contratti");
    }
    private void onLogout() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("logout");
    }
    private void onProfilo() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("profilo");
    }
    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("homeDashboard");
    }
    private void onOrdine(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("ordine");
    }

}
