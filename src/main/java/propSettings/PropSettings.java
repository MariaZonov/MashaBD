package propSettings;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropSettings implements IPropSettings {
    @Override
    public Map<String, String> getPropSettings() {
        Properties properties = new Properties();
        Map<String, String> result = new HashMap<>();
        try {
            properties.load(new FileInputStream((System.getProperty("user.dir") + "/src/main/resources/db.properties")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            result.put(key, properties.getProperty(key));
        }
        return result;
    }
}