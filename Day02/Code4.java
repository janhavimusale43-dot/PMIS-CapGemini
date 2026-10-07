package Day02;

import java.util.Scanner;

public class Code4 {

    public static void main(String[] args) {
        System.out.println("Enter two numbers:");
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println(a);
        System.out.println(b);

        sc.close();
    }
}