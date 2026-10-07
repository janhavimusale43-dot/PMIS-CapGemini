//2. Write a function to print the sum of all odd numbers from 1 to n. 

package Day04;
import java.util.*;

public class Code2 {
    static void sumOdd(int n) {

        int sum = 0;

        for (int i = 1; i <= n; i++) {

            if (i % 2 != 0) {
                sum = sum + i;
            }
        }

        System.out.println("Sum of odd numbers = " + sum);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        sumOdd(n);

        sc.close();
    }
}
