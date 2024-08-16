package com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico;

import java.net.URL;
import java.util.ResourceBundle;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.fxml.FXML;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.LoginControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteLoginBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.UserNotFoundException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private TextField emailInputField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordInputField;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(actionEvent -> onLogin());
    }
    private void onLogin(){
        UtenteLoginBean utente = new UtenteLoginBean();
        String email = emailInputField.getText();
        String password = passwordInputField.getText();

        if((!email.isEmpty() && !password.isEmpty())){
            if(utente.mailSyntaxCheck(email)){
                try {
                    utente.setEmail(email);
                    utente.setPassword(password);
                    LoginControllerA controller = new LoginControllerA(utente);
                    controller.autenticazioneUtente();

                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                }catch (UserNotFoundException e) {
                    errorLabel.setVisible(true);
                    errorLabel.setText(e.getMessage());
                }
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("Errore : Email non conforme. Riprova!!");
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("Errore : Input non conforme. Controlla i campi");
        }
    }
}




