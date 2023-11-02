package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;
    private final Connection connection;

    private DatabaseManager() {
        try {
            DatabaseConfig config = DatabaseConfig.getInstance();
            this.connection = DriverManager.getConnection(config.buildUrl(), config.getDatabaseUser(), config.getDatabasePassword());
        }
        catch (SQLException e) {
            System.out.println("Error while connecting to database.");
            throw new RuntimeException(e);
        }
    }

    private DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static DatabaseManager getInstance(Connection connection) {
        if (instance == null) {
            instance = new DatabaseManager(connection);
        }
        return instance;
    }
}
