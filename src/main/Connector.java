package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.utils.Properties;

public class Connector {

    private static Connection sourceConnection = null;
    private static Connection destinationConnection = null;
    private static Connector connector=null;

    private Connector(){}
    public static Connector getInstance()
    {
        if(connector==null)
            connector= new Connector();
        return connector;
    }


    public  Connection getSourceConnection() {

        if (this.sourceConnection == null) {
            this.sourceConnection = getConnection(Properties.getProperty("source.driver"), Properties.getProperty("source.connection"),
                    Properties.getProperty("source.server.name"), Properties.getProperty("source.server.password"));
        }
        return sourceConnection;
    }


    public  Connection getDestinationConnection() {
        if (this.destinationConnection == null) {
            this.destinationConnection = getConnection(Properties.getProperty("destination.driver"), Properties.getProperty("destination.connection"),
                    Properties.getProperty("destination.server.name"), Properties.getProperty("destination.server.password"));
        }
        return destinationConnection;
    }

    public static Connection getConnection(String driver, String server, String userName, String password) {
        try {
//            System.out.println(driver.substring(1, driver.length() - 1));
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(server, userName, password);


        } catch (SQLException e) {

            e.printStackTrace();
        }
        return connection;
    }
}

