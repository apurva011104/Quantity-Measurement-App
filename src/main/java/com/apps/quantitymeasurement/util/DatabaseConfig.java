package com.apps.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;

import com.apps.quantitymeasurement.exception.DatabaseException;

public class DatabaseConfig {

    private static Connection connection;
    private static boolean h2Started = false;

    private DatabaseConfig(){
    }

    private static void createH2WebServer() throws SQLException{
        if(!h2Started){
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("H2 Console started at http://localhost:8082");
            h2Started = true;
        }
    }

    public static Connection getConnectionInstance() throws DatabaseException{ 
        try{
            if(connection == null || connection.isClosed()){
                createH2WebServer();
                Properties properties = new Properties();
                InputStream input = DatabaseConfig.class.getClassLoader()
                                            .getResourceAsStream("application.properties");
                InputStream schemaStream = DatabaseConfig.class.getClassLoader()
                                            .getResourceAsStream("database/schema.sql");
                
                if(input == null){
                    throw new DatabaseException("application.properties not found");
                }
                properties.load(input);
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String driver = properties.getProperty("db.driver");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);

                System.out.println("Database connection established successfully");

                if (schemaStream != null) {
                    String sql = new String(schemaStream.readAllBytes());
                    connection.createStatement().execute(sql);
                }

                input.close();
                schemaStream.close();
            }
        } 
        catch ( SQLException | ClassNotFoundException | IOException e) {
            throw new DatabaseException("Error while establishing connection.", e);
        }
            
        return connection;
    }

}
