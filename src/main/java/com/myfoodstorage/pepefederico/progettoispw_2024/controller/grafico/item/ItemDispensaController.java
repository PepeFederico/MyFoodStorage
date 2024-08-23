package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.item;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.DispensaBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemDispensaController {
    @FXML
    private Label nomeDispensa;
    @FXML
    private ImageView iconaDispensa;
    private DispensaBean dispensaBean = new DispensaBean();

    public void setItemDispensa(DispensaBean dispensa){
        nomeDispensa.setText(dispensa.getNomeDispensa());
        switch (dispensa.getNomeDispensa()){
            case "Frigorifero":
                iconaDispensa.setImage(new Image(String.valueOf(getClass().getResource("/image/imageDispensa/fridge.png"))));
            break;
            case "Congelatore":
                iconaDispensa.setImage(new Image(String.valueOf(getClass().getResource("/image/imageDispensa/freezer.png"))));
            break;
            default:
                iconaDispensa.setImage(new Image(String.valueOf(getClass().getResource("/image/diet.png"))));
        }
    }

    public void richiestaCategorie() {
        dispensaBean.setNomeDispensa(nomeDispensa.getText());
        ClientController.getInstance().setDispensaBean(dispensaBean);
        ClientController.getInstance().setClientParentCategoria();
    }
}
