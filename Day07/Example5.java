package Day07;

class Bank{
    void calculateInterest(){
        System.out.println("Base Interest Calculation");
    }
}

class Branch extends Bank{
    void calculateInterest(){
        System.out.println("custom Interest");
    }
}
