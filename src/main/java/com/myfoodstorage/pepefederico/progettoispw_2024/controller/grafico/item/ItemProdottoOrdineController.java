package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoOrdineBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        switch (nomeCategoria){
            case "Carne":
                switch (prodotto.getTipoAnimale()) {
                    case Manzo:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/cow.png"))));
                        break;
                    case Vitella:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/dinosaur.png"))));
                        break;
                    case Maiale:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/pig.png"))));
                        break;
                    case Pollo:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/hen.png"))));
                        break;
                    case Tacchino:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/turkey.png"))));
                        break;
                }
            break;

            case "Pesce":
                switch (prodotto.getTipoAnimale()) {
                    case Salmone:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/fish.png"))));
                        break;
                    case Tonno:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/tuna.png"))));
                        break;
                }
            break;

            case "Verdura":
                iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/vegetable.png"))));
            break;

            case "Frutta":
                iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/fruits.png"))));
            break;

            default:
                iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
        }
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
