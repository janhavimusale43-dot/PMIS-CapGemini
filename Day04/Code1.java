//1. Enter 3 numbers from the user & make a function to print their average. 

package Day04;
import java.util.*;
public class Code1 {
    static void average(int a, int b, int c) {
        double avg = (a + b + c) / 3.0;
        System.out.println("Average = " + avg);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int a = sc.nextInt();

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.print("Enter third number: ");
        int c = sc.nextInt();

        average(a, b, c);

        sc.close();
    }
}
