package com.jyothiyanapu.csms.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private DBConnection() {
    }

    // Backward-compatible helper that delegates to the singleton manager.
    // Existing DAO code can keep calling DBConnection.getConnection().
    // New code should use DatabaseConnectionManager directly.

    public static Connection getConnection() throws SQLException {
        return DatabaseConnectionManager.getInstance().getConnection();
    }
}
