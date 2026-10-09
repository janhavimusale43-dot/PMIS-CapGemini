package Oops;

class Employee {

    // Private fields
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;

        // Salary cannot be negative
        if (salary >= 0) {
            this.salary = salary;
        } else {
            this.salary = 0.0;
        }
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative.");
        }
    }

    // Give raise
    public void giveRaise(double percent) {
        salary = salary + (salary * percent / 100);
    }
}

public class Problem3 {

    public static void main(String[] args) {

        // Create employee
        Employee emp = new Employee(101, "Alice", 50000.0);

        // Apply 8% raise
        emp.giveRaise(8);

        // Print updated salary
        System.out.println("Employee ID: " + emp.getId());
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Updated Salary: " + emp.getSalary());

        // Try setting negative salary
        emp.setSalary(-5000);
    }
}
