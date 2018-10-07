package main.utils;

import main.Connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Properties Interface to get properties from external properties file
 */
public class Properties {

    public static final String CONFIG_PROPERTIES = "conf.properties";

    private static java.util.Properties properties;

    /**
     * loads properties from properties file in the resources folder
     */
    public static void loadProperties() {
        java.util.Properties properties = new java.util.Properties();
        InputStream inputStream = Connector.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Properties.properties = properties;
    }

    /**
     * gets the property for the given key
      * @param key property name
     * @return property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
