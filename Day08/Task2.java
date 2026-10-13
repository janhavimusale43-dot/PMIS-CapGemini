//Problem 2: Count character types in a password Task: Given a password string, count how many uppercase letters, lowercase letters, digits, and special characters it contains. Key concept: Built-in static utility methods of Character. 

package Day08;

public class Task2 {
    public static void main(String[] args){
        String text = "Pass@12345_Nashik";
        int upper = 0, lower = 0, digits = 0, special = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upper++;
            } else if (Character.isLowerCase(ch)) {
                lower++;
            } else if (Character.isDigit(ch)) {
                digits++;
            } else {
                special++;
            }
        }
            System.out.println("UpperCase: " + upper);
            System.out.println("LowerCase: " + lower);
            System.out.println("Digits: " + digits);
            System.out.println("Special: " + special);
    }
}
