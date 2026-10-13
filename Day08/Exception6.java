package Day08;

class Exception6 {
    public static void main(String[] args){
        int balance = 50000;
        int withdrawal = 70000;

        try{
            if(withdrawal > balance){
                throw new IllegalArgumentException("Insufficient balance ");
            }
            balance = withdrawal;
            System.out.println("Withdrawal was sucessful");
            System.out.println("Remaining balance:" + balance);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Program completed");
    }
}
