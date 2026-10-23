import java.sql.*;

public class sql_connect {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/JDBC";
        String username = "root";
        String password = "root";
        String query = "SELECT * FROM football";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver error: " + e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Connection Established Successfully !!");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("footballer_name");
                double salary = rs.getDouble("salary");

                System.out.println();
                System.out.println("======================");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Salary: " + salary);
            }

            System.out.println();
            System.out.println("Connection Closed Successfully!!!!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}