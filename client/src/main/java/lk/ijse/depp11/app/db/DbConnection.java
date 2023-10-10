package lk.ijse.depp11.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static DbConnection dbConnection;
    private final Connection connection;
    private DbConnection(){
        try {
            // Map<Key, Value>
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/application.properties"));

            String url = properties.getProperty("client.db.url");
            String username = properties.getProperty("client.db.username");
            String password = properties.getProperty("client.db.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static DbConnection getInstance(){
        return (dbConnection == null) ? dbConnection = new DbConnection(): dbConnection;
    }
}