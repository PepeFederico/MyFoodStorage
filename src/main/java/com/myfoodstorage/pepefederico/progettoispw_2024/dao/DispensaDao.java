package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.FoodStorageNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ConnectionFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.DispensaFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Dispensa;

public class DispensaDao {
    private final ArrayList<Dispensa> dispensa = new ArrayList<>();

    public void recuperoDispensa(String nomeAttivita) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs;

        try(
            InputStream input = new FileInputStream("risorseDB/queryDatabase.properties")
        ){
            Properties properties = new Properties();
            properties.load(input);

            PreparedStatement query = connection.prepareStatement(properties.getProperty("QUERY_CATEGORIE_DISPENSA"));
            query.setString(1,nomeAttivita);
            rs = query.executeQuery();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        if(rs.next()){
            do{
                dispensa.add(DispensaFactory.getInstance().getDispensa(rs.getString("Dispensa"), nomeAttivita, rs.getInt("numeroBoxCategorie")));
            }while (rs.next());
        }else{
            throw new FoodStorageNotFoundException("Ops, come è vuota la tua dispensa. Prova ad inserire una nuova dispensa!");
        }

    }

    public ArrayList<Dispensa> getDispensa() {
        return dispensa;
    }
}
