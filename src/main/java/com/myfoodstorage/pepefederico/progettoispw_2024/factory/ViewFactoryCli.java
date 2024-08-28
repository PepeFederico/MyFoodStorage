package com.myfoodstorage.pepefederico.progettoispw_2024.factory;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.SessioneBean;
import com.myfoodstorage.pepefederico.progettoispw_2024.controller.grafico.ClientController;
import com.myfoodstorage.pepefederico.progettoispw_2024.secondagui.LoginController;

public class ViewFactoryCli {

    public void loginUser(){
        LoginController loginController = new LoginController();
        loginController.loginUser();
    }

    public void showRistoratoreWindowCLI(SessioneBean sessione){
        ClientController.getInstance().setSessioneUtente(sessione);
        ClientController.getInstance().inizialize();
    }
}
