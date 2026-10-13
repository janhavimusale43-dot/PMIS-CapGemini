//Exception Handling

package Day08;

class Exception1{
    public static void main(String[] args){
        int a = 10;
        int b = 0;

        try {
            int rest = a/b;
            System.out.println(rest);
        } catch(ArithmeticException e) {
            System.out.println("You cannot divide by 0. ");
        }
    }
}


