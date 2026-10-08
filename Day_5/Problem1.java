package Oops;

class CoffeeWallet {

    String name;
    double balance;

    CoffeeWallet(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void addFunds(double amount) {
        balance += amount;
        System.out.println("Added: ₹" + amount);
        System.out.println("Current Balance: ₹" + balance);
    }

    void purchase(double amount) {

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Purchase successful: ₹" + amount);
            System.out.println("Remaining Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds!");
            System.out.println("Current Balance: ₹" + balance);
        }
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class Problem1 {

    public static void main(String[] args) {
        CoffeeWallet wallet = new CoffeeWallet("Janhavi", 500);
        wallet.display();
        System.out.println();
        wallet.addFunds(200);
        System.out.println();
        wallet.purchase(150);
        System.out.println();
        wallet.purchase(800);
    }
}