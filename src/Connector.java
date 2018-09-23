import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    public static final String CONFIG_PROPERTIES = "conf.properties";

    private static Connection hiveConnection = null;
    private static Connection sqlConnection=null;
    private static Connector connector=null;

    private Connector(){}
    public static Connector getInstance()
    {
        if(connector==null)
            connector= new Connector();
        return connector;
    }


    public  Connection getSQLConnection() {
        Properties properties = loadProperties();

        if (this.sqlConnection == null) {
            this.sqlConnection = getConnection(properties.getProperty("DRIVER_NAME"), properties.getProperty("CONNECTION_STRING"),
                    properties.getProperty("USER"), properties.getProperty("PASS"));
        }
        return sqlConnection;
    }

    public  Connection getHiveConnection() {
        Properties properties = loadProperties();
        if (this.hiveConnection == null) {
            this.hiveConnection = getConnection(properties.getProperty("hive.driver"), properties.getProperty("hive.server"),
                    properties.getProperty("hive.server.name"), properties.getProperty("hive.server.password"));
        }
        return hiveConnection;
    }


    public static Connection getConnection(String driver, String server, String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://salma:3306/shopping", "nifi-cluster", "root");


        } catch (SQLException e) {

            e.printStackTrace();
        }
        return connection;
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = Connector.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}

