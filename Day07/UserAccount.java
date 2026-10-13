package Day07;

class UserAccount {
    private String username;
    private static int totalUsers = 0;

    // Parameterized constructor
    UserAccount(String username) {
        this.username = username;
        totalUsers++;
    }

    // Getter method
    String getUsername() {
        return username;
    }

    // Static method
    static int getTotalUsers() {
        return totalUsers;
    }
}