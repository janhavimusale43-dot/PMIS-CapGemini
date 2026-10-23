package banking;
public class Account {
    private int accountID, customerID; private String type; private double balance;
    public Account(int accountID,int customerID,String type,double balance){
        this.accountID=accountID;this.customerID=customerID;this.type=type;this.balance=balance;
    }
    public int getAccountID(){return accountID;} public void setAccountID(int v){accountID=v;}
    public int getCustomerID(){return customerID;} public void setCustomerID(int v){customerID=v;}
    public String getType(){return type;} public void setType(String v){type=v;}
    public double getBalance(){return balance;} public void setBalance(double v){balance=v;}
    public String toString(){return "Account ID: "+accountID+", Customer ID: "+customerID+", Type: "+type+", Balance: "+String.format("%.2f",balance);}
}
