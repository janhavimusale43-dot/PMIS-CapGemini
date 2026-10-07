//8. Two numbers are entered by the user, x and n. Write a function to find the value of one number raised to the power of another i.e. 𝑥 𝑛 . 

package Day04;
import java.util.*;

public class Code8 {
    static int power(int x, int n) {

        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * x;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = sc.nextInt();

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int result = power(x, n);

        System.out.println(x + " raised to power " + n + " = " + result);

        sc.close();
    }
}
