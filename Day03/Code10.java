package Day03;

import java.util.Scanner;

public class Code10 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");

        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.print("Enter side: ");
                double side = sc.nextDouble();
                System.out.println("Area of Square = " + (side * side));
                break;

            case 2:
                System.out.print("Enter length: ");
                double length = sc.nextDouble();
                System.out.print("Enter width: ");
                double width = sc.nextDouble();
                System.out.println("Area of Rectangle = " + (length * width));
                break;

            case 3:
                System.out.print("Enter base: ");
                double base = sc.nextDouble();
                System.out.print("Enter height: ");
                double height = sc.nextDouble();
                System.out.println("Area of Triangle = " + (0.5 * base * height));
                break;

            default:
                System.out.println("Invalid choice");
        }
            sc.close();
    }
}
