//3. Write a function which takes in 2 numbers and returns the greater of those two. 

package Day04;
import java.util.*;

public class Code3 {
    static int greater(int a, int b) {

        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int a = sc.nextInt();

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        int result = greater(a, b);

        System.out.println("Greater number = " + result);

        sc.close();
    }
}
