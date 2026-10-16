package Day10;

import java.util.*;

class generic5 {
    public static void printList(List<?> list){
        for (Object o : list){
            System.out.println(o);
        }
    }
    public static void main(String[] args){
        List<String> names = Arrays.asList("Janhavi" , "Rakhi");
        List<Integer> nums = Arrays.asList(10,20);
        List<Double> prices = Arrays.asList(99.5,99.6);

        System.out.println("name: ");
        printList(names);

        System.out.println("\nNumbers: ");
        printList(nums);

        System.out.println("\nprices: ");
        printList(prices);
    }
}
