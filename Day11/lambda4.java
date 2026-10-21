package Day11;

import java.util.function.Function;

public class lambda4 {
    public static void main(String[] args) {

        Function<Integer, Integer> square = n -> n * n;

        System.out.println("Square of 5: " + square.apply(5));
        System.out.println("Square of 10: " + square.apply(10));
        System.out.println("Square of 7: " + square.apply(7));
    }
}