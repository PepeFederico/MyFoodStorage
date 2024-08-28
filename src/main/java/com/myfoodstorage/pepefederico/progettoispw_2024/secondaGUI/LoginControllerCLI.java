package com.myfoodstorage.pepefederico.progettoispw_2024.secondaGUI;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.UtenteLoginBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.applicativo.LoginControllerA;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.UserNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControllerCLI {
    private boolean errore = false;
    private final Logger logger = Logger.getLogger(LoginControllerCLI.class.getName());

    public void loginUser() {
        UtenteLoginBean utente = new UtenteLoginBean();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {

            try {

                System.out.println("------Login View------");

                System.out.println("Username: ");
                String email = reader.readLine();
                System.out.println("Password: ");
                String password = reader.readLine();

                if ((!email.isEmpty() && !password.isEmpty())) {
                    if (utente.mailSyntaxCheck(email)) {
                        try {
                            utente.setEmail(email);
                            utente.setPassword(password);
                            LoginControllerA controller = new LoginControllerA(utente);
                            controller.autenticazioneUtenteCLI();

                        } catch (UserNotFoundException e) {
                            logger.log(Level.WARNING, e.getMessage());
                            setErrore();
                        }
                    } else {
                        logger.log(Level.WARNING, "Email non conforme. Riprova!!");
                        setErrore();
                    }
                } else {
                    logger.log(Level.WARNING, "Input non conforme. Controlla i campi");
                    setErrore();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }while(errore);
    }

    private void setErrore(){
        this.errore = true;
    }
}
