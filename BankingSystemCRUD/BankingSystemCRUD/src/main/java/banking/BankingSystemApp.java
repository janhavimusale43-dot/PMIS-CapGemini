package banking;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BankingSystemApp {
    private static final Scanner sc=new Scanner(System.in);
    private static final BankingService service=new JdbcBankingService();
    public static void main(String[] args) {
        try(var c=DBConnection.getConnection()){System.out.println("Connected to banking_db.");}
        catch(SQLException e){System.out.println("MySQL connection failed: "+e.getMessage());return;}
        boolean running=true;
        while(running){
            System.out.println("\n=== BANKING SYSTEM ===");
            System.out.println("1. Add Customer\n2. Add Account\n3. Add Beneficiary\n4. Deposit/Withdrawal");
            System.out.println("5. Find Customer\n6. List Customer Accounts\n7. List Account Transactions\n8. List Customer Beneficiaries\n9.Update  \n10.Delete \n 11. Exit");
            try {
                int choice=integer("Choice: ");
                switch(choice){
                    case 1 -> addCustomer();
                    case 2 -> addAccount();
                    case 3 -> addBeneficiary();
                    case 4 -> transact();
                    case 5 -> {Customer c=service.findCustomerById(integer("Customer ID: "));System.out.println(c==null?"Customer not found.":c);}
                    case 6 -> {List<Account> a=service.getAccountsByCustomerId(integer("Customer ID: "));if(a.isEmpty())System.out.println("No accounts found.");else a.forEach(System.out::println);}
                    case 7 -> {List<BankTransaction> t=service.getTransactionsByAccountId(integer("Account ID: "));if(t.isEmpty())System.out.println("No transactions found.");else t.forEach(System.out::println);}
                    case 8 -> {List<Beneficiary> b=service.getBeneficiariesByCustomerId(integer("Customer ID: "));if(b.isEmpty())System.out.println("No beneficiaries found.");else b.forEach(System.out::println);}

                    case 9 -> updateCustomer();
                    case 10 -> deleteCustomer();
                    case 11 -> {running=false;System.out.println("Thank you!");}
                    default -> System.out.println("Choose a number from 1 to 11.");
                }
            } catch(SQLException|IllegalArgumentException e){System.out.println("Operation failed: "+e.getMessage());}
        }
    }
    private static int integer(String prompt){System.out.print(prompt);return Integer.parseInt(sc.nextLine().trim());}
    private static double amount(String prompt){System.out.print(prompt);return Double.parseDouble(sc.nextLine().trim());}
    private static void addCustomer() throws SQLException {
        int id=integer("Customer ID: ");System.out.print("Name: ");String name=sc.nextLine();
        System.out.print("Address: ");String address=sc.nextLine();System.out.print("Contact: ");String contact=sc.nextLine();
        service.addCustomer(new Customer(id,name,address,contact));System.out.println("Customer added.");
    }
    private static void addAccount() throws SQLException {
        int id=integer("Account ID: "),cid=integer("Existing customer ID: ");System.out.print("Type (Saving/Current): ");String type=sc.nextLine();
        double balance=amount("Opening balance: ");if(balance<0)throw new IllegalArgumentException("Balance cannot be negative.");
        service.addAccount(new Account(id,cid,type,balance));System.out.println("Account added.");
    }
    private static void addBeneficiary() throws SQLException {
        int id=integer("Beneficiary ID: "),cid=integer("Existing customer ID: ");System.out.print("Name: ");String name=sc.nextLine();
        System.out.print("Account number: ");String no=sc.nextLine();System.out.print("Bank details: ");String bank=sc.nextLine();
        service.addBeneficiary(new Beneficiary(id,cid,name,no,bank));System.out.println("Beneficiary added.");
    }
    private static void transact() throws SQLException {
        int id=integer("Account ID: ");System.out.print("Type (Deposit/Withdrawal): ");String type=sc.nextLine();
        double amount=amount("Amount: ");service.processTransaction(new BankTransaction(id,type,amount));
        Account a=service.findAccountById(id);System.out.println("Transaction saved. New balance: "+(a==null?"unknown":a.getBalance()));
    }


    private static void updateCustomer() {
        try {
            System.out.print("Enter Customer ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            Customer customer = service.findCustomerById(id);

            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new address: ");
            String address = sc.nextLine();

            System.out.print("Enter new contact: ");
            String contact = sc.nextLine();

            Customer updatedCustomer =
                    new Customer(id, name, address, contact);

            boolean updated = service.updateCustomer(updatedCustomer);

            if (updated) {
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("Customer was not updated.");
            }

        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        try {
            System.out.print("Enter Customer ID to delete: ");
            int id = sc.nextInt();

            Customer customer = service.findCustomerById(id);

            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            System.out.print("Are you sure you want to delete this customer? (yes/no): ");
            sc.nextLine();
            String confirmation = sc.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean deleted = service.deleteCustomer(id);

                if (deleted) {
                    System.out.println("Customer deleted successfully!");
                } else {
                    System.out.println("Customer was not deleted.");
                }
            } else {
                System.out.println("Delete cancelled.");
            }

        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

}
