package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final String JDBC_SQLITE = "jdbc:sqlite:";
    private final String dbName;
    SQLiteDataSource sqLiteDataSource;
    private static DataSource dataSource = null;

    private DataSource(String dbName) {
        this.dbName = dbName;
        init();
    }

    private void init() {
        sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(JDBC_SQLITE + dbName);
    }

    public static DataSource getInstance(String dbName) {
        if (dataSource == null) {
            dataSource = new DataSource(dbName);
        }

        return dataSource;
    }

    public Connection getConnection() {
        try {
            return sqLiteDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
