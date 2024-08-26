package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetIconProdotti {

    public void setIconProdotto(ImageView icona, String string, TipoAnimale tipoAnimale) {
        switch (string){
            case "Carne":
                switch (tipoAnimale) {
                    case MANZO:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/cow.png"))));
                        break;
                    case VITELLA:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/dinosaur.png"))));
                        break;
                    case MAIALE:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/pig.png"))));
                        break;
                    case POLLO:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/hen.png"))));
                        break;
                    case TACCHINO:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/turkey.png"))));
                        break;
                    default:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/beef_generic.png"))));
                }
                break;

            case "Pesce":
                switch (tipoAnimale) {
                    case SALMONE:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/fish.png"))));
                        break;

                    case TONNO:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/tuna.png"))));
                        break;

                    default:
                        icona.setImage(new Image(String.valueOf(getClass().getResource("/image/fish_generic.png"))));
                }
                break;

            case "Verdura":
                icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/vegetable.png"))));
                break;

            case "Frutta":
                icona.setImage(new Image(String.valueOf(getClass().getResource("/image/imageProdotti/fruits.png"))));
                break;

            default:
                icona.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
        }
    }
}
