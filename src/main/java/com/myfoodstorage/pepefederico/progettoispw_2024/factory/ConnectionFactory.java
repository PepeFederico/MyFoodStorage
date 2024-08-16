package com.myfoodstorage.pepefederico.progettoispw_2024.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Connection connection;
    private ConnectionFactory(){}

    static {
        try(InputStream input = new FileInputStream("risorseDB/credenzialiDB.properties")){
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl   = properties.getProperty("CONNECTION_URL");
            String user             = properties.getProperty("LOGIN_USER");
            String password         = properties.getProperty("LOGIN_PASS");

            connection = DriverManager.getConnection(connectionUrl,user,password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
