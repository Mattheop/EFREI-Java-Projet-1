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

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe
     * param configRessourcePath part défaut : database.properties
     */
    private DatabaseConfig() {
        this("database.properties");
    }

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe
     *
     * @param configRessourcePath chemin vers le fichier de configuration
     */
    private DatabaseConfig(String configRessourcePath) {
        this.configRessourcePath = configRessourcePath;
    }

    /**
     * Permet de charger les paramètres de connexion à la base de données
     * depuis le fichier de configuration
     */
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

    /**
     * Permet de construire l'url de connexion à la base de données
     *
     * @return url de connexion à la base de données
     */
    public String buildUrl() {
        return "jdbc:" + this.databaseDriver + "://" + this.databaseHost + ":" + this.databasePort + "/" + this.databaseName;
    }

    /**
     * Permet de récupérer le nom d'utilisateur de la base de données
     *
     * @return nom d'utilisateur de la base de données
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Permet de récupérer le mot de passe de la base de données
     *
     * @return mot de passe de la base de données
     */
    public String getDatabasePassword() {
        return databasePassword;
    }

    /**
     * Permet de récupérer une instance de la classe DatabaseConfig
     * Singleton
     *
     * @return instance de la classe DatabaseConfig
     */
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
