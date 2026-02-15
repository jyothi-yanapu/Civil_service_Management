package com.jyothiyanapu.csms.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnectionManager {
    private static volatile DatabaseConnectionManager instance;

    private final String url;
    private final String username;
    private final String password;

    private DatabaseConnectionManager() {
        try {
            Properties props = new Properties();
            InputStream input = DatabaseConnectionManager.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (input == null) {
                throw new RuntimeException("db.properties file not found");
            }

            props.load(input);
            this.url = props.getProperty("db.url");
            this.username = props.getProperty("db.username");
            this.password = props.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB properties", e);
        }
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
