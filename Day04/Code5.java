//5. Write a function that takes in age as input and returns if that person is eligible to vote or not. A person of age > 18 is eligible to vote. 

package Day04;
import java.util.*;

public class Code5 {
    static boolean isEligible(int age) {

        if (age > 18) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        if (isEligible(age)) {
            System.out.println("Eligible to vote");
        } else {
            System.out.println("Not eligible to vote");
        }

        sc.close();
    }
}
