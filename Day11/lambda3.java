package Day11;

import java.util.*;
import java.util.function.Function;
public class lambda3 {
    public static void main(String[] args){
        Function<String, Integer> getLength = name -> name.length();
        System.out.println(getLength.apply("java"));
        System.out.println(getLength.apply("Janhavi"));
    }
}
