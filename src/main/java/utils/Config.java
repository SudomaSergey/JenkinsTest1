package utils;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
    protected static Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getResourceAsStream("/config.properties"));
        } 
        catch (IOException e) {
        }
    }

    protected static Properties getProperties () {
        return properties;
    }

    public static String getProperty (String name) {
        if (!properties.containsKey(name)) {
            throw new RuntimeException("Config option with name - " + name + "does not exists.");
        }
        return properties.getProperty(name);
    }
}