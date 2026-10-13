//Problem Statement: Write a program that takes two integer inputs from the user and calculates their division. If the second number is zero, handle the resulting ArithmeticException gracefully and print an error message instead of crashing.

package Day08;

import java.util.Scanner;

public class ProblemS1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the denominator:");

        int a = sc.nextInt();
        int b = sc.nextInt();

        try {
            int result = a / b;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } finally{
            System.out.println("Program executed ");
        }

        sc.close();
    }
}
