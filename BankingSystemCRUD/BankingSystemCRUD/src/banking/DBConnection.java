package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/banking_db";

    private static final String USER = "root";

    // Replace with your local MySQL password
    private static final String PASSWORD = "password";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }
}