//Problem 3: Filter odd numbers from an ArrayList Task: Create an ArrayList<Integer> with mixed numbers, remove all odd numbers, and print the remaining list. Key concept: Autoboxing, unboxing, and collections requiring wrapper types instead of primitives. 

package Day08;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(12);
        numbers.add(7);
        numbers.add(30);
        numbers.add(44);
        numbers.add(50);
        numbers.add(60);

        List<Integer> evens = new ArrayList<>();
        for(Integer num : numbers) {
            if (num % 2 == 0){
                evens.add(num);
            }
        }
            System.out.println("Even numbers: " + evens);
    }
}
