package database;

import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private static DatabaseConfig instance;
    private final String configRessourcePath;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;
    private String databaseDriver;
    private String databaseHost;
    private String databasePort;

    private DatabaseConfig() {
        this("database.properties");
    }

    private DatabaseConfig(String configRessourcePath) {
        this.configRessourcePath = configRessourcePath;
    }

    private void load() throws IOException {
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemResourceAsStream(this.configRessourcePath));

        this.databaseName = prop.getProperty("database.name");
        this.databaseUser = prop.getProperty("database.username");
        this.databasePassword = prop.getProperty("database.password");
        this.databaseDriver = prop.getProperty("database.driver");
        this.databaseHost = prop.getProperty("database.host");
        this.databasePort = prop.getProperty("database.port");
    }

    public String buildUrl() {
        return "jdbc:" + this.databaseDriver + "://" + this.databaseHost + ":" + this.databasePort + "/" + this.databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
            try {
                instance.load();
            } catch (IOException e) {
                return null;
            }
        }
        return instance;
    }
}
