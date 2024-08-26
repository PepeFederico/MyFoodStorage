package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ConnectionFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RecuperoInfoFornitoriDao {

    private final List<ProdottoFornito> prodottiForniti = new ArrayList<>();
    private String nomeFornitore;

    public void recuperoFornitori(String nomeAttivita){
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs;

        try(InputStream input = new FileInputStream("risorseDB/queryDatabase.properties")){
            Properties properties = new Properties();
            properties.load(input);

            PreparedStatement query = connection.prepareStatement(properties.getProperty("QUERY_CONTRATTI_FORNITURA"));
            query.setString(1, nomeAttivita);
            rs = query.executeQuery();

            if(rs.next()){
                nomeFornitore = rs.getString("Fornitore");
                do{
                    prodottiForniti.add(ProdottoFactory.getInstance().getProdotto(
                            rs.getString("Fornitore"),
                            rs.getString("contattoFornitore"),
                            rs.getString("nome"),
                            TipoAnimale.valueOf(rs.getString("TipoAnimale")),
                            rs.getInt("taglia"),
                            rs.getDouble("costo")));
                }while(rs.next());
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ProdottoFornito> getProdotti() {
        return prodottiForniti;
    }

    public String getNomeFornitore() {
        return nomeFornitore;
    }
}
