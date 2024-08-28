package com.myfoodstorage.pepefederico.progettoispw_2024;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Model;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.RejectedExecutionException;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Benvenuto in MyFoodStorage, la prima dispensa digitale !");

            System.out.println("Quale GUI vuoi caricare ? 1 --> JavaFx, 2 --> CLI");
            int guiSelector = Integer.parseInt(reader.readLine());

            if (guiSelector == 1) {
                Model.getInstance().getViewFactory().showLoginWindow();
            } else if (guiSelector == 2) {
                Model.getInstance().getViewFactoryCLI().loginUser();
                System.exit(0);
            }
        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }
    }
}
