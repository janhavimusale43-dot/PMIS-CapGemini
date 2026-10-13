//throw

package Day08;

import java.util.*;

class Exception4 {
    public static void main(String[] args){
        System.out.print("Enter age: ");
        Scanner sc = new Scanner(System.in);

        int age = sc.nextInt();

        if(age < 0){
            throw new IllegalArgumentException ("Age cannot be negative");     
        }
            System.out.println("Age :" + age );
    }
}
