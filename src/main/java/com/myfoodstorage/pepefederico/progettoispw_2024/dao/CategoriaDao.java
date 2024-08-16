package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ConnectionFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.CategoriaFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Categoria;
import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.CategoryNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class CategoriaDao {
    private final ArrayList<Categoria> categoria = new ArrayList<>();

    public void recuperoCategorie(String nomeAttivita, String nomeDispensa) throws CategoryNotFoundException{
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs;

        try(InputStream input = new FileInputStream("risorseDB/queryDatabase.properties")){
            Properties properties = new Properties();
            properties.load(input);

            PreparedStatement query = connection.prepareStatement(properties.getProperty("QUERY_NOME_CATEGORIE"));
            query.setString(1,nomeAttivita);
            query.setString(2, nomeDispensa);
            rs = query.executeQuery();

            if(rs.next()){
                do{
                    categoria.add(CategoriaFactory.getInstance().getCategoria(rs.getString("nomeCategoria"), nomeAttivita, nomeDispensa));
                }while(rs.next());
            }else{
                throw new CategoryNotFoundException("La dispensa non presenta alcuna Categoria di Prodotti. Aggiungi una categoria");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public ArrayList<Categoria> getCategoria() {
        return categoria;
    }
}
