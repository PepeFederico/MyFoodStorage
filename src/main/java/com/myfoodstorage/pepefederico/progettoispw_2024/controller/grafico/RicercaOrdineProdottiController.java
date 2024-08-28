package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.CategoriaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.OrdineProdottiControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.SearchException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RicercaOrdineProdottiController implements Initializable {
    @FXML
    private TextField categoriaInput;
    @FXML
    private TextField dispensaInput;
    @FXML
    private Button ricercaProdotti;
    @FXML
    private Button visualizzaOrdini;
    private final DispensaBean dispensaBean = new DispensaBean();
    private final CategoriaBean categoriaBean = new CategoriaBean();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ricercaProdotti.setOnAction(e -> onRicercaProdotti());
        visualizzaOrdini.setOnAction(e -> visualizzaOrdini());
    }

    private void onRicercaProdotti(){
        OrdineProdottiControllerA ordineProdottiControllerA = new OrdineProdottiControllerA(ClientController.getInstance().getSessioneUtente());
        if(ordineProdottiControllerA.checkSessione()) {
            try {
                if(!dispensaInput.getText().isEmpty() && !categoriaInput.getText().isEmpty()) {
                    dispensaBean.setNomeDispensa(dispensaInput.getText());
                    categoriaBean.setNomeCategoria(categoriaInput.getText());

                    ordineProdottiControllerA.ricercaProdotti(dispensaBean, categoriaBean);
                    ClientController.getInstance().setProdottiOrdine();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Attenzione!! non hai inserito alcuna informazione.");
                    alert.showAndWait();
                }
            } catch (ProductNotFoundException | SearchException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Attenzione!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void visualizzaOrdini(){
        ClientController.getInstance().visualizzaStatoOrdini();
    }
}
