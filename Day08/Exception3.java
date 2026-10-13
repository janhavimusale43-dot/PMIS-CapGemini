//Exception Handling

package Day08;

class Exception3{
    public static void main(String[] args){

        try {
            int res = 10/5;
            System.err.println(res);
        } catch(ArithmeticException e) {
            System.out.println("cannot divide by 0. ");
        } finally {
            System.out.println("Finally block executed. ");
        }
    }
}


