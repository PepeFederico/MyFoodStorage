package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ProductNotFoundException;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ConnectionFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ProdottoDao {
    private final ArrayList<Prodotto> prodotti = new ArrayList<>();

    public void recuperoProdotti(String nomeAttivita, String nomeCategoria, String nomeDispensa) throws ProductNotFoundException {
        ResultSet rs = getResultSet(nomeAttivita, nomeCategoria, nomeDispensa);
        try {
            if(rs.next()){
                do{
                    prodotti.add(ProdottoFactory.getInstance().getProdotto(
                                    rs.getString("nome"),
                                    rs.getString("numLotto"),
                                    rs.getDate("scadenza"),
                                    rs.getInt("taglia"),
                                    rs.getInt("numeroScorte"),
                                    rs.getDouble("costo"),
                                    TipoAnimale.valueOf(rs.getString("TipoAnimale"))));
                }while(rs.next());
            }else{
                throw new ProductNotFoundException("Ops, come è vuota la tua dispensa. Inserisci qualche prodotto");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ResultSet getResultSet(String nomeAttivita, String nomeCategoria, String nomeDispensa) {
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs;

        try(InputStream input = new FileInputStream("risorseDB/queryDatabase.properties")){
            Properties properties = new Properties();
            properties.load(input);

            PreparedStatement query = connection.prepareStatement(properties.getProperty("QUERY_PRODOTTI"));
            query.setString(1, nomeAttivita);
            query.setString(2, nomeDispensa);
            query.setString(3, nomeCategoria);
            rs = query.executeQuery();

        }catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }
}
