package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static final String JDBC_MYSQL = "jdbc:mysql:";
    public static final String DB_NAME = "Mp3Master.db";

    public static Connection getConnection() throws Exception {

        try(Connection conn = DriverManager.getConnection(JDBC_MYSQL + DB_NAME)) {
            System.out.println("Connection to SQLite has been established");
            return conn;
        } catch (Exception e) {
            throw new Exception("Error in establishing connection to the database");
        }
    }
}
