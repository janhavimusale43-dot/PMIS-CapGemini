package Day15;

import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;
import java.util.Scanner;

public class DateExplorer {

    private LocalDate date;

    // Constructor
    public DateExplorer(LocalDate date) {
        this.date = date;
    }

    // Display month
    public void displayMonth() {
        System.out.println("Month: " + date.getMonth());
    }

    // Display year
    public void displayYear() {
        System.out.println("Year: " + date.getYear());
    }

    // Display day of the week
    public void displayDayOfWeek() {
        System.out.println("Day of Week: " + date.getDayOfWeek());
    }

    // Look back by a specified number of months
    public void lookBackMonths(int months) {
        LocalDate pastDate = date.minusMonths(months);
        System.out.println("Date " + months + " months ago: " + pastDate);
    }

    // Check whether the year is a leap year
    public void checkLeapYear() {
        if (date.isLeapYear()) {
            System.out.println(date.getYear() + " is a leap year.");
        } else {
            System.out.println(date.getYear() + " is not a leap year.");
        }
    }

    // Display the menu
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Date Explorer Menu =====");
            System.out.println("1. Display Month");
            System.out.println("2. Display Year");
            System.out.println("3. Display Day of Week");
            System.out.println("4. Look Back by Months");
            System.out.println("5. Check Leap Year");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayMonth();
                    break;

                case 2:
                    displayYear();
                    break;

                case 3:
                    displayDayOfWeek();
                    break;

                case 4:
                    System.out.print("Enter number of months to look back: ");
                    int months = sc.nextInt();
                    lookBackMonths(months);
                    break;

                case 5:
                    checkLeapYear();
                    break;

                case 6:
                    System.out.println("Exiting Date Explorer. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = sc.nextLine();

        LocalDate date = LocalDate.parse(input);

        DateExplorer explorer = new DateExplorer(date);
        explorer.displayMenu();

        sc.close();
    }
}