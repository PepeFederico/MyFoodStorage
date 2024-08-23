package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.ProdottoBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemProdottoController {
    @FXML
    private ImageView iconaProdotto;
    @FXML
    private Label nomeProdotto;
    @FXML
    private Label scadenza;
    @FXML
    private Label scorte;
    @FXML
    private Label taglia;

    public void setItemProdotto(ProdottoBean prodotto){
        nomeProdotto.setText(prodotto.getNomeProdotto());
        scadenza.setText(String.valueOf(prodotto.getScadenza()));
        scorte.setText(String.valueOf(prodotto.getScorte()));
        taglia.setText(String.valueOf(prodotto.getTaglia()));

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
                    default:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
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

                    default:
                        iconaProdotto.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
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
}
