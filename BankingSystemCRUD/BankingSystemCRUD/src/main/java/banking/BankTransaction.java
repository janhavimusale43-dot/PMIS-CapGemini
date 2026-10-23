package banking;
import java.time.LocalDateTime;
public class BankTransaction {
    private int transactionID, accountID; private String type; private double amount; private LocalDateTime timestamp;
    public BankTransaction(int accountID,String type,double amount){this.accountID=accountID;this.type=type;this.amount=amount;}
    public int getTransactionID(){return transactionID;} public void setTransactionID(int v){transactionID=v;}
    public int getAccountID(){return accountID;} public void setAccountID(int v){accountID=v;}
    public String getType(){return type;} public void setType(String v){type=v;}
    public double getAmount(){return amount;} public void setAmount(double v){amount=v;}
    public LocalDateTime getTimestamp(){return timestamp;} public void setTimestamp(LocalDateTime v){timestamp=v;}
    public String toString(){return "Transaction ID: "+transactionID+", Account ID: "+accountID+", Type: "+type+", Amount: "+amount+", Timestamp: "+timestamp;}
}
