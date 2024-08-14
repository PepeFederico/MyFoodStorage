package com.myfoodstorage.pepefederico.progettoispw_2024.factory;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ViewFactory {
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(ViewFactory.class.getName());
    private AnchorPane dashboardView;
    private AnchorPane categoriaView;
    private AnchorPane profiloView;
    private AnchorPane prodottiView;
    private AnchorPane contrattiView;
    private final StringProperty clientSelectedMenuItem;
    public ViewFactory(){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView(){
        if (dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/Dashboard.fxml")).load();
            }catch (Exception e){
                logger.log(Level.SEVERE, ACTION, e);
            }
        }
        return dashboardView;
    }

    public AnchorPane getCategoriaView(){
        if (categoriaView == null){
            try{
                categoriaView = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/categoria.fxml")).load();
            }catch (Exception e){
                logger.log(Level.SEVERE, ACTION, e);
            }
        }
        return categoriaView;
    }

    public AnchorPane getProdottiView(){
        if (prodottiView == null){
            try{
                prodottiView = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/prodotti.fxml")).load();
            }catch (Exception e){
                logger.log(Level.SEVERE, ACTION, e);
            }
        }
        return prodottiView;
    }

    public AnchorPane getProfiloView(){
        if (profiloView == null){
            try{
                profiloView = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/profilo.fxml")).load();
            }catch (Exception e){
                logger.log(Level.SEVERE, ACTION, e);
            }
        }
        return profiloView;
    }

    public AnchorPane getContrattiView(){
        if (contrattiView == null){
            try{
                contrattiView = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/contratti.fxml")).load();
            }catch (Exception e){
                logger.log(Level.SEVERE, ACTION, e);
            }
        }
        return contrattiView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/login.fxml"));
        createStage(loader);
    }

    public void showRistoratoreWindow(SessioneBean sessioneUtente){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/myfoodstorage/pepefederico/progettoispw_2024/utenteRistoratore/utenteRistoratore.fxml"));
        ClientController.getInstance().setSessioneUtente(sessioneUtente);
        loader.setController(ClientController.getInstance());
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            logger.log(Level.SEVERE, ACTION, e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("MyFoodStorage");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/IconaMFS.png"))));
        stage.show();
    }

    public void closeApplication(){
        this.prodottiView = null;
        this.profiloView = null;
        this.contrattiView = null;
        this.dashboardView = null;
        this.categoriaView = null;
    }

    public void closeStage(Stage stage){
        stage.close();
    }
    public void setCategoriaView() {
        this.categoriaView = null;
    }
    public void setProdottiView(){
        this.prodottiView = null;
    }
}
