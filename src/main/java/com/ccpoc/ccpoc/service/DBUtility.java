package com.ccpoc.ccpoc.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtility {

    private static Connection connection = null;

    public static Connection getConnection() throws URISyntaxException {
        if (connection != null) {
            return connection;
        } else {
            try {

                String dbu = null, driver = "", url = "", username = "", password = "";
                Properties prop = new Properties();
                InputStream input = null;
                dbu = System.getenv("DATABASE_URL");

                if (dbu == null) {
                     input = DBUtility.class.getClassLoader().getResourceAsStream("application.properties");
                    //input = new FileInputStream("application.properties");
                    prop.load(input);
                    driver = prop.getProperty("driver");
                    url = prop.getProperty("url");
                    username = prop.getProperty("user");
                    password = prop.getProperty("password");
                } else {
                    URI dbUri = new URI(dbu);
                    driver = "org.postgresql.Driver";
                    username = dbUri.getUserInfo().split(":")[0];
                    password = dbUri.getUserInfo().split(":")[1];
                    url = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + "" + dbUri.getPath() + "?user=" + username + "&password=" + password;
                    System.out.println("JDBC Url :" + url);
                }

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
