//problem 3: reverse an array in-place problem statement reverse the elements of an array without using a second auxiliary array by using the two-pointer approach. 

package Day08;

import java.util.Scanner;

public class Arrays3 {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int start = 0;
        int end = n - 1;

        while (start < end) {
            int temp = arr[start];
            arr[end] = temp;
            start++;
            end--;
        }
            System.out.println("Reversed array : ");
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
                System.out.println();
    }
}