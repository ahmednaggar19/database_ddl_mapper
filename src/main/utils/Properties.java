package main.utils;

import main.Connector;

import java.io.IOException;
import java.io.InputStream;

public class Properties {

    public static final String CONFIG_PROPERTIES = "conf.properties";

    private static java.util.Properties properties;

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

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
