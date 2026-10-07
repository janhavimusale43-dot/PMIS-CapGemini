//4. Write a function that takes in the radius as input and returns the circumference of a circle. 

package Day04;
import java.util.*;

public class Code4 {
    static double circumference(double radius) {

        return 2 * Math.PI * radius;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius: ");
        double radius = sc.nextDouble();

        double result = circumference(radius);

        System.out.println("Circumference = " + result);

        sc.close();
    }
}
