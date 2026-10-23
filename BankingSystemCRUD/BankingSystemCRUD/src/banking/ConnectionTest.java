
package banking;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("MySQL connected successfully!");
            System.out.println("Database: " + connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
