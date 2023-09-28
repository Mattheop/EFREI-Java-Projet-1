package database;

public class DatabaseConfig {

    public static final String DATABASE_NAME = "APTN61_BD";
    public static final String DATABASE_USER = "adm";
    public static final String DATABASE_PASSWORD = "adm";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;

    public static final String SQL_ARCHIVE_PROGRAMERS = """
            SELECT * FROM personnes WHERE type = 'developer'""";


}
