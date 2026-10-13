package Day07;

public class StaticDemo1 {
    public static void main(String[] args) {

        // Display initial user count
        System.out.println("Initial User Count: " + UserAccount.getTotalUsers());

        // Create three user accounts
        UserAccount user1 = new UserAccount("Aarav");
        UserAccount user2 = new UserAccount("Priya");
        UserAccount user3 = new UserAccount("Rohan");

        // Display usernames
        System.out.println("Username: " + user1.getUsername());
        System.out.println("Username: " + user2.getUsername());
        System.out.println("Username: " + user3.getUsername());

        // Display total registered users
        System.out.println("Total Registered Users: " + UserAccount.getTotalUsers());
    }
}