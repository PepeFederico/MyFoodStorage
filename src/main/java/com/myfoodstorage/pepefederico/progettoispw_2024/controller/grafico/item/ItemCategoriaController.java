package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.CategoriaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemCategoriaController {
    @FXML
    private Label nomeCategoria;
    @FXML
    private ImageView iconaCategoria;
    private final CategoriaBean categoriaBean = new CategoriaBean();

    public void setItemCategoria(CategoriaBean categoria){
        nomeCategoria.setText(categoria.getNomeCategoria());
        switch (categoria.getNomeCategoria()){
            case "Carne":
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/imageCategoria/meat.png"))));
            break;
            case "Pesce":
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/imageCategoria/fish.png"))));
            break;
            case "Verdura":
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/imageCategoria/vegetable.png"))));
            break;
            case "Frutta":
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/imageCategoria/fruits.png"))));
            break;
            case "Formaggio":
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/imageCategoria/cheese.png"))));
            break;
            default:
                iconaCategoria.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
        }
    }

    public void richiestaProdotti(){
        categoriaBean.setNomeCategoria(nomeCategoria.getText());
        ClientController.getInstance().setCategoriaBean(categoriaBean);
        ClientController.getInstance().setClientParentProdotti();
    }
}
