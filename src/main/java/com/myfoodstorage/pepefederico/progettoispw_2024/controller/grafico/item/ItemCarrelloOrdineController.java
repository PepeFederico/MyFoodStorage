package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ItemCarrelloOrdineController {
    @FXML
    private Label TipoAnimale;
    @FXML
    private Button modificaProdotto;
    @FXML
    private Label nomeProdotto;
    @FXML
    private TextField qtaRichiesta;
    @FXML
    private Button rimuoviProdotto;

    public void setItemProdotto(ProdottoOrdineBean prodottoOrdineBean){
        nomeProdotto.setText(prodottoOrdineBean.getNomeProdotto());
        qtaRichiesta.setText(String.valueOf(prodottoOrdineBean.getQtaRichiesta()));
        TipoAnimale.setText(prodottoOrdineBean.getTipoAnimale().toString());

        modificaProdotto.setOnAction(actionEvent -> modificaProdotto());
        rimuoviProdotto.setOnAction(actionEvent -> rimuoviProdotto());
    }

    private void rimuoviProdotto(){
        OrdineBean ordineBean = ClientController.getInstance().getOrdineBean();

        for(int i = 0; i < ordineBean.getProdotti().size(); i++){
            if(nomeProdotto.getText().equals(ordineBean.getProdotti().get(i).getNomeProdotto())){
                ordineBean.getProdotti().remove(i);
            }
        }

        AlertMessage("Hai rimosso il prodotto dall'ordine");
    }

    private void modificaProdotto(){
        int qtaModificata = Integer.parseInt(qtaRichiesta.getText());
        OrdineBean ordineBean = ClientController.getInstance().getOrdineBean();

        for(int i = 0; i < ordineBean.getProdotti().size(); i++){
            if(nomeProdotto.getText().equals(ordineBean.getProdotti().get(i).getNomeProdotto())){
                ordineBean.getProdotti().get(i).setQtaRichiesta(qtaModificata);
            }
        }

        AlertMessage("Hai modificato la quantità di prodotto richiesta");
    }

    private void AlertMessage(String string){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ordine Prodotti");
        alert.setContentText(string);
        alert.showAndWait();
    }

}
