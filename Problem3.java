
class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = (salary >= 0) ? salary : 0.0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative.");
        }
    }

    public void giveRaise(double percent) {
        salary = salary + (salary * percent / 100);
    }
}

public class Problem3 {
    public static void main(String[] args) {

        Employee emp = new Employee(101, "Alice", 50000.0);

        emp.giveRaise(8);

        System.out.println("Employee ID: " + emp.getId());
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Updated Salary: " + emp.getSalary());

        emp.setSalary(-5000);
    }
}