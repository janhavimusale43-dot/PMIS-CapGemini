package banking;
public class ConnectionTest {
    public static void main(String[] args) {
        try(var c=DBConnection.getConnection()) {
            System.out.println("MySQL connected successfully! Database: "+c.getCatalog());
        } catch(Exception e) {
            System.out.println("Connection failed: "+e.getMessage());
        }
    }
}
