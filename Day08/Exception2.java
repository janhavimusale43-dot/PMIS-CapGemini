//Exception handling

package Day08;

class Exception2{
    public static void main(String[] args){

        try {
            int[] numbers = {10,20,30};
            System.out.println(numbers[5]);
            int res = 10/0;
            System.out.println(res);

        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid array index");
        } catch(ArithmeticException e) {
            System.out.println("You cannot divide by 0. ");
        }
    }
}



