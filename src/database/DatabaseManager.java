package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;
    private final Connection connection;

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe, singleton.
     * Ira chercher les informations de connexion dans le fichier de configuration.
     *
     * @see DatabaseConfig
     */
    private DatabaseManager() {
        try {
            DatabaseConfig config = DatabaseConfig.getInstance();
            this.connection = DriverManager.getConnection(config.buildUrl(), config.getDatabaseUser(), config.getDatabasePassword());
        } catch (SQLException e) {
            System.out.println("Error while connecting to database.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe, singleton.
     *
     * @param connection Connection à la base de données. Utilisé pour les tests.
     */
    private DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retourne la connexion à la base de données.
     *
     * @return Connection à la base de données.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Retourne l'instance de la classe.
     *
     * @return singleton de la classe.
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Retourne l'instance de la classe en passant une connexion à la base de données spécifique.
     *
     * @param connection Connection à la base de données. Utilisé pour les tests.
     * @return singleton de la classe.
     */
    public static DatabaseManager getInstance(Connection connection) {
        if (instance == null) {
            instance = new DatabaseManager(connection);
        }
        return instance;
    }
}
