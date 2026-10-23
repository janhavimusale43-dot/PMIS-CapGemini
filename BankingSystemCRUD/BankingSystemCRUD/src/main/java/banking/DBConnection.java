package banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/banking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Change this
    private DBConnection() {}
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
